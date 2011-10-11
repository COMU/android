package com.ahbap;

import java.util.Random;

public class Generate {
	// generates a verification code 
	public static String key(int n) {
		Random random = new Random();
		byte[] array = new byte[30];
		random.nextBytes(array);
		String str = "";
		for (int i = 0; i < n; i++) {
			str += Integer.toHexString(0xF & array[i]);
		}
		return str;
	}

}
