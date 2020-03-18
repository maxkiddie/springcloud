package com.ydy.auths.client.fallback;

import org.springframework.stereotype.Component;

import com.ydy.auths.client.UserClient;
import com.ydy.base.vo.PageVo;
import com.ydy.base.vo.ResultVo;
import com.ydy.ienum.EnumSystem;
import com.ydy.user.model.User;

@Component
public class UserFallback implements UserClient {

	@Override
	public ResultVo<User> login(String username, String password) {
		ResultVo<User> vo = new ResultVo<User>();
		vo.setErrorEnum(EnumSystem.REMOTE_IP_ERROR);
		return vo;
	}

	@Override
	public ResultVo<User> selectById(Long id) {
		return null;
	}


	@Override
	public ResultVo<User> saveOrUpdate(User user) {
		return null;
	}

	@Override
	public ResultVo<PageVo<User>> list(String username, Integer page, Integer size) {
		return null;
	}

}
