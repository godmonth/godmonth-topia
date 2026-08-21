package com.godmonth.topia.basic.replenishment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.Range;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import java.util.Date;

@ToString
@Builder
@AllArgsConstructor
public class ReplenishmentRange {
    private static final Period DEFAULT_START_PERIOD = Period.days(30);
    private static final Period DEFAULT_END_PERIOD = Period.minutes(5);

    public static final ReplenishmentRange DEFAULT_INSTANCE = ReplenishmentRange.builder().startPeriod(DEFAULT_START_PERIOD).endPeriod(DEFAULT_END_PERIOD).build();

    @Builder.Default
    @Setter
    private Period startPeriod = DEFAULT_START_PERIOD;

    @Builder.Default
    @Setter
    private Period endPeriod = DEFAULT_END_PERIOD;

    public ReplenishmentRange() {
        this(DEFAULT_START_PERIOD, DEFAULT_END_PERIOD);
    }

    public Interval getInterval() {
        DateTime startTime = new DateTime().minus(startPeriod);
        DateTime endTime = new DateTime().minus(endPeriod);
        return new Interval(startTime, endTime);
    }


    public Range<Date> getDateRange() {
        Interval interval = getInterval();
        return Range.of(interval.getStart().toDate(), interval.getEnd().toDate());
    }

}
