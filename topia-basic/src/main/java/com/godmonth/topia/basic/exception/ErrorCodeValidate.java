package com.godmonth.topia.basic.exception;

import com.godmonth.topia.rpc.error.ErrorCode;

import java.util.function.Supplier;

public class ErrorCodeValidate {
    private ErrorCodeValidate() {
    }

    public static String notBlank(String object, ErrorCode errorCode, final Object... values) {
        CustomeValidate.notBlank(object, errorCode.getCode(), errorCode.getTemplate(), values);
        return object;
    }

    public static <T> T notNull(T object, ErrorCode errorCode, final Object... values) {
        CustomeValidate.notNull(object, errorCode.getCode(), errorCode.getTemplate(), values);
        return object;
    }

    public static void isTrue(boolean condition, ErrorCode errorCode, final Object... values) {
        CustomeValidate.isTrue(condition, errorCode.getCode(), errorCode.getTemplate(), values);
    }

    public static void isTrue(boolean condition, Supplier<ErrorCode> errorCode, final Object... values) {
        ErrorCode errorCode2 = errorCode.get();
        CustomeValidate.isTrue(condition, errorCode2.getCode(), errorCode2.getTemplate(), values);
    }

    public static <T> T fail(ErrorCode errorCode, final Object... values) {
        return CustomeValidate.fail(errorCode.getCode(), errorCode.getTemplate(), values);
    }
}
