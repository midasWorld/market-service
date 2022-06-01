package com.midas.marketservice.service.order;

import static com.google.common.base.Preconditions.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midas.marketservice.domain.item.Item;
import com.midas.marketservice.domain.order.Order;
import com.midas.marketservice.domain.order.OrderItem;
import com.midas.marketservice.domain.order.OrderItemRepository;
import com.midas.marketservice.domain.order.OrderRepository;
import com.midas.marketservice.domain.user.User;
import com.midas.marketservice.error.NotFoundException;
import com.midas.marketservice.service.item.ItemService;
import com.midas.marketservice.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;

	private final UserService userService;
	private final ItemService itemService;

	@Transactional
	public Order create(Long ordererId, Map<Long, Long> items) {
		checkArgument(!items.isEmpty(), "Item must be greater than zero.");

		User orderer = userService.findById(ordererId)
			.orElseThrow(() -> new NotFoundException(User.class, ordererId));

		Order order = orderRepository.save(new Order(orderer));
		items.forEach((itemId, requestQty) -> {
			Item item = itemService.findById(itemId)
				.orElseThrow(() -> new NotFoundException(Item.class, itemId));

			item.checkOverStock(requestQty);

			OrderItem orderItem = orderItemRepository.save(new OrderItem(order, item, requestQty));
			order.addOrderItem(orderItem);
		});

		return order;
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Optional<Order> findById(Long id) {
		checkArgument(id != null, "orderId must be provided.");

		return orderRepository.findById(id);
	}
}
