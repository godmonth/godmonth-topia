package com.godmonth.topia.basic.jodd;

import jodd.bean.BeanCopy;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.exception.ContextedRuntimeException;

public class JoddTransformer<IN, OUT> implements Transformer<IN, OUT> {
	private Class<OUT> clazz;

	public JoddTransformer(Class<OUT> clazz) {
		this.clazz = clazz;
	}

	public OUT transform(IN input) {
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
