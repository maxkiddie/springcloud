package com.ydy.user.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "u_user.u_user")
public class User extends com.ydy.model.User {
	@Id
	private Long id;
	private String username;
	private String password;
	private Date birthday;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
