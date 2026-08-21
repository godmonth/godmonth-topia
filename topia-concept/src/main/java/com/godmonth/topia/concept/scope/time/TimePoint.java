package com.godmonth.topia.concept.scope.time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>时间点</p >
 *
 * @author shenyue
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TimePoint implements Serializable {
    /**
     * 时间点包含特性
     */
    @NotNull
    private PointInclusion inclusion;

    /**
     * 时间点
     */
    @NotNull
    private Date time;
}
