package com.godmonth.topia.basic.rpc.utils2;

import com.godmonth.topia.rpc.BusinessCode;
import com.godmonth.topia.rpc.SystemCode;
import com.godmonth.topia.rpc.response.MapBodyResponse;
import com.godmonth.topia.rpc.response.ResponseHeader;

import java.util.Map;

/**
 * 包含处理数据的响应组件
 *
 * @author shenyue
 */
public class MapBodyResponseUtils {

    public static <K, V> MapBodyResponse<K, V> codeMapResultResponse(Map<K, V> result, SystemCode systemCode,
                                                                     String businessCode, String message) {
        MapBodyResponse<K, V> rr = new MapBodyResponse<K, V>();
        rr.setHeader(ResponseHeader.builder()
            .systemCode(systemCode)
            .businessCode(businessCode)
            .message(message)
            .build());
        rr.setBody(result);
        return rr;
    }

    public static <K, V> MapBodyResponse<K, V> successMapResultResponse(Map<K, V> result) {
        return codeMapResultResponse(result, SystemCode.SUCCESS, BusinessCode.SUCCESS.name(), null);
    }

    public static <K, V> MapBodyResponse<K, V> failureMapResultResponse(String message) {
        return codeMapResultResponse(null, SystemCode.FAILURE, null, message);
    }


}
