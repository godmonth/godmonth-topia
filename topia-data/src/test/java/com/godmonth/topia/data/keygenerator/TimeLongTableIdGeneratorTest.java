package com.godmonth.topia.data.keygenerator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.EventType;
import org.hibernate.id.enhanced.TableGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * 纯 Mock，不依赖数据库；通过反射替换 {@link TableGenerator} 验证拼接与边界。
 */
class TimeLongTableIdGeneratorTest {

    private static final long BASE = 1_000_000_000L;

    private TimeLongTableIdGenerator generator;

    private AutoCloseable mocks;

    @Mock
    private TableGenerator tableGenerator;

    @Mock
    private SharedSessionContractImplementor session;

    @BeforeEach
    void setUp() throws Exception {
        mocks = MockitoAnnotations.openMocks(this);
        generator = new TimeLongTableIdGenerator();
        replaceTableGenerator(generator, tableGenerator);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (mocks != null) {
            mocks.close();
        }
    }

    private static void replaceTableGenerator(TimeLongTableIdGenerator gen, TableGenerator mock) throws Exception {
        Field f = TimeLongTableIdGenerator.class.getDeclaredField("tableGenerator");
        f.setAccessible(true);
        f.set(gen, mock);
    }

    @Test
    void getEventTypes_containsInsertOnly() {
        assertEquals(EnumSet.of(EventType.INSERT), generator.getEventTypes());
    }

    @Test
    void generatedOnExecution_isFalse() {
        assertFalse(generator.generatedOnExecution());
    }

    @Test
    void generate_positiveSequence_combinesDatePrefixAndSequence() {
        String yyyymmdd = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        long seq = 5L;
        when(tableGenerator.generate(any(), any())).thenReturn(seq);
        Object id = generator.generate(session, new Object(), null, EventType.INSERT);
        long expected = Long.parseLong(yyyymmdd) * BASE + seq;
        assertEquals(expected, id);
    }

    @Test
    void generate_sequenceExceedsNineDigits_appliesModulo() {
        String yyyymmdd = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        long seq = 1_234_567_890L;
        when(tableGenerator.generate(any(), any())).thenReturn(seq);
        Object id = generator.generate(session, new Object(), null, EventType.INSERT);
        long expected = Long.parseLong(yyyymmdd) * BASE + (seq % BASE);
        assertEquals(expected, id);
    }

    @Test
    void generate_negativeSequence_throwsIllegalArgumentException() {
        when(tableGenerator.generate(any(), any())).thenReturn(-1L);
        assertThrows(
                IllegalArgumentException.class,
                () -> generator.generate(session, new Object(), null, EventType.INSERT));
    }
}
