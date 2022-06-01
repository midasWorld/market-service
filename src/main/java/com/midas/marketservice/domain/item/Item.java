package com.midas.marketservice.domain.item;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.DynamicUpdate;

import com.midas.marketservice.domain.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item")
@Entity
@DynamicUpdate
public class Item extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 100)
	private String name;

	@NotNull
	@Min(value = 0)
	private long unitPrice;

	@NotNull
	@Min(value = 0)
	private long price;

	@NotNull
	@Min(value = 0)
	private long stockQty;

	private LocalDateTime expiryDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "item_group")
	private ItemGroup group;

	@Builder
	public Item(String name, long unitPrice, long price, long stockQty, LocalDateTime expiryDate, ItemGroup group) {
		this(null, name, unitPrice, price, stockQty, expiryDate, group);
	}

	public Item(Long id, String name, long unitPrice, long price, long stockQty, LocalDateTime expiryDate,
		ItemGroup group) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.price = price;
		this.stockQty = stockQty;
		this.expiryDate = expiryDate;
		this.group = group;
	}

	//== 비지니스 로직 ==//
	public void updateItemInfo(Item item) {
		this.name = item.getName();
		this.unitPrice = item.getUnitPrice();
		this.price = item.getPrice();
		this.stockQty = item.getStockQty();
		this.expiryDate = item.getExpiryDate();
		this.group = item.getGroup();
	}

	public void addStock(long qty) {
		this.stockQty += qty;
	}

	public void removeStock(long qty) {
		long restStock = this.stockQty - qty;
		if (restStock < 0) {
			throw new IllegalArgumentException("Quantity exceeds the available stock on hand.");
		}
		this.stockQty = restStock;
	}

	public void checkOverStock(long qty) {
		long restStock = this.stockQty - qty;
		if (restStock < 0) {
			throw new IllegalArgumentException("Quantity exceeds the available stock on hand.");
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("name", name)
			.append("unitPrice", unitPrice)
			.append("price", price)
			.append("stockQty", stockQty)
			.append("expiryDate", expiryDate)
			.append("group", group)
			.append("createdAt", createdAt)
			.append("createdBy", createdBy)
			.append("modifiedAt", modifiedAt)
			.append("modifiedBy", modifiedBy)
			.toString();
	}
}
