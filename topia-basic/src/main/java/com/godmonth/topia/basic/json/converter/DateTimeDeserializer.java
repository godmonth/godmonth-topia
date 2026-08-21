package com.godmonth.topia.basic.json.converter;

import java.io.IOException;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateTimeDeserializer extends JsonDeserializer<DateTime> {

	@Override
	public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		return DateTime.parse(jp.readValueAs(String.class));
	}

	@Override
	public Class<DateTime> handledType() {
		return DateTime.class;
	}

}
