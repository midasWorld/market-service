package com.midas.marketservice.web.item;

import static com.midas.marketservice.web.ApiResponse.*;
import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.marketservice.service.item.ItemService;
import com.midas.marketservice.web.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
@RestController
public class ItemRestController {

	private final ItemService itemService;

	@PostMapping
	public ApiResponse<ItemDto> save(@RequestBody ItemRequest request) {
		return OK(
			new ItemDto(
				itemService.create(request.toEntity())
			)
		);
	}

	@PatchMapping
	public ApiResponse<ItemDto> modify(@RequestBody ItemRequest request) {
		return OK(
			new ItemDto(
				itemService.modify(request.toEntity())
			)
		);
	}

	@GetMapping
	public ApiResponse<List<ItemDto>> findAll() {
		return OK(
			itemService.findAll().stream()
				.map(ItemDto::new)
				.collect(toList())
		);
	}
}
