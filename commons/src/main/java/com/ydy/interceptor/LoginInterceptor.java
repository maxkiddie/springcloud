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
 * @Feature: ��¼������
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	/**
	 * ����һ���߳��򣬴�ŵ�¼�û�
	 */
	private static final ThreadLocal<User> t1 = new ThreadLocal<>();

	/**
	 * * ��ҵ��������������֮ǰ������ * �������false * ��ӵ�ǰ������������ִ��������������afterCompletion(),���˳��������� *
	 * �������true * ִ����һ����������ֱ��������������ִ����� * ��ִ�б����ص�Controller * Ȼ������������� *
	 * �����һ������������ִ�����е�postHandle()
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
			HandlerMethod m = (HandlerMethod) handler;// ǿתԭ��������
			UserToken an = m.getMethod().getAnnotation(UserToken.class);// �鿴�÷�������Ȩ��ע��
			if (an == null) {// û�и�ע�⣬����
				return true;
			}
		}
		// 1.��ѯtoken
		String token = CookieUtils.getCookieValue(request, SystemConstant.COOKIE_TOKEN);
		if (StringUtils.isEmpty(token)) {
			ResultVo<Object> vo = new ResultVo<Object>();
			vo.setErrorEnum(EnumSystem.NO_AUTH);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(JsonUtil.toJson(vo));
			return false;
		}
		// 3.��token����ѯ�û���Ϣ
		try {
			// 4.�����ɹ���˵���Ѿ���¼
			User userInfo = TokenUtil.getUser(token);
			// 5.�����߳���
			t1.set(userInfo);
			return true;
		} catch (Exception e) {
			// 6.�׳��쳣��֤��δ��¼������401
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return false;
		}
	}

	/**
	 * ��ҵ��������������ִ����ɺ�������ͼ֮ǰִ�еĶ��� ����modelAndView�м������ݣ����統ǰʱ��
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
	 * ��DispatcherServlet��ȫ����������󱻵���,������������Դ��
	 * �����������׳��쳣ʱ,��ӵ�ǰ����������ִ�����е���������afterCompletion()
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
