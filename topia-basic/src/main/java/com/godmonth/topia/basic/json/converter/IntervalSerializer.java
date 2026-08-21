package com.godmonth.topia.basic.json.converter;

import java.io.IOException;

import org.joda.time.Interval;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class IntervalSerializer extends JsonSerializer<Interval> {

	@Override
	public void serialize(Interval value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (value != null) {
			gen.writeString(value.toString());
		}
	}

	@Override
	public Class<Interval> handledType() {
		return Interval.class;
	}

}
