package com.godmonth.topia.rpc.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.godmonth.topia.rpc.SystemCode;
import com.godmonth.topia.rpc.i18n.I18nMessage;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p></p >
 *
 * @author shenyue
 */
@Data
@Builder
public class ResponseHeader implements Serializable {
    /**
     * 用作非阻塞式通讯的消息id.可空,值等于对等的请求id
     */
    private String messageId;

    /**
     * 系统代码
     */
    @NotNull
    private SystemCode systemCode;

    /**
     * 业务代码
     */
    private String businessCode;

    /**
     * 异常堆栈
     */
    private String trace;
    /**
     * 相关信息
     */
    private String message;

    /**
     * 国际化消息键
     */
    private I18nMessage i18nMessage;

    @JsonIgnore
    public String getDescription() {
        return String.format("systemCode:%s,businessCode:%s, message:%s", systemCode, businessCode, message);
    }
}
