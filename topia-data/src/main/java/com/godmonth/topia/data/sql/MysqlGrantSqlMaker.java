package com.godmonth.topia.data.sql;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.text.StringSubstitutor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MysqlGrantSqlMaker {

	private static String tablePattern = "create table ";

	private static String grant = "grant ${auth} on ${schema}.${objectName} to ${user}@'%';";

	public static List<String> grant(InputStream inputStream) throws IOException {
		String string = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
		return grant(Arrays.asList(StringUtils.split(string, "\r\n")));
	}

	public static List<String> grant(List<String> sqls) {
		List<String> grantList = new ArrayList<>();
		for (String line : sqls) {
			Param param = getParam(line);
			if (param != null) {
				grantList.addAll(grant(param));
			}
		}
		return grantList;
	}

	public static Param getParam(String sql) {
		if (StringUtils.isNotBlank(StringUtils.substringAfter(sql, tablePattern))) {
			String fullNameObject = StringUtils.substringAfter(sql, tablePattern).trim();
			log.trace("fullNameObject:{}", fullNameObject);
			Param param = new Param();
			param.schema = StringUtils.substringBefore(fullNameObject, ".");
			param.objectName = StringUtils.substringAfter(fullNameObject, ".");
			param.user = param.schema + "user";
			param.auth = "select,insert,update,delete";
			return param;
		} else {
			return null;
		}
	}

	public static class Param {
		public String schema;
		public String objectName;
		public String user;
		public String auth;

		public String getSchema() {
			return schema;
		}

		public void setSchema(String schema) {
			this.schema = schema;
		}

		public String getObjectName() {
			return objectName;
		}

		public void setObjectName(String objectName) {
			this.objectName = objectName;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getAuth() {
			return auth;
		}

		public void setAuth(String auth) {
			this.auth = auth;
		}

	}

	public static List<String> grant(Param param) {
		Map<String, String> paramMap;
		try {
			paramMap = BeanUtils.describe(param);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new ContextedRuntimeException(e);
		}

		StringSubstitutor sub = new StringSubstitutor(paramMap);
		String grantSql = sub.replace(grant);
		return Arrays.asList(grantSql, "");
	}

}
