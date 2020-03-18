/**
 * 
 */
package com.ydy.base.vo;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ydy.ienum.EnumSystem;
import com.ydy.ienum.base.IErrorEnum;

/**
 * @author xuzhaojie
 *
 *         2019年5月7日 上午10:00:19
 */
public class ResultVo<T> implements IErrorEnum {
	private Integer code;
	private String msg;
	private T t;

	{
		setErrorEnum(EnumSystem.SUSS);
	}

	public ResultVo() {
		super();
	}

	public void setErrorEnum(IErrorEnum e) {
		this.code = e.getCode();
		this.msg = e.getMsg();
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return t;
	}

	public void setData(T data) {
		this.t = data;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@JsonIgnore
	public boolean isSus() {
		if (Objects.equals(this.code, EnumSystem.SUSS.getCode()))
			return true;
		return false;
	}
}
