package com.midas.marketservice.service.item;

import static com.google.common.base.Preconditions.*;
import static com.midas.marketservice.domain.item.QProduct.*;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midas.marketservice.domain.item.Product;
import com.midas.marketservice.domain.item.ProductRepository;
import com.midas.marketservice.error.NotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

	private final ProductRepository productRepository;

	private final ItemService itemService;

	@Transactional
	public void removeStock(Long id) {
		checkArgument(id != null, "id must be provided.");

		Product product = productRepository.findById(id)
			.orElseThrow(() -> new NotFoundException(Product.class, id));

		itemService.removeStock(product.getItem().getId(), product.getQty());
		productRepository.delete(product);
	}

	public Optional<Product> findByBarcode(String barcode) {
		return productRepository.findByBarcode(barcode);
	}

	public Boolean findExists(String barcode) {
		return productRepository.exists(product.barcode.eq(barcode));
	}
}
