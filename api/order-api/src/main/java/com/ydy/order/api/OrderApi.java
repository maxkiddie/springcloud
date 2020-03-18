package com.ydy.order.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ydy.base.vo.ResultVo;
import com.ydy.order.model.Order;

public interface OrderApi {
	@GetMapping("/selectById")
	ResultVo<Order> selectById(Long id);

	@PostMapping("/create")
	ResultVo<Order> create(@RequestBody Order order);

}
