package com.godmonth.topia.rpc.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.Valid;
import java.io.Serializable;
import java.util.Locale;

/**
 * <p></p >
 *
 * @author shenyue
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RequestHeader implements Serializable {
    public static final RequestHeader EMPTY_HEADER = new RequestHeader();
    /**
     * 用作非阻塞式通讯的消息id.可空
     */
    private String messageId;

    /**
     * 来源系统代码
     */
    private String sourceCode;

    /**
     * 请求发起方.这个属性的用来让服务方识别请求者,并以此判断请求者是否有权限使用本次请求涉及的业务数据.这里的请求者是个抽象的概念,由服务方自行定义,请求方执行.当该字段空时,应该放行
     */
    @Valid
    private Issuer issuer;

    /**
     * 是否开启缓存.空表示AUTO
     */
    private Cacheable cacheable;

    /**
     * 来源本地代码
     */
    private Locale locale;
}
