package com.midas.marketservice.web.delivery;

import com.midas.marketservice.domain.delivery.DeliveryItem;

import lombok.Getter;

@Getter
public class DeliveryItemDto {

	private String name;
	private String barcode;
	private long qty;

	public DeliveryItemDto(DeliveryItem deliveryItem) {
		this.name = deliveryItem.getProduct().getItem().getName();
		this.barcode = deliveryItem.getProduct().getBarcode();
		this.qty = deliveryItem.getProduct().getQty();
	}
}
