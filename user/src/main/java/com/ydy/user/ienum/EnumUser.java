package com.ydy.user.ienum;

import com.ydy.ienum.base.IErrorEnum;

public enum EnumUser  implements IErrorEnum{
	USER_NAME_NO_EXSIT(2000, "用户名不存在"),
	PW_ERROR(2001, "密码错误"),
	USER_NOT_FOUND(2002, "找不到该用户");
	
	private Integer code;
	private String msg;

	private EnumUser(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public Integer getCode() {
		return code;
	}
	
	@Override
	public String getMsg() {
		return msg;
	}

}
