package com.midas.marketservice.domain.delivery;

import static com.google.common.base.Preconditions.*;
import static com.midas.marketservice.domain.delivery.DeliveryStatus.*;
import static java.time.LocalDateTime.*;
import static org.apache.commons.lang3.ObjectUtils.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.midas.marketservice.domain.BaseEntity;
import com.midas.marketservice.domain.order.Order;
import com.midas.marketservice.domain.user.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "delivery")
@Entity
public class Delivery extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@NotNull
	private LocalDateTime deliveryDate = now();

	@OneToMany(mappedBy = "delivery")
	private List<DeliveryItem> deliveryItems = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delvery_person_id")
	private User deliveryPerson;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	public Delivery(Order order, User deliveryPerson) {
		this(order, null, deliveryPerson, null);
	}

	public Delivery(Order order, LocalDateTime deliveryDate, User deliveryPerson, DeliveryStatus status) {
		this.order = order;
		this.deliveryDate = defaultIfNull(deliveryDate, now());
		this.deliveryPerson = deliveryPerson;
		this.status = defaultIfNull(status, DELIVERING);
	}

	//== 비지니스 로직 ==//
	public void changeStatus(DeliveryStatus status) {
		checkArgument(status != null, "status must be provided.");

		this.status = status;
	}

	public void addDeliveryItem(DeliveryItem deliveryItem) {
		this.deliveryItems.add(deliveryItem);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("order", order)
			.append("deliveryDate", deliveryDate)
			.append("deliveryPerson", deliveryPerson)
			.append("status", status)
			.toString();
	}
}
