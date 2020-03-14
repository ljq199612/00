package com.ljq.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Uuid {

	private Uuid() {

	}

	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID
	 * 
	 */
	private static int num = 0;
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}
	public static String getDateId(boolean y) {
		if (y) {
			System.out.println(y);
			num = 0;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String a = "D"+dateFormat.format(date);
		num = num+1;
		String str = String.format("%3d", num).replace(" ", "0"); 
		a = a+str;
		return a ;
	}

}
