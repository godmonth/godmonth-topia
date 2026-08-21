package com.godmonth.topia.basic.rpc.utils2;

import com.godmonth.topia.basic.exception.ErrorCodeException;
import com.godmonth.topia.basic.exception.i18n.I18nErrorCodeException;
import com.godmonth.topia.rpc.SystemCode;
import com.godmonth.topia.rpc.response.ResponseHeader;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;

/**
 * <p></p >
 *
 * @author shenyue
 */
@Slf4j
public class ResponseHeaderUtils {
    private ResponseHeaderUtils() {
    }

    /**
     * @param e
     * @param enableTrace
     * @param customizedExceptionHeader 定制的异常头生成器
     * @return
     */
    public static ResponseHeader exceptionHeader(Throwable e, boolean enableTrace, Function<Throwable, ResponseHeader.ResponseHeaderBuilder> customizedExceptionHeader) {
        ResponseHeader.ResponseHeaderBuilder responseHeaderBuilder = null;
        if (customizedExceptionHeader != null) {
            responseHeaderBuilder = customizedExceptionHeader.apply(e);
        }
        if (responseHeaderBuilder == null) {
            if (e instanceof I18nErrorCodeException) {
                responseHeaderBuilder = i18nErrorCodeExceptionResponseHeaderBuilder((I18nErrorCodeException) e);
            } else if (e instanceof ErrorCodeException) {
                responseHeaderBuilder = errorCodeExceptionResponseHeaderBuilder((ErrorCodeException) e);
            } else {
                responseHeaderBuilder = normalExceptionResponseHeaderBuilder(e);
            }
        }
        if (enableTrace) {
            responseHeaderBuilder = responseHeaderBuilder.trace(ExceptionUtils.getStackTrace(e));
        }
        return responseHeaderBuilder.build();
    }


    private static ResponseHeader.ResponseHeaderBuilder i18nErrorCodeExceptionResponseHeaderBuilder(I18nErrorCodeException e) {
        log.trace("code:{},message:{},i18nMessage:{}", e.getErrorCode(), e.getMessage(), e.getI18nMessage());
        return ResponseHeader.builder().systemCode(SystemCode.SUCCESS).businessCode(e.getErrorCode()).message(e.getMessage()).i18nMessage(e.getI18nMessage());
    }

    private static ResponseHeader.ResponseHeaderBuilder errorCodeExceptionResponseHeaderBuilder(ErrorCodeException e) {
        log.trace("code:{},message:{}", e.getErrorCode(), e.getMessage());
        return ResponseHeader.builder().systemCode(SystemCode.SUCCESS).businessCode(e.getErrorCode()).message(e.getMessage());
    }

    private static ResponseHeader.ResponseHeaderBuilder normalExceptionResponseHeaderBuilder(Throwable e) {
        log.trace("message:{}", e.getMessage());
        return ResponseHeader.builder().systemCode(SystemCode.FAILURE).message(e.getMessage());
    }
}
