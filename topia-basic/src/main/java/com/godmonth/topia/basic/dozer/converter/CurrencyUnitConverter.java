package com.godmonth.topia.basic.dozer.converter;

import org.dozer.DozerConverter;
import org.joda.money.CurrencyUnit;

public class CurrencyUnitConverter extends DozerConverter<String, CurrencyUnit> {
	public CurrencyUnitConverter() {
		super(String.class, CurrencyUnit.class);
	}

	@Override
	public CurrencyUnit convertTo(String source, CurrencyUnit destination) {
		if (source != null) {
			return CurrencyUnit.of(source);
		} else {
			return null;
		}
	}

	@Override
	public String convertFrom(CurrencyUnit source, String destination) {
		if (source != null) {
			return source.getCode();
		} else {
			return null;
		}
	}

}
