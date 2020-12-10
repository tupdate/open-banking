package com.cts.thirdparty.utility;

import java.util.Base64;

public class Base64Utility {
	private static Base64.Encoder encoder = Base64.getEncoder();
	private static Base64.Decoder decoder = Base64.getDecoder();

	public static String getEncodedString(String str) {
		return encoder.encodeToString(str.getBytes());
	}

	public static String getDecodedString(String str) {
		return new String(decoder.decode(str));
	}

	public static void main(String[] args) {
		String username = getEncodedString("admin");
		System.out.println(username);
		System.out.println(getDecodedString(username));

	}
}
