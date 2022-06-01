package com.midas.marketservice.web.delivery;

import com.midas.marketservice.domain.item.Product;

import lombok.Getter;

@Getter
public class ProductDto {

	private Long id;
	private String barcode;
	private Long itemId;
	private long qty;

	public ProductDto(Product product) {
		this.id = product.getId();
		this.barcode = product.getBarcode();
		this.itemId = product.getItem().getId();
		this.qty = product.getQty();
	}
}
