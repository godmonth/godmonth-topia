package com.godmonth.topia.rpc.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p></p >
 *
 * @author shenyue
 */
class BaseRequestTest {
    @Test
    public void testToString(){
        RequestHeader
                header = RequestHeader.builder().sourceCode("sss").build();
        BaseRequest build = BaseRequest.builder().header(header).build();
        System.out.println(build);
    }
}