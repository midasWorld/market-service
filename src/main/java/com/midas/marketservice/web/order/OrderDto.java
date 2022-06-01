package com.midas.marketservice.web.order;

import static java.util.stream.Collectors.*;

import java.time.LocalDateTime;
import java.util.List;

import com.midas.marketservice.domain.order.Order;
import com.midas.marketservice.domain.order.OrderStatus;
import com.midas.marketservice.web.delivery.DeliveryDto;
import com.midas.marketservice.web.user.UserDto;

import lombok.Getter;

@Getter
public class OrderDto {

	private final Long id;
	private final LocalDateTime orderDate;
	private final OrderStatus status;
	private final List<OrderItemDto> orderItems;
	private final UserDto orderer;

	public OrderDto(Order order) {
		this.id = order.getId();
		this.orderDate = order.getOrderDate();
		this.status = order.getStatus();
		this.orderItems = order.getOrderItems().stream()
			.map(OrderItemDto::new)
			.collect(toList());
		this.orderer = new UserDto(order.getOrderer());
	}
}
