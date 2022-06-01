package com.midas.marketservice.web.item;

import java.time.LocalDateTime;

import com.midas.marketservice.domain.item.Item;
import com.midas.marketservice.domain.item.ItemGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ItemRequest {

	private Long id;
	private String name;
	private long unitPrice;
	private long price;
	private long stockQty;
	private LocalDateTime expiryDate;
	private ItemGroup group;

	public Item toEntity() {
		return new Item(id, name, unitPrice, price, stockQty, expiryDate, group);
	}
}