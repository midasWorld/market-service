package com.midas.marketservice.web.order;

import com.midas.marketservice.domain.item.Item;
import com.midas.marketservice.domain.order.OrderItem;

import lombok.Getter;

@Getter
public class OrderItemDto {

	private Long itemId;
	private String name;
	private long price;
	private final long qty;

	public OrderItemDto(OrderItem orderItem) {
		Item item = orderItem.getItem();
		this.itemId = item.getId();
		this.name = item.getName();
		this.price = item.getPrice();

		this.qty = orderItem.getQty();
	}
}
