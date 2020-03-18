/**
 * 
 */
package com.ydy.ctrl;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ydy.base.vo.ResultVo;
import com.ydy.base.vo.ErrorVo;
import com.ydy.exception.BusinessException;
import com.ydy.exception.RemoteApiException;
import com.ydy.exception.ValidateException;
import com.ydy.ienum.EnumSystem;

/**
 * 控制器全局处理器
 * 
 * @author nothing
 *
 *         2019年5月12日 上午8:58:23
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	public ResultVo<Object> exceptionMyHandler(HttpServletRequest req, BusinessException e) {
		ResultVo<Object> vo = new ResultVo<Object>();
		vo.setErrorEnum(e.getErrorEnum());
		log.error(JSON.toJSONString(vo));
		return vo;
	}

	@ExceptionHandler(value = RemoteApiException.class)
	@ResponseBody
	public ResultVo<Object> exceptionMyHandler(HttpServletRequest req, RemoteApiException e) {
		ResultVo<Object> vo = new ResultVo<Object>();
		vo.setErrorEnum(e.getErrorEnum());
		log.error(JSON.toJSONString(vo));
		return vo;
	}

	@ExceptionHandler(value = ValidateException.class)
	@ResponseBody
	public ResultVo<Object> exceptionMyHandler(HttpServletRequest req, ValidateException e) {
		ErrorVo<Object> vo = new ErrorVo<Object>();
		vo.setErrorEnum(EnumSystem.DATA_NOT_MATCH);
		vo.setErrors(e.getErrors());
		log.error(JSON.toJSONString(vo));
		return vo;
	}

	@ExceptionHandler
	@ResponseBody
	public ResultVo<Object> exceptionHandler(HttpServletRequest req, Exception e) {
		String requestURI = req.getRequestURI();
		e.printStackTrace();
		String msg = requestURI + "出现异常:" + e.getMessage();
		log.error(msg);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		ErrorVo<Object> vo = new ErrorVo<Object>();
		vo.setErrorEnum(EnumSystem.SYSTEM_ERROR);
		vo.putError("error", sw.toString());
		log.error(JSON.toJSONString(vo));
		return vo;
	}
}
