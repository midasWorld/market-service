package com.midas.marketservice.domain.delivery;

import lombok.Getter;

@Getter
public class ProductQueryDto {
	private Long id;
	private String barcode;
	private Long itemId;
	private long qty;

	public ProductQueryDto(Long id, String barcode, Long itemId, long qty) {
		this.id = id;
		this.barcode = barcode;
		this.itemId = itemId;
		this.qty = qty;
	}
}
