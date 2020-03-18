package com.ydy.order.service;

import com.ydy.base.vo.PageVo;
import com.ydy.order.dto.OrderDTO;
import com.ydy.order.model.Order;

public interface OrderService {

	Order selectById(Long id);

	Order create(Order order);

	PageVo<Order> list(OrderDTO orderDTO, Integer page, Integer size);
}
