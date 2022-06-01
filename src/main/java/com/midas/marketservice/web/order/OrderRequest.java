package com.midas.marketservice.web.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderRequest {
	private final Long ordererId;
	private final List<OrderItemRequest> items;

	public OrderRequest(Long ordererId, List<OrderItemRequest> items) {
		this.ordererId = ordererId;
		this.items = items;
	}

	public Map<Long, Long> getItemsToMap() {
		Map<Long, Long> map = new HashMap<>();
		items.forEach(item -> map.put(item.getItemId(), item.getQty()));
		return map;
	}

	@Getter @Setter
	static class OrderItemRequest {
		private Long itemId;
		private long qty;
	}
}
