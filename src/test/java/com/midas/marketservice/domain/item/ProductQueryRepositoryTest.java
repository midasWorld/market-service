package com.midas.marketservice.domain.item;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ProductQueryRepositoryTest {

	@Autowired
	ProductQueryRepository productQueryRepository;

	@Test
	@Order(1)
	void 문제_묵시적_CROSS_조인_테스트() {
		List<Product> products = productQueryRepository.findByItemName("새우깡");
	}

	@Test
	@Order(2)
	void 해결_명시적_조인_테스트() {
		List<Product> products = productQueryRepository.findByItemNameV2("새우깡");
	}
}