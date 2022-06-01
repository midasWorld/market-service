package com.midas.marketservice.web.item;

import java.time.LocalDateTime;

import com.midas.marketservice.domain.item.Item;
import com.midas.marketservice.domain.item.ItemGroup;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ItemDto {

	private final String name;
	private final long unitPrice;
	private final long price;
	private final long stockQty;
	private final LocalDateTime expiryDate;
	private final ItemGroup group;

	public ItemDto(Item item) {
		this.name = item.getName();
		this.unitPrice = item.getUnitPrice();
		this.price = item.getPrice();
		this.stockQty = item.getStockQty();
		this.expiryDate = item.getExpiryDate();
		this.group = item.getGroup();
	}
}
