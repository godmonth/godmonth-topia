package com.godmonth.topia.concept.scope.time;

import com.godmonth.topia.concept.scope.Scope;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * <p>时间范围.</p >
 * 为什么不直接使用internval或者Range&lt;Date>，因为两边都可空，并且要加上是开闭区间的标志.
 * 为什么需要子类，因为部分序列化工具对泛型支持有限，无法正常序列化Scope&lt;TimePoint>
 *
 * @author shenyue
 */
@Data
@SuperBuilder
@AllArgsConstructor
//@NoArgsConstructor
@ToString(callSuper = true)
public class TimeScope extends Scope<TimePoint> {
}
