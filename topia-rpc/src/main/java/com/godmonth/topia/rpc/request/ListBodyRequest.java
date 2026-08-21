package com.godmonth.topia.rpc.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * <p>含请求体请求</p>
 *
 * @author shenyue
 */
@Data
@Builder(builderMethodName = "listBodyRequestBuilder")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ListBodyRequest<T extends Serializable> extends BaseRequest {
    /**
     * 列表请求体
     */
    private List<T> body;

}
