package com.ydy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ydy.annotation.UserToken;
import com.ydy.base.vo.ResultVo;
import com.ydy.constant.SystemConstant;
import com.ydy.ienum.EnumSystem;
import com.ydy.model.User;
import com.ydy.utils.CookieUtils;
import com.ydy.utils.JsonUtil;
import com.ydy.utils.TokenUtil;

/**
 * @Author: 98050
 * @Time: 2018-10-25 18:17
 * @Feature: 登录拦截器
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 定义一个线程域，存放登录用户
	 */
	private static final ThreadLocal<User> t1 = new ThreadLocal<>();

	/**
	 * * 在业务处理器处理请求之前被调用 * 如果返回false * 则从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 *
	 * 如果返回true * 执行下一个拦截器，直到所有拦截器都执行完毕 * 再执行被拦截的Controller * 然后进入拦截器链 *
	 * 从最后一个拦截器往回执行所有的postHandle()
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod m = (HandlerMethod) handler;// 强转原来的类型
			UserToken an = m.getMethod().getAnnotation(UserToken.class);// 查看该方法有无权限注解
			if (an == null) {// 没有该注解，放行
				return true;
			}
		}
		// 1.查询token
		String token = CookieUtils.getCookieValue(request, SystemConstant.COOKIE_TOKEN);
		if (StringUtils.isEmpty(token)) {
			ResultVo<Object> vo = new ResultVo<Object>();
			vo.setErrorEnum(EnumSystem.NO_AUTH);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(JsonUtil.toJson(vo));
			return false;
		}
		// 3.有token，查询用户信息
		try {
			// 4.解析成功，说明已经登录
			User userInfo = TokenUtil.getUser(token);
			// 5.放入线程域
			t1.set(userInfo);
			return true;
		} catch (Exception e) {
			// 6.抛出异常，证明未登录，返回401
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return false;
		}
	}

	/**
	 * 在业务处理器处理请求执行完成后，生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		t1.remove();
	}

	public static User getUser() {
		return t1.get();
	}
}
