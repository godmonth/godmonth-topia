package com.godmonth.topia.data.sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.godmonth.topia.data.sql.MysqlGrantSqlMaker.Param;
import org.junit.jupiter.api.Test;

public class MysqlGrantSqlMakerTest {
	@Test
	public void grantIs() throws IOException {
		try (InputStream is = new FileInputStream("src/test/resources/abc.sql")) {
			List<String> grant = GrantSqlMaker.grant(is);
			System.out.println(grant);
		}

	}

	@Test
	public void grant() throws IOException {
		String readFileToString = FileUtils.readFileToString(new File("src/test/resources/mysql.sql"),
				StandardCharsets.UTF_8);
		List<String> grant = MysqlGrantSqlMaker.grant(Arrays.asList(StringUtils.split(readFileToString, "\r\n")));
		String collect = grant.stream().collect(Collectors.joining("\r\n"));
		FileUtils.writeStringToFile(new File("target/mysql.grant.sql"), collect, StandardCharsets.UTF_8);

	}

	@Test
	public void grantTable() throws IOException {
		Param param = MysqlGrantSqlMaker.getParam("create table fxbtg.t_operator_create_order");
		List<String> grant = MysqlGrantSqlMaker.grant(param);
		System.out.println(grant);
	}

}
