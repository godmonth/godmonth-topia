package com.godmonth.topia.basic.exception;

import com.godmonth.topia.rpc.error.ErrorCodeImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <p></p >
 *
 * @author shenyue
 */
class ErrorCodeValidateTest {
    @Test
    void notblank() {
        String s = ErrorCodeValidate.notBlank("aaa", new ErrorCodeImpl("c", "b"));
        Assertions.assertEquals("aaa", s);
    }

    @Test
    void blank() {
        ErrorCodeImpl errorCode = new ErrorCodeImpl("c", "b");
        try {
            String s = ErrorCodeValidate.notBlank("", errorCode);
            Assertions.fail();
        } catch (ErrorCodeException e) {
            Assertions.assertEquals(errorCode.getCode(), e.getErrorCode());
        }
    }
}