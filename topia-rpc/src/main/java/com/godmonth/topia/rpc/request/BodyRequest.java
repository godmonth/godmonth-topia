package com.godmonth.topia.rpc.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import jakarta.validation.Valid;
import java.io.Serializable;

/**
 * <p>含请求体请求</p>
 *
 * @author shenyue
 */
@Data
@SuperBuilder(builderMethodName = "bodyRequestBuilder")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BodyRequest<T extends Serializable> extends BaseRequest {

    /**
     * 请求体
     */
    @Valid
    private T body;

}
