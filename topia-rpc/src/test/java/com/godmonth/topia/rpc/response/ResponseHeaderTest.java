package com.godmonth.topia.rpc.response;

import com.godmonth.topia.rpc.SystemCode;
import org.junit.jupiter.api.Test;

/**
 * <p></p >
 *
 * @author shenyue
 */
class ResponseHeaderTest {

    @Test
    void testToString() {
        ResponseHeader responseHeader = ResponseHeader.builder().systemCode(SystemCode.FAILURE).businessCode("sss").build();
        System.out.println(responseHeader);
    }
}