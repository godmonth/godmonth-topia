package com.godmonth.topia.basic.jodd;

import jodd.bean.BeanCopy;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

import com.google.common.base.Function;

public class JoddFunction<IN, OUT> implements Function<IN, OUT> {

	private final Class<OUT> clazz;

	public JoddFunction(Class<OUT> clazz) {
		this.clazz = clazz;
	}

	@Override
	public OUT apply(IN input) {
		OUT output = null;
		try {
			output = clazz.newInstance();
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
		BeanCopy.beans(input, output).copy();
		return output;

	}

}
