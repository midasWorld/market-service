package com.midas.marketservice.service.delivery;

import static com.google.common.base.Preconditions.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midas.marketservice.domain.delivery.Delivery;
import com.midas.marketservice.domain.delivery.DeliveryItem;
import com.midas.marketservice.domain.delivery.DeliveryItemRepository;
import com.midas.marketservice.domain.delivery.DeliveryQueryRepository;
import com.midas.marketservice.domain.delivery.DeliveryRepository;
import com.midas.marketservice.domain.delivery.ProductQueryDto;
import com.midas.marketservice.domain.item.Product;
import com.midas.marketservice.domain.order.Order;
import com.midas.marketservice.domain.user.User;
import com.midas.marketservice.error.NotFoundException;
import com.midas.marketservice.service.item.ProductService;
import com.midas.marketservice.service.order.OrderService;
import com.midas.marketservice.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DeliveryService {

	private final DeliveryRepository deliveryRepository;
	private final DeliveryItemRepository deliveryItemRepository;
	private final DeliveryQueryRepository deliveryQueryRepository;

	private final OrderService orderService;
	private final ProductService productService;
	private final UserService userService;

	public Delivery deliver(Long orderId, List<Product> products, Long userId) {
		checkArgument(!products.isEmpty(), "Products must be greater than zero.");

		Order order = orderService.findById(orderId)
			.orElseThrow(() -> new NotFoundException(Order.class, orderId));

		User deliveryPerson = userService.findById(userId)
			.orElseThrow(() -> new NotFoundException(User.class, userId));

		Delivery delivery = deliveryRepository.save(new Delivery(order, deliveryPerson));

		products.forEach(product -> {
			productService.removeStock(product.getId());
			DeliveryItem deliveryItem = deliveryItemRepository.save(new DeliveryItem(delivery, product));
			delivery.addDeliveryItem(deliveryItem);
		});

		return delivery;
	}

	public Product findProductByBarcode(String barcode) {
		return productService.findByBarcode(barcode)
			.orElseThrow(() -> new NotFoundException(Product.class, barcode));
	}

	public ProductQueryDto findProducDtoByBarcode(String barcode) {
		return deliveryQueryRepository.findOne(barcode);
	}

	public boolean findExistsProduct(String barcode) {
		return productService.findExists(barcode);
	}

	public void complete() {

	}

	public void cancel() {

	}
}
