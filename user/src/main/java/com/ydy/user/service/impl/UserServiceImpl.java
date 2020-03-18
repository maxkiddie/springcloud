package com.ydy.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Objects;
import com.ydy.base.vo.PageVo;
import com.ydy.exception.BusinessException;
import com.ydy.user.ienum.EnumUser;
import com.ydy.user.mapper.UserMapper;
import com.ydy.user.model.User;
import com.ydy.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(String username, String password) {
		User user = new User();
		user.setUsername(username);
		PageHelper.startPage(1, 1, false);
		user = userMapper.selectOne(user);
		if (user == null) {
			throw new BusinessException(EnumUser.USER_NAME_NO_EXSIT);
		}
		if (!Objects.equal(password, user.getPassword())) {
			throw new BusinessException(EnumUser.PW_ERROR);
		}
		return user;
	}

	@Override
	public User saveOrUpdate(User user) {
		if (user.getId() == null) {
			userMapper.insertSelective(user);
		} else {
			userMapper.updateByPrimaryKeySelective(user);
		}
		return user;
	}

	@Override
	public User selectById(Long id) {
		User user = userMapper.selectByPrimaryKey(id);
		if (user == null) {
			throw new BusinessException(EnumUser.USER_NOT_FOUND);
		}
		return user;
	}

	@Override
	public PageVo<User> list(String username, Integer page, Integer size) {
		PageVo<User> pageVo = new PageVo<User>(page, size);
		Page<User> pageHelper = PageHelper.startPage(pageVo.getPage(), pageVo.getSize());
		User record = new User();
		record.setUsername(username);
		List<User> list = userMapper.select(record);
		pageVo.setList(list);
		pageVo.setTotal(pageHelper.getTotal());
		return pageVo;
	}

}
