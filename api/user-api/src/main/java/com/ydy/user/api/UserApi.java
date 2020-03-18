package com.ydy.user.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ydy.base.vo.PageVo;
import com.ydy.base.vo.ResultVo;
import com.ydy.user.model.User;

public interface UserApi {

	@PostMapping("/login")
	ResultVo<User> login(@RequestParam("username") String username, @RequestParam("password") String password);

	@PostMapping("/saveOrUpdate")
	ResultVo<User> saveOrUpdate(@RequestBody User user);

	@GetMapping("/selectById")
	ResultVo<User> selectById(@RequestParam("id") Long id);

	@GetMapping("/list")
	ResultVo<PageVo<User>> list(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size);
}
