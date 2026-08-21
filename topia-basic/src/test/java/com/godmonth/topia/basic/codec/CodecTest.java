package com.godmonth.topia.basic.codec;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

public class CodecTest {
	@Test
	public void testNmame() throws Exception {
		String s = "[0xe7][0xb3][0xbb][0xe7][0xbb][0x9f][0xe5][0xbc][0x82][0xe5][0xb8][0xb8]";
		Pattern pattern = Pattern.compile("\\[.*?\\]");
		Matcher matcher = pattern.matcher(s);
		List<Byte> l = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			String group = matcher.group();
//			System.out.println(group);
			String substringBetween = StringUtils.substringBetween(group, "[", "]");
			String substringAfter = StringUtils.substringAfter(substringBetween.toLowerCase(), "0x");
			sb.append(substringAfter);
//			byte parseUnsignedByte = UnsignedBytes.parseUnsignedByte(substringAfter, 16);
//			System.out.println(parseUnsignedByte);
//			System.out.println(parseUnsignedByte, 16);
//			Byte.parseByte(substringAfter, 16);
//			byte[] decodeHex = Hex.decodeHex(substringAfter);
//			System.out.println(ArrayUtils.toString(decodeHex));
//			l.add(parseUnsignedByte);
//			StringBuilder sb = new StringBuilder();
		}
		System.out.println(sb.toString());
		byte[] decodeHex = Hex.decodeHex(sb.toString());
		System.out.println(new String(decodeHex, "utf-8"));

	}

	private static final Pattern pattern = Pattern.compile("\\[.*?\\]");

	@Test
	public void testNmam2() throws Exception {
		String s = "[0xe7][0xb3][0xbb][0xe7][0xbb][0x9f][0xe5][0xbc][0x82][0xe5][0xb8][0xb8]";
		Matcher matcher = pattern.matcher(s);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			String data = matcher.group();
			data = StringUtils.substringBetween(data, "[", "]");
			data = StringUtils.substringAfter(data.toLowerCase(), "0x");
			sb.append(data);
		}
		System.out.println(new String(Hex.decodeHex(sb.toString()), StandardCharsets.UTF_8));
	}
}
