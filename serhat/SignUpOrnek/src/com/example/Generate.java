package com.example;

import java.util.Random;

public class Generate {
	// onay kodu uretimi
	public static String key(int n) {
		Random random = new Random();
		byte[] dizi = new byte[30];
		random.nextBytes(dizi);
		String str = "";
		for (int i = 0; i < n; i++) {
			str += Integer.toHexString(0xF & dizi[i]);
		}
		return str;
	}

}
