package com.ydy.auths.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydy.auths.service.AuthsService;
import com.ydy.base.vo.ResultVo;
import com.ydy.constant.SystemConstant;
import com.ydy.ctrl.BaseCtrl;

@RestController
public class AuthsCtrl extends BaseCtrl {
	@Autowired
	private AuthsService authsService;

	@GetMapping("/userAuth")
	public ResultVo<String> userAuth(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
		String token = authsService.createUserToken(username, password);
		setCookie(SystemConstant.COOKIE_TOKEN, token, request, response);
		return buildBaseVo(token);
	}
}
