package com.godmonth.topia.basic.json.converter;

import java.io.IOException;

import org.joda.time.Interval;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class IntervalDeserializer extends JsonDeserializer<Interval> {

	@Override
	public Interval deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		return Interval.parse(jp.readValueAs(String.class));
	}

	@Override
	public Class<Interval> handledType() {
		return Interval.class;
	}

}
