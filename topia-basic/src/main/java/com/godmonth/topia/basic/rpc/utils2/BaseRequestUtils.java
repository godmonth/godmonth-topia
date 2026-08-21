package com.godmonth.topia.basic.rpc.utils2;

import com.godmonth.topia.rpc.request.BaseRequest;
import com.godmonth.topia.rpc.request.BodyRequest;
import com.godmonth.topia.rpc.request.ListBodyRequest;

import java.io.Serializable;
import java.util.List;

/**
 * 基础响应组件
 *
 * @author shenyue
 */
public class BaseRequestUtils {

    private BaseRequestUtils() {
    }

    public static BaseRequest empty() {
        return BaseRequest.builder().build();
    }

    public static <T extends Serializable> BodyRequest<T> body(T body) {
        return new BodyRequest<>();
    }

    public static <T extends Serializable> ListBodyRequest<T> listBodyRequest(List<T> listBody) {
        ListBodyRequest listBodyRequest = new ListBodyRequest();
        listBodyRequest.setBody(listBody);
        return listBodyRequest;
    }

    public static <T extends Serializable> BodyRequest<T> listBodyRequest(T body) {
        BodyRequest bodyRequest = new BodyRequest();
        bodyRequest.setBody(body);
        return bodyRequest;
    }


}
