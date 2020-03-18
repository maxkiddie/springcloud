package com.ydy.utils;

public class DesensitizationUtil {
	public static String name(String str) {
		if (StringUtil.isEmpty(str)) {
			return "";
		}
		String name = str.substring(0, 1);
		return name + "**";
	}

	public static String phone(String str) {
		if (StringUtil.isEmpty(str)) {
			return "";
		}
		return str.substring(0, 3) + "****" + str.substring(str.length() - 4);
	}

}