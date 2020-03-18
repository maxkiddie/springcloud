package com.ydy.utils;

import java.math.BigDecimal;

public class MoneyUtil {

	public static Long yuanToCent(Double yuan) {
		return BigDecimalUtil.mul(new BigDecimal(yuan + ""), new BigDecimal(100)).longValue();
	}

	public static Double centToYuan(Long cent) {
		return BigDecimalUtil.div2bit(new BigDecimal(cent), new BigDecimal(100)).doubleValue();
	}

	public static Double minusToZero(Double d) {
		if (d < 0) {
			return 0.0;
		}
		return d;
	}
}
