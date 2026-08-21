package com.godmonth.topia.basic.exception;

import java.util.function.Supplier;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.godmonth.topia.rpc.error.ErrorCode;

public class ErrorCodeExceptionSupplier implements Supplier<ErrorCodeException> {

	private ErrorCode errorCode;

	private Object[] values;

	public ErrorCodeExceptionSupplier(ErrorCode errorCode, Object... values) {
		this.errorCode = errorCode;
		this.values = values;
	}

	@Override
	public ErrorCodeException get() {
		String message = errorCode.getTemplate();
		if (StringUtils.isNotBlank(errorCode.getTemplate()) && ArrayUtils.isNotEmpty(values)) {
			message = String.format(errorCode.getTemplate(), values);
		}
		return new ErrorCodeException(errorCode.getCode(), message);
	}

}
