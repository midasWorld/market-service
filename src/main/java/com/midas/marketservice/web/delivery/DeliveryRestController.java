package com.midas.marketservice.web.delivery;

import static com.midas.marketservice.web.ApiResponse.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.marketservice.domain.delivery.ProductQueryDto;
import com.midas.marketservice.service.delivery.DeliveryService;
import com.midas.marketservice.web.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class DeliveryRestController {

	private final DeliveryService deliveryService;

	@GetMapping("/v1/deliveries/product")
	public ApiResponse<ProductDto> findProduct(@RequestBody ScanRequest request) {
		return OK(
			new ProductDto(
				deliveryService.findProductByBarcode(request.getBarcode())
			)
		);
	}

	@GetMapping("/v2/deliveries/product")
	public ApiResponse<ProductQueryDto> findProductV2(@RequestBody ScanRequest request) {
		return OK(
			deliveryService.findProducDtoByBarcode(request.getBarcode())
		);
	}

	@GetMapping("/v3/deliveries/product")
	public ApiResponse<Boolean> findProductV3(@RequestBody ScanRequest request) {
		return OK(
			deliveryService.findExistsProduct(request.getBarcode())
		);
	}
}
