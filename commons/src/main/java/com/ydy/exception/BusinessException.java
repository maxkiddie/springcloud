/**
 * 
 */
package com.ydy.exception;

import com.ydy.ienum.base.IErrorEnum;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 上午11:54:23
 */
public class BusinessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IErrorEnum errorEnum;
	private Object object;

	public BusinessException(String message) {
		this(message, null);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(IErrorEnum errorEnum) {
		super(errorEnum.getMsg() + "(" + errorEnum.getCode() + ")");
		this.errorEnum = errorEnum;
	}

	public BusinessException(IErrorEnum errorEnum, Object object) {
		super(errorEnum.getMsg() + "(" + errorEnum.getCode() + ")");
		this.errorEnum = errorEnum;
		this.object = object;
	}

	/**
	 * @return the errorEnum
	 */
	public IErrorEnum getErrorEnum() {
		return errorEnum;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	/**
	 * @param errorEnum the errorEnum to set
	 */
	public void setErrorEnum(IErrorEnum errorEnum) {
		this.errorEnum = errorEnum;
	}

}
