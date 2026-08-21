package com.godmonth.topia.basic.json.converter;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MoneyDeserializerTest {

    private static ObjectMapper objectMapper;

    @BeforeAll
    private static void prepare() {
        Jackson2ObjectMapperFactoryBean bean = new Jackson2ObjectMapperFactoryBean();
        bean.setSerializers(new MoneySerializer());
        bean.setDeserializers(new MoneyDeserializer());
        bean.afterPropertiesSet();
        objectMapper = bean.getObject();
    }

    @Test
    public void deserialize() throws IOException {
        String sampleJson = "{\"money\":\"USD 0.01\"}";
        MoneyModel readValue = objectMapper.readValue(sampleJson, MoneyModel.class);
        System.out.println(readValue);
    }

}
