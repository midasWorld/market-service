package com.midas.marketservice.domain.order;

import static com.google.common.base.Preconditions.*;
import static com.midas.marketservice.domain.order.OrderStatus.*;
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
import com.midas.marketservice.domain.delivery.Delivery;
import com.midas.marketservice.domain.user.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderer_id")
	private User orderer;

	@OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
	private Delivery delivery;

	public Order(User orderer) {
		this(null, null, orderer);
	}

	public Order(LocalDateTime orderDate, OrderStatus status, User orderer) {
		this.orderDate = defaultIfNull(orderDate, now());
		this.status = defaultIfNull(status, ACCEPTED);
		this.orderer = orderer;
	}

	//== 비지니스 로직 ==//
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
	}

	public void changeStatus(OrderStatus status) {
		checkArgument(status != null, "OrderStatus must be provided.");

		this.status = status;
	}

	public void updateDelivery(Delivery delivery) {
		checkArgument(delivery != null, "delivery must be provided.");

		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("orderDate", orderDate)
			.append("status", status)
			.append("orderItems", orderItems)
			.append("createdAt", createdAt)
			.append("createdBy", createdBy)
			.append("modifiedAt", modifiedAt)
			.append("modifiedBy", modifiedBy)
			.toString();
	}
}
