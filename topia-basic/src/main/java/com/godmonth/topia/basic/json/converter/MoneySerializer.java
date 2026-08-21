package com.godmonth.topia.basic.json.converter;

import java.io.IOException;

import org.joda.money.Money;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MoneySerializer extends JsonSerializer<Money> {

	@Override
	public void serialize(Money value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (value != null) {
			gen.writeString(value.toString());
		}
	}

	@Override
	public Class<Money> handledType() {
		return Money.class;
	}

}
