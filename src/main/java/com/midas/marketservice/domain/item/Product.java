package com.midas.marketservice.domain.item;

import static java.util.UUID.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.midas.marketservice.domain.BaseEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
@Entity
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String barcode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@NotNull
	@Min(value = 0)
	private long qty;

	public Product(Item item, long qty) {
		this(randomUUID().toString(), item, qty);
	}

	public Product(String barcode, Item item, long qty) {
		this.barcode = barcode;
		this.item = item;
		this.qty = qty;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("barcode", barcode)
			.append("item", item)
			.append("qty", qty)
			.append("createdAt", createdAt)
			.append("createdBy", createdBy)
			.append("modifiedAt", modifiedAt)
			.append("modifiedBy", modifiedBy)
			.toString();
	}
}
