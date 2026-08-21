package com.godmonth.topia.rpc.request;

import org.junit.jupiter.api.Test;

/**
 * <p></p >
 *
 * @author shenyue
 */
class RequestHeaderTest {
    @Test
    public void testToString() {
        RequestHeader
                header = RequestHeader.builder().sourceCode("sss").build();
        System.out.println(header);
    }
}