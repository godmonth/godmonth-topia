package com.godmonth.topia.basic.exception.i18n;


import com.godmonth.topia.rpc.i18n.I18nMessage;

/**
 * <p></p >
 *
 * @author shenyue
 */
public class I18nCustomeValidate {
    private I18nCustomeValidate() {
    }

    public static <T> T notNull(T object, String errorCode, String message, I18nMessage i18nMessage) {
        if (object == null) {
            fail(errorCode, message, i18nMessage);
        }
        return object;
    }

    public static void isTrue(boolean condition, String errorCode, String message, I18nMessage i18nMessage) {
        if (!condition) {
            fail(errorCode, message, i18nMessage);
        }
    }

    public static <T> T fail(String errorCode, String message, I18nMessage i18nMessage) {
        throw new I18nErrorCodeException(errorCode, message, i18nMessage);
    }
}
