package com.ydy.user.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import com.ydy.base.vo.PageVo;
import com.ydy.base.vo.ResultVo;
import com.ydy.ctrl.BaseCtrl;
import com.ydy.user.api.UserApi;
import com.ydy.user.model.User;
import com.ydy.user.service.UserService;

@RestController
@RefreshScope
public class UserCtrl extends BaseCtrl implements UserApi {

	@Autowired
	private UserService userService;

	@Override
	public ResultVo<User> login(String username, String password) {
		return buildBaseVo(userService.login(username, password));
	}

	@Override
	public ResultVo<User> saveOrUpdate(User user) {
		return buildBaseVo(userService.saveOrUpdate(user));
	}

	@Override
	public ResultVo<User> selectById(Long id) {
		return buildBaseVo(userService.selectById(id));
	}

	@Override
	public ResultVo<PageVo<User>> list(String username, Integer page, Integer size) {
		return buildBaseVo(userService.list(username, page, size));
	}

}
