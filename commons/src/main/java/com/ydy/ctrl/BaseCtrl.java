package com.ydy.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ydy.base.vo.ResultVo;
import com.ydy.utils.CookieUtils;

public abstract class BaseCtrl {

	public <T> ResultVo<T> buildBaseVo(T data) {
		ResultVo<T> vo = new ResultVo<T>();
		vo.setData(data);
		return vo;
	}

	public void setCookie(String name, String value, HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.setCookie(request, response, name, value);
	}
}
