package com.midas.marketservice.web.order;

import static com.midas.marketservice.web.ApiResponse.*;
import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.marketservice.service.order.OrderService;
import com.midas.marketservice.web.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@RestController
public class OrderRestController {

	private final OrderService orderService;

	@PostMapping
	public ApiResponse<OrderDto> create(@RequestBody OrderRequest request) {
		return OK(
			new OrderDto(
				orderService.create(request.getOrdererId(), request.getItemsToMap())
			)
		);
	}

	@GetMapping
	public ApiResponse<List<OrderDto>> findAll() {
		return OK(
			orderService.findAll().stream()
				.map(OrderDto::new)
				.collect(toList())
		);
	}
}
