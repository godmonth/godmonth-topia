package com.godmonth.topia.basic.exception;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class CustomeValidate {
    private CustomeValidate() {
    }

    public static void notBlank(String object, String errorCode, String message, final Object... values) {
        if (StringUtils.isBlank(object)) {
            fail(errorCode, message, values);
        }
    }

    public static void notNull(Object object, String errorCode, String message, final Object... values) {
        if (object == null) {
            fail(errorCode, message, values);
        }
    }

    public static void isTrue(boolean condition, String errorCode, String message, final Object... values) {
        if (!condition) {
            fail(errorCode, message, values);
        }
    }

    public static <T> T fail(String errorCode, String message, final Object... values) {
        if (StringUtils.isNotBlank(message) && ArrayUtils.isNotEmpty(values)) {
            message = String.format(message, values);
        }
        throw new ErrorCodeException(errorCode, message);
    }
}
