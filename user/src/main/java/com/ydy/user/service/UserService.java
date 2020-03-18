package com.ydy.user.service;

import com.ydy.base.vo.PageVo;
import com.ydy.user.model.User;

public interface UserService {

	User login(String username, String password);

	User saveOrUpdate(User user);

	User selectById(Long id);

	PageVo<User> list(String username, Integer page, Integer size);
}
