/**
 * 
 */
package com.ydy.ienum;

import com.ydy.ienum.base.IErrorEnum;

/**
 * @author xuzhaojie
 *
 *         2018年11月12日 上午9:41:48
 */
public enum EnumSystem implements IErrorEnum{
	SUSS(0, "请求成功"),
	SYSTEM_ERROR(1000, "系统异常"),
	SERVICE_ERROR(1000, "服务出了问题"),
	PARAM_ERROR(1001, "参数缺失"),
	PARAM_FORMAT_ERROR(1002, "参数格式错误"),
	DATA_NOT_MATCH(1003, "数据填写有误"),
	DATA_NOT_FOUND(1004, "找不到数据"),
	TOKEN_HAD_MODIFY(1005, "登录信息(token)被篡改"),
	TOKEN_EXPIRED(1005, "登录信息(token)已过期"),
	TOKEN_NOT_MATCH(1006, "登录信息(token)不匹配"),
	USER_CAN_NOT_GET(1008, "系统获取不到用户身份信息"),
	CODE_EXPIRED(1010, "验证码已过期"),
	CODE_ERROR(1011, "验证码不匹配"),
	DATA_REPEAT(1012, "请勿过快操作，请等待"),
	NO_AUTH(1013, "用户没有登录"),
	PWD_NOT_FIT(1014, "两次密码不匹配"),
	FILE_TYPE_NOT_FIT(1015, "文件类型不匹配,{}"),
	FILE_TOO_BIG(1015, "文件太大了,上限为{}M"),
	CODE_PRD_ERROR(1016, "系统错误,重新刷新页面获取后台验证码"),
	REMOTE_IP_ERROR(1017, "远程业务出现问题"),
	CREATE_FILE_ERROR(1018, "文件创建失败"),
	FILE_IO_ERROR(1019, "文件IO失败"),
	FILE_NOT_FOUND(1020, "文件源不存在"),
	PACK_FILE_ERROR(1021, "打包文件失败"),
	DEL_FILE_ERROR(1022, "删除文件失败"),
	ENCODE_ERROR(1023, "系统内部编码异常"),
	TOKEN_KEY_ERROR(1024, "tokenKey不匹配"),
	JSON_ERROR(1025, "JSON格式解释错误"),
	AES_ENCODE_ERROR(1026, "AES加密错误"),
	AES_DECODE_ERROR(1027, "AES解密错误"),
	SIGN_EXPIRE(1028, "sign过期"),
	CODE_EXPIRE(1029, "code过期"),
	REQUEST_ERROR(1030, "网络请求发起错误");
	
	private Integer code;
	private String msg;

	private EnumSystem(int code, String msg) {
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
