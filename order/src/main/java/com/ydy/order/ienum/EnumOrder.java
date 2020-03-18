package com.ydy.order.ienum;

import com.ydy.ienum.base.IErrorEnum;

public enum EnumOrder implements IErrorEnum{

	ORDER_NOT_FOUND(3000, "订单不存在"),
	ORDER_NOT_BELONG_USER(3001, "订单不属于您");
	
	private Integer code;
	private String msg;

	private EnumOrder(int code, String msg) {
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
