package com.godmonth.topia.basic.dozer.converter;

import java.util.Date;

import org.dozer.DozerConverter;
import org.joda.time.DateTime;

public class DateTimeConverter extends DozerConverter<DateTime, Date> {
	public DateTimeConverter() {
		super(DateTime.class, Date.class);
	}

	@Override
	public Date convertTo(DateTime source, Date destination) {
		if (source != null) {
			return source.toDate();
		} else {
			return null;
		}
	}

	@Override
	public DateTime convertFrom(Date source, DateTime destination) {
		if (source != null) {
			return new DateTime(source);
		} else {
			return null;
		}
	}

}
