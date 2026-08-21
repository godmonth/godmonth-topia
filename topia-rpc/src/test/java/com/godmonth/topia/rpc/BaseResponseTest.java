package com.godmonth.topia.rpc;

import com.godmonth.topia.rpc.response.BaseResponse;
import com.godmonth.topia.rpc.response.ResponseHeader;
import org.junit.jupiter.api.Test;

public class BaseResponseTest {

	@Test
	public void tt() {
		BaseResponse br = new BaseResponse();
		br.setHeader(ResponseHeader.builder()
				.systemCode(SystemCode.SUCCESS)
				.businessCode("ff")
				.message("mm")
				.trace("tt")
				.build());
		System.out.println(br.toString());
	}
}
