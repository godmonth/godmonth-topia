package com.godmonth.topia.basic.json.converter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.time.FastDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateDeserializer extends JsonDeserializer<Date> {
	private static FastDateFormat fastDateFormat = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSS");

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		try {
			return fastDateFormat.parse(jp.readValueAs(String.class));
		} catch (ParseException e) {
			throw new ContextedRuntimeException();
		}
	}

	@Override
	public Class<Date> handledType() {
		return Date.class;
	}

}
