package com.ydy.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Objects;
import com.ydy.base.vo.PageVo;
import com.ydy.base.vo.ResultVo;
import com.ydy.exception.BusinessException;
import com.ydy.interceptor.LoginInterceptor;
import com.ydy.order.client.UserClient;
import com.ydy.order.dto.OrderDTO;
import com.ydy.order.ienum.EnumOrder;
import com.ydy.order.mapper.OrderMapper;
import com.ydy.order.model.Order;
import com.ydy.order.service.OrderService;
import com.ydy.user.model.User;
import com.ydy.utils.JsonUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UserClient userClient;

	@Autowired
	private OrderMapper orderMapper;

	public Order selectById(Long id) {
		ResultVo<User> baseVo = userClient.selectById(LoginInterceptor.getUser().getId());// 这段其实在这里没有意义，只是做个demo
		System.out.println(JsonUtil.toJson(baseVo));
		Order order = orderMapper.selectByPrimaryKey(id);
		if (order == null) {
			throw new BusinessException(EnumOrder.ORDER_NOT_FOUND);
		}
		if (!Objects.equal(order.getUserId(), LoginInterceptor.getUser().getId())) {
			throw new BusinessException(EnumOrder.ORDER_NOT_FOUND);
		}
		return order;
	}

	@Override
	public Order create(Order order) {
		order.setId(System.currentTimeMillis());
		order.setUserId(LoginInterceptor.getUser().getId());
		order.setCreateTime(new Date());
		orderMapper.insertSelective(order);
		return order;
	}

	@Override
	public PageVo<Order> list(OrderDTO orderDTO, Integer page, Integer size) {
		PageVo<Order> pageVo = new PageVo<Order>(page, size);
		Page<Order> pageHelper = PageHelper.startPage(pageVo.getPage(), pageVo.getSize());
		List<Order> list = orderMapper.select(orderDTO);
		pageVo.setList(list);
		pageVo.setTotal(pageHelper.getTotal());
		return pageVo;
	}

}
