package com.godmonth.topia.basic.config;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.godmonth.topia.basic.exception.ErrorCodeValidate;
import com.godmonth.topia.basic.jackson.JacksonObjectBuilder;
import com.godmonth.topia.rpc.error.ErrorCode;

import jodd.bean.BeanUtil;

public class ConfigReaderImpl<KEY, CONF> implements ConfigReader<KEY, CONF>, InitializingBean {

	private ObjectMapper objectMapper;

	private Resource jsonResource;

	private Class<CONF> objectType;

	private List<CONF> configList;

	private Map<KEY, CONF> configMap;
	private String keyProperty;

	private ErrorCode notFoundErrorCode;

	@Override
	public CONF getConfig(KEY key) {
		if (notFoundErrorCode != null) {
			return ErrorCodeValidate.notNull(configMap.get(key), notFoundErrorCode, key);
		} else {
			return Validate.notNull(configMap.get(key), "config not found,%s", key);
		}
	}

	@Override
	public List<CONF> getConfigList() {
		return configList;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		JacksonObjectBuilder<CONF> builder = new JacksonObjectBuilder<>();
		builder.setObjectMapper(objectMapper);
		builder.setObjectType(objectType);
		builder.setResource(jsonResource);
		configList = builder.createObjectList();
		configMap = Maps.uniqueIndex(configList, new Function<CONF, KEY>() {

			@Override
			public KEY apply(CONF conf) {
				return BeanUtil.pojo.getProperty(conf, keyProperty);
			}
		});
	}

	public void setObjectMapper(@NotNull ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setJsonResource(@NotNull Resource jsonResource) {
		this.jsonResource = jsonResource;
	}

	public void setKeyProperty(@NotNull String keyProperty) {
		this.keyProperty = keyProperty;
	}

	public void setObjectType(@NotNull Class<CONF> objectType) {
		this.objectType = objectType;
	}

	public void setNotFoundErrorCode(ErrorCode notFoundErrorCode) {
		this.notFoundErrorCode = notFoundErrorCode;
	}

}
