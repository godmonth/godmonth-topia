package com.godmonth.topia.basic.codec;


import org.junit.jupiter.api.Test;

public class HexBracketToStringTest {

	@Test
	public void decode() {
		String s = "[0xe7][0xb3][0xbb][0xe7][0xbb][0x9f][0xe5][0xbc][0x82][0xe5][0xb8][0xb8]";
		String decode = HexBracketToString.decode(s);
		System.out.println(decode);
	}
}
