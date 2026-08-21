package com.godmonth.topia.basic.predicate;

import org.apache.commons.collections4.Predicate;

import jodd.bean.BeanUtil;

public class CC4BeanPredicate<T> implements Predicate<T> {
	private String propertyName;

	private Predicate predicate;

	public CC4BeanPredicate(String propertyName, Predicate predicate) {
		this.propertyName = propertyName;
		this.predicate = predicate;
	}

	@Override
	public boolean evaluate(T object) {
		Object propertySilently = BeanUtil.silent.getProperty(object, propertyName);
		return predicate.evaluate(propertySilently);
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Predicate getPredicate() {
		return predicate;
	}

}
