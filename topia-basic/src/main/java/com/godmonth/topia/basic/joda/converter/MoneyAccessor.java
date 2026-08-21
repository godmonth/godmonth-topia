package com.godmonth.topia.basic.joda.converter;

import java.math.BigDecimal;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import jodd.bean.BeanUtil;

public class MoneyAccessor {
	private MoneyAccessor() {
	}

	public static Money getValue(Object bean, String propertyName) {
		BigDecimal amount = BeanUtil.silent.getProperty(bean, propertyName + "Amount");
		String unit = BeanUtil.silent.getProperty(bean, propertyName + "Unit");

		if (amount != null && unit != null) {
			return Money.of(CurrencyUnit.of(unit), amount);
		}
		return null;
	}

	public static void setValue(Object bean, String propertyName, Money value) {
		String amountPropertyName = propertyName + "Amount";
		String unitPropertyName = propertyName + "Unit";

		if (value != null) {
			BeanUtil.pojo.setProperty(bean, amountPropertyName, value.getAmount());
			BeanUtil.pojo.setProperty(bean, unitPropertyName, value.getCurrencyUnit().getCode());
		} else {
			BeanUtil.pojo.setProperty(bean, amountPropertyName, null);
			BeanUtil.pojo.setProperty(bean, unitPropertyName, null);
		}
	}
}
