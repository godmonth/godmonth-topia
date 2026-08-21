package com.godmonth.topia.rpc.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Map;

/**
 * 包含处理数据的响应组件
 * 
 * @author shenyue
 *
 * @param <K>
 * @param <V>
 */
@Data
@SuperBuilder(builderMethodName = "mapBodyBuilder")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MapBodyResponse<K, V> extends BaseResponse {
	/**
	 * 返回结果
	 */
	private Map<K, V> body;



}
