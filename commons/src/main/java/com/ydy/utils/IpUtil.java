package com.ydy.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * 作用：获取ip
 * 
 * @author 庄佳弟
 * 
 *         2018年1月13日
 */
public class IpUtil {
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

}
