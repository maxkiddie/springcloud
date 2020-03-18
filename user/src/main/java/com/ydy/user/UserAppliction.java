package com.ydy.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.ydy.base.mapper.BaseMapper;

import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = { "com.ydy" }, markerInterface = BaseMapper.class)
public class UserAppliction {
	public static void main(String[] args) {
		SpringApplication.run(UserAppliction.class, args);
	}
}
