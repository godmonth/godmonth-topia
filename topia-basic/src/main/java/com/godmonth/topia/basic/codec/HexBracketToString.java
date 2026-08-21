package com.godmonth.topia.basic.codec;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ContextedRuntimeException;

public class HexBracketToString {

	private static final Pattern pattern = Pattern.compile("\\[.*?\\]");

	private HexBracketToString() {
	}

	public static String decode(String input) {

		Matcher matcher = pattern.matcher(input);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			String data = matcher.group();
			data = StringUtils.substringBetween(data, "[", "]");
			data = StringUtils.substringAfter(data.toLowerCase(), "0x");
			sb.append(data);
		}
		try {
			return new String(Hex.decodeHex(sb.toString()), StandardCharsets.UTF_8);
		} catch (DecoderException e) {
			throw new ContextedRuntimeException(e);
		}
	}
}
