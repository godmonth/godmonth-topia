package com.godmonth.topia.basic.rpc.utils;

import com.godmonth.topia.rpc.error.ErrorCode;

import jodd.bean.BeanUtil;

public class EnumErrorCode implements ErrorCode {
	private Enum<?> enumObject;

	public EnumErrorCode(Enum<?> enumObject) {
		this.enumObject = enumObject;
	}

	@Override
	public String getTemplate() {
		return BeanUtil.silent.getProperty(enumObject, "template");
	}

	@Override
	public String getCode() {
		return enumObject.name();
	}

}