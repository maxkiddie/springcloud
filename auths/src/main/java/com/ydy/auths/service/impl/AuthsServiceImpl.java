package com.ydy.auths.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ydy.auths.client.UserClient;
import com.ydy.auths.service.AuthsService;
import com.ydy.base.vo.ResultVo;
import com.ydy.exception.RemoteApiException;
import com.ydy.user.model.User;
import com.ydy.utils.TokenUtil;

@Service
public class AuthsServiceImpl implements AuthsService {

	@Autowired
	private UserClient userClient;

	@Override
	public String createUserToken(String username, String password) {
		ResultVo<User> vo = userClient.login(username, password);
		if (!vo.isSus()) {
			throw new RemoteApiException(vo);
		}
		return TokenUtil.createUserToken(vo.getData());
	}

}
