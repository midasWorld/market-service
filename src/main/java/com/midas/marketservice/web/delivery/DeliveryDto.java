package com.midas.marketservice.web.delivery;

import static java.util.stream.Collectors.*;

import java.time.LocalDateTime;
import java.util.List;

import com.midas.marketservice.domain.delivery.Delivery;
import com.midas.marketservice.domain.delivery.DeliveryStatus;
import com.midas.marketservice.web.user.UserDto;

import lombok.Getter;

@Getter
public class DeliveryDto {

	private LocalDateTime deliveryDate;
	private DeliveryStatus status;
	private List<DeliveryItemDto> deliveryItems;
	private UserDto deliveryPerson;

	public DeliveryDto(Delivery delivery) {
		this.deliveryDate = delivery.getDeliveryDate();
		this.status = delivery.getStatus();
		this.deliveryItems = delivery.getDeliveryItems().stream()
			.map(DeliveryItemDto::new)
			.collect(toList());
		this.deliveryPerson = new UserDto(delivery.getDeliveryPerson());
	}
}
