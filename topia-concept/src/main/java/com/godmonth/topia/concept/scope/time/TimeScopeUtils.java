package com.godmonth.topia.concept.scope.time;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class TimeScopeUtils {
    private TimeScopeUtils() {
    }

    public static TimeScope recentDuration(Duration duration) {
        Instant now = Instant.now();
        Instant start = now.minus(duration);
        TimePoint nowPoint = new TimePoint(PointInclusion.EXCLUSIVE, Date.from(now));
        TimePoint startPoint = new TimePoint(PointInclusion.INCLUSIVE, Date.from(start));
        return TimeScope.builder().from(startPoint).to(nowPoint).build();
    }
}
