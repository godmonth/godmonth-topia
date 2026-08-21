package com.godmonth.topia.rpc.request;

import org.junit.jupiter.api.Test;

/**
 * <p></p >
 *
 * @author shenyue
 */
class BodyRequestTest {

    @Test
    public void testToString() {
        RequestHeader
                header = RequestHeader.builder().sourceCode("sss").build();
        BodyRequest build = BodyRequest.bodyRequestBuilder().header(header).body("fff").build();
        System.out.println(build);
    }
}