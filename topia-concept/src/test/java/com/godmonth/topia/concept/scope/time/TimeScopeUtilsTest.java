package com.godmonth.topia.concept.scope.time;

import org.junit.jupiter.api.Test;

import java.time.Duration;

class TimeScopeUtilsTest {
    @Test
    void name() {
        TimeScope timeScope = TimeScopeUtils.recentDuration(Duration.ofDays(2));
        System.out.println(timeScope);
    }
}