package com.godmonth.topia.rpc.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * 包含处理结果列表的响应组件
 * 
 * @author shenyue
 *
 * @param <T>
 */
@Data
@SuperBuilder(builderMethodName = "listBodyBuilder")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ListBodyResponse<T extends Serializable> extends BaseResponse {
	/**
	 * 处理结果列表
	 */
	private List<T> body;

}
