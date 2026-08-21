package com.godmonth.topia.basic.dozer.converter;

import java.util.Collections;
import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DateTimeConverterTest {

    private static DozerBeanMapper mapper;

    @BeforeAll
    private static void prepare() {
        mapper = new DozerBeanMapper(Collections.singletonList("dozer.xml"));
    }

    @Test
    public void convert() {
        A a = new A();
        a.setTime(new DateTime());
        B map = mapper.map(a, B.class);
        System.out.println(map.getTime());
    }

    public static class A {
        private DateTime time;

        public DateTime getTime() {
            return time;
        }

        public void setTime(DateTime time) {
            this.time = time;
        }

    }

    public static class B {
        private Date time;

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

    }
}
