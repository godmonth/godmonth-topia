package com.godmonth.topia.data.keygenerator;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.FastDateFormat;
import org.hibernate.boot.model.relational.SqlStringGenerationContext;
import org.hibernate.boot.model.relational.internal.SqlStringGenerationContextImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.AnnotationBasedGenerator;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.GeneratorCreationContext;
import org.hibernate.id.OptimizableGenerator;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.lang.reflect.Member;
import java.util.Date;
import java.util.EnumSet;
import java.util.Properties;

/**
 * 与 {@link TimeLongTableId} 配套；逻辑与历史「表序列 + {@code yyyyMMdd} 前缀拼 long id」一致（{@link TableGenerator} + 日期前缀）。
 * 实现 {@link AnnotationBasedGenerator}，由 Hibernate 调用 {@link #initialize}，避免 Spring 把注解类型当成要注入的 Bean。
 * Hibernate 6.6 使用 {@code configure(Type, Properties, ServiceRegistry)}。
 */
public final class TimeLongTableIdGenerator implements BeforeExecutionGenerator, AnnotationBasedGenerator<TimeLongTableId> {

    private static final int SEQUENCE_LENGTH = 9;

    private final TableGenerator tableGenerator = new TableGenerator();
    private final long baseNumber = (long) Math.pow(10, SEQUENCE_LENGTH);
    private final FastDateFormat dateFormat = FastDateFormat.getInstance("yyyyMMdd");

    @Override
    public void initialize(
            TimeLongTableId config,
            Member annotatedMember,
            GeneratorCreationContext creationContext) {
        Properties params = new Properties();
        params.setProperty(TableGenerator.TABLE_PARAM, config.tableName());
        params.setProperty(TableGenerator.SEGMENT_COLUMN_PARAM, config.segmentColumnName());
        params.setProperty(TableGenerator.VALUE_COLUMN_PARAM, config.valueColumnName());
        params.setProperty(TableGenerator.SEGMENT_VALUE_PARAM, config.segmentValue());
        params.setProperty(OptimizableGenerator.OPT_PARAM, config.optimizer());
        params.setProperty(OptimizableGenerator.INCREMENT_PARAM, String.valueOf(config.incrementSize()));

        Type type = creationContext.getProperty().getType();
        ServiceRegistry serviceRegistry = creationContext.getServiceRegistry();
        tableGenerator.configure(type, params, serviceRegistry);
        tableGenerator.registerExportables(creationContext.getDatabase());

        JdbcEnvironment jdbcEnvironment = serviceRegistry.requireService(JdbcEnvironment.class);
        SqlStringGenerationContext sqlContext = SqlStringGenerationContextImpl.fromExplicit(
                jdbcEnvironment,
                creationContext.getDatabase(),
                creationContext.getDefaultCatalog(),
                creationContext.getDefaultSchema());
        tableGenerator.initialize(sqlContext);
    }

    @Override
    public Object generate(
            SharedSessionContractImplementor session,
            Object owner,
            Object currentValue,
            EventType eventType) {
        Number n = (Number) tableGenerator.generate(session, owner);
        long longValue = n.longValue();
        Validate.isTrue(longValue >= 0);
        longValue = longValue % baseNumber;
        return datePrefix() + longValue;
    }

    private long datePrefix() {
        String pattern = dateFormat.format(new Date());
        return Long.parseLong(pattern) * baseNumber;
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }

    @Override
    public boolean generatedOnExecution() {
        return false;
    }
}
