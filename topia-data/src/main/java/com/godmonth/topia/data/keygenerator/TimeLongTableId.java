package com.godmonth.topia.data.keygenerator;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Hibernate 7 表段主键（替代 {@code @GenericGenerator}），由 {@link TimeLongTableIdGenerator} 实现。
 * 规则与历史「表序列 + {@code yyyyMMdd} 前缀拼 long id」一致（底层 {@code TableGenerator} + 日期前缀）；成员对应原 {@code @Parameter}：
 * {@code table_name}、{@code segment_column_name}、{@code value_column_name}、{@code segment_value}、
 * {@code optimizer}、{@code increment_size}。
 */
@IdGeneratorType(TimeLongTableIdGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface TimeLongTableId {

    String tableName() default "t_sequence_table";

    String segmentColumnName() default "sequence_name";

    String valueColumnName() default "sequence_value";

    String segmentValue();

    String optimizer() default "pooled-lo";

    int incrementSize() default 10;
}
