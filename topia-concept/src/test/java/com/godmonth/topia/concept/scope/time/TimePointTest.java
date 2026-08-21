package com.godmonth.topia.concept.scope.time;

import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * <p></p >
 *
 * @author shenyue
 */
class TimePointTest {
    @Test
    void name() {
        TimePoint build = TimePoint.builder().inclusion(PointInclusion.INCLUSIVE).time(new Date()).build();
        System.out.println(build);
    }
}