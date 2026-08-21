package com.godmonth.topia.concept.scope.time;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * <p></p >
 *
 * @author shenyue
 */
class TimeScopeTest {
    @Test
    void name() {
        TimePoint startTime = TimePoint.builder().inclusion(PointInclusion.EXCLUSIVE).time(DateUtils.addDays(new Date(), -1)).build();
        TimePoint endTime = TimePoint.builder().inclusion(PointInclusion.INCLUSIVE).time(DateUtils.addDays(new Date(), 0)).build();

        TimeScope timeScope = TimeScope.builder().from(startTime).to(endTime).build();
        System.out.println(timeScope);
        TimeScope timeScope1 = new TimeScope();

    }

    @Test
    void name2() {
        TimePoint startTime = new TimePoint();
        startTime.setInclusion(PointInclusion.INCLUSIVE);
        startTime.setTime(new Date());
        TimePoint endTime = startTime;
        TimeScope timeScope = new TimeScope();
        timeScope.setFrom(startTime);
        timeScope.setTo(endTime);
        System.out.println(timeScope);
    }


}