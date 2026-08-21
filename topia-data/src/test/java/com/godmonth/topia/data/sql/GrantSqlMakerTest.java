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
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.godmonth.topia.data.sql.GrantSqlMaker.Param;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GrantSqlMakerTest {
    @Test
    public void grantIs() throws IOException {
        try (InputStream is = new FileInputStream("src/test/resources/abc.sql")) {
            List<String> grant = GrantSqlMaker.grant(is);
            System.out.println(grant);
        }

    }

    @Test
    @Disabled
    public void grant() throws IOException {
        String readFileToString = FileUtils.readFileToString(new File("target/abc.sql"),
                StandardCharsets.UTF_8);
        List<String> grant = GrantSqlMaker.grant(Arrays.asList(StringUtils.split(readFileToString, "\r\n")));
        String collect = grant.stream().collect(Collectors.joining("\r\n"));
        FileUtils.writeStringToFile(new File("target/abc.grant.sql"), collect, StandardCharsets.UTF_8);

    }

    @Test
    public void getParam() throws IOException {
        Param param = GrantSqlMaker.getParam("create sequence fxbtg.seq_operator_create_order");
        System.out.println(ToStringBuilder.reflectionToString(param));
    }

    @Test
    public void grantSequence() throws IOException {
        Param param = GrantSqlMaker.getParam("create sequence fxbtg.seq_operator_create_order");
        List<String> grant = GrantSqlMaker.grant(param);
        System.out.println(grant);
    }

    @Test
    public void grantTable() throws IOException {
        Param param = GrantSqlMaker.getParam("create table fxbtg.t_operator_create_order");
        List<String> grant = GrantSqlMaker.grant(param);
        System.out.println(grant);
    }

}
