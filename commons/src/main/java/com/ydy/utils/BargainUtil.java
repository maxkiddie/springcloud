package com.ydy.utils;

import java.math.BigDecimal;

public class BargainUtil {

	public static Double randomRedPacket(Double totalMoney, Double factor, Integer count) {
		if (count == 1)
			return totalMoney;
		Double maxMoney = totalMoney / count * factor;
		Double minMoney = totalMoney / count / factor;
		// 在 minMoney到maxMoney 生成一个随机红包
		Double redPacket = (Double) (Math.random() * (maxMoney - minMoney) + minMoney);
		return BigDecimalUtil.div2bit(new BigDecimal(redPacket), new BigDecimal(1)).doubleValue();
	}

	public static void main(String[] args) {
		for (int i = 0; i <= 100; i++) {
			System.out.println(randomRedPacket(20.0, 1.3, 10));
		}
	}
}