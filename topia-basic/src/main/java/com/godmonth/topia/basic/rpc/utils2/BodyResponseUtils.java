package com.godmonth.topia.basic.rpc.utils2;

import com.godmonth.topia.rpc.BusinessCode;
import com.godmonth.topia.rpc.SystemCode;
import com.godmonth.topia.rpc.response.BodyResponse;
import com.godmonth.topia.rpc.response.ResponseHeader;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 包含处理数据的响应组件
 *
 * @author shenyue
 */

public class BodyResponseUtils {

    public static <T extends Serializable> BodyResponse<T> codeBodyResponse(T result, SystemCode systemCode, String businessCode, String message) {
        ResponseHeader responseHeader = ResponseHeader.builder().systemCode(systemCode).businessCode(businessCode).message(message).build();
        return BodyResponse.<T>bodyBuilder().header(responseHeader).body(result).build();
    }

    public static <T extends Serializable> BodyResponse<T> successBodyResponse(T result) {
        return codeBodyResponse(result, SystemCode.SUCCESS, BusinessCode.SUCCESS.name(), null);
    }

    public static <T extends Serializable> BodyResponse<T> failureBodyResponse(String message) {
        return codeBodyResponse(null, SystemCode.FAILURE, null, message);
    }

    public static <T extends Serializable> BodyResponse<T> exceptionBodyResponse(Throwable throwable) {
        return exceptionBodyResponse(throwable, false, null);
    }

    public static <T extends Serializable> BodyResponse<T> exceptionBodyResponse(Throwable throwable, boolean enableTrace, Function<Throwable, ResponseHeader.ResponseHeaderBuilder> customizedExceptionHeader) {
        return BodyResponse.<T>bodyBuilder().header(ResponseHeaderUtils.exceptionHeader(throwable, enableTrace, customizedExceptionHeader)).build();
    }

    public static <T extends Serializable> T parse(BodyResponse<T> resultResponse, String... silentBusinessCodes) {
        BaseResponseUtils.parse(resultResponse, silentBusinessCodes);
        return resultResponse.getBody();
    }

}
