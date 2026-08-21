package com.godmonth.topia.basic.dubbo;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

import lombok.extern.slf4j.Slf4j;

@Activate(group = CommonConstants.CONSUMER)
@Slf4j
public class DubboConsumerLoggerFilter implements Filter {
	public static final int DEFAULT_MAX_PAY_LOAD_LENGTH = 2000;

	private int maxPayLoadLength = DEFAULT_MAX_PAY_LOAD_LENGTH;

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		try {
			Result result = invoker.invoke(invocation);
			if (log.isTraceEnabled()) {
				log.trace("invocation:{}, result:{}",
						StringUtils.abbreviate(String.valueOf(invocation), maxPayLoadLength),
						StringUtils.abbreviate(String.valueOf(result.getValue()), maxPayLoadLength));
			}
			return result;
		} catch (RuntimeException e) {
			if (log.isTraceEnabled()) {
				log.trace("exception:{}", StringUtils.abbreviate(e.getMessage(), maxPayLoadLength * 2));
			}
			throw e;
		}

	}

}
