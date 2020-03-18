package com.ydy.order.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydy.annotation.UserToken;
import com.ydy.base.vo.PageVo;
import com.ydy.base.vo.ResultVo;
import com.ydy.ctrl.BaseCtrl;
import com.ydy.order.api.OrderApi;
import com.ydy.order.dto.OrderDTO;
import com.ydy.order.model.Order;
import com.ydy.order.service.OrderService;

@RestController
public class OrderCtrl extends BaseCtrl implements OrderApi {

	@Autowired
	private OrderService orderService;

	@UserToken
	@Override
	public ResultVo<Order> selectById(Long id) {
		return buildBaseVo(orderService.selectById(id));
	}

	@UserToken
	@Override
	public ResultVo<Order> create(Order order) {
		return buildBaseVo(orderService.create(order));
	}

	// 这个接口不需要给其他服务使用
	@GetMapping("/list")
	public ResultVo<PageVo<Order>> list(OrderDTO orderDTO, Integer page, Integer size) {
		return buildBaseVo(orderService.list(orderDTO, page, size));
	}
}
