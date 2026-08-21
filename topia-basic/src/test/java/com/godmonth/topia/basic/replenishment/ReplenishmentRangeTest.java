package com.godmonth.topia.basic.replenishment;

import org.joda.time.Interval;
import org.joda.time.Period;
import org.junit.jupiter.api.Test;

public class ReplenishmentRangeTest {

    @Test
    public void getInterval() {
        ReplenishmentRange range = new ReplenishmentRange();
        Interval interval = range.getInterval();
        System.out.println(interval);
        System.out.println(range.getDateRange());
        System.out.println(range);
    }

    @Test
    public void testBuilder() {
        ReplenishmentRange replenishmentRange = new ReplenishmentRange();
        System.out.println(replenishmentRange);
        System.out.println(replenishmentRange.getDateRange());
    }

    @Test
    public void builder() {
        ReplenishmentRange range = ReplenishmentRange.builder().build();
        System.out.println(range);
        range = ReplenishmentRange.builder().endPeriod(Period.hours(9)).startPeriod(Period.weeks(3)).build();
        System.out.println(range);
        range = ReplenishmentRange.builder().endPeriod(Period.hours(9)).build();
        System.out.println(range);
        range = ReplenishmentRange.builder().startPeriod(Period.weeks(3)).build();
        System.out.println(range);
    }
}
