package com.godmonth.topia.basic.exception.i18n;

import com.godmonth.topia.rpc.error.i18n.I18nErrorCode;
import com.godmonth.topia.rpc.i18n.I18nMessage;

import java.io.Serializable;

/**
 * <p></p >
 *
 * @author shenyue
 */
public class I18nErrorCodeValidate {
    private I18nErrorCodeValidate() {
    }

    public static <T> T notNull(T object, I18nErrorCode errorCode, I18nMessage i18nMessage) {
        return I18nCustomeValidate.notNull(object, errorCode.getCode(), errorCode.getTemplate(), i18nMessage);
    }

    public static <T> T notNull(T object, I18nErrorCode errorCode, Serializable... params) {
        I18nMessage i18nMessage = I18nMessage.builder().key(errorCode.getMessageKey()).params(params).build();
        return notNull(object, errorCode, i18nMessage);
    }

    public static void isTrue(boolean condition, I18nErrorCode errorCode, I18nMessage i18nMessage) {
        I18nCustomeValidate.isTrue(condition, errorCode.getCode(), errorCode.getTemplate(), i18nMessage);
    }

    public static void isTrue(boolean condition, I18nErrorCode errorCode, Serializable... params) {
        I18nMessage i18nMessage = I18nMessage.builder().key(errorCode.getMessageKey()).params(params).build();
        isTrue(condition, errorCode, i18nMessage);
    }

    public static <T> T fail(I18nErrorCode errorCode, Serializable... params) {
        I18nMessage i18nMessage = I18nMessage.builder().key(errorCode.getMessageKey()).params(params).build();
        return fail(errorCode, i18nMessage);
    }

    public static <T> T fail(I18nErrorCode errorCode, I18nMessage i18nMessage) {
        return I18nCustomeValidate.fail(errorCode.getCode(), errorCode.getTemplate(), i18nMessage);
    }
}
