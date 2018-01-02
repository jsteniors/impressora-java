package com.wing.util;

import java.util.Random;

public class HashUtil {
	
	public static String hashGenerator() {
		Random rand = new Random();
		StringBuilder build = new StringBuilder();
		for(int i=0;i<15;i++) {
			int num = rand.nextInt(26)+65;
			int num1 = rand.nextInt(10)+48;
			int num2 = rand.nextInt(26)+97;
			int[] ran = {num,num1,num2};
			int n = rand.nextInt(3);
			build.append((char)ran[n]);
		}
		return build.toString();
	}
}
