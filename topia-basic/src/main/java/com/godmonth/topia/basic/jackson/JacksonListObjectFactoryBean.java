package com.godmonth.topia.basic.jackson;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author shenyue
 */
public class JacksonListObjectFactoryBean<T> implements FactoryBean<List<T>>, InitializingBean {

	private Resource jsonResource;

	private ObjectMapper objectMapper;

	private Class<T> objectType;

	private List<T> list;

	@Override
	public void afterPropertiesSet() throws JsonParseException, JsonMappingException, IOException {
		JacksonObjectBuilder<T> builder = new JacksonObjectBuilder<T>();
		builder.setObjectMapper(objectMapper);
		builder.setObjectType(objectType);
		builder.setResource(jsonResource);
		list = builder.createObjectList();
	}

	@Override
	public List<T> getObject() throws Exception {
		return list;
	}

	@Override
	public Class<?> getObjectType() {
		return List.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setObjectMapper(@NotNull ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setJsonResource(@NotNull Resource jsonResource) {
		this.jsonResource = jsonResource;
	}

	public void setObjectType(@NotNull Class<T> objectType) {
		this.objectType = objectType;
	}
}
