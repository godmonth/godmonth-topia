package com.godmonth.topia.basic.predicate;

import com.google.common.base.Predicate;

import jodd.bean.BeanUtil;

public class GuavaBeanPredicate<T> implements Predicate<T> {
	private String propertyName;

	private Predicate predicate;

	public GuavaBeanPredicate(String propertyName, Predicate predicate) {
		this.propertyName = propertyName;
		this.predicate = predicate;
	}

	@Override
	public boolean test(T input) {
		return Predicate.super.test(input);
	}

	@Override
	public boolean apply(T input) {
		Object propertySilently = BeanUtil.silent.getProperty(input, propertyName);
		return predicate.apply(propertySilently);
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Predicate getPredicate() {
		return predicate;
	}

}
