package com.godmonth.topia.rpc.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 包含处理数据的响应组件
 *
 * @param <T>
 * @author shenyue
 */
@Data
@SuperBuilder(builderMethodName = "bodyBuilder")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BodyResponse<T extends Serializable> extends BaseResponse {
    /**
     * 返回结果
     */
    private T body;

}
