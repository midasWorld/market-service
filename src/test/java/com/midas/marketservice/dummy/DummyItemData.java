package com.midas.marketservice.dummy;

import static com.midas.marketservice.domain.item.ItemGroup.*;
import static java.time.LocalDateTime.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.midas.marketservice.domain.item.Item;
import com.midas.marketservice.domain.item.ItemRepository;
import com.midas.marketservice.domain.item.Product;
import com.midas.marketservice.domain.item.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class DummyItemData {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ProductRepository productRepository;

	@Test
	@Transactional
	@Commit
	@Order(1)
	void dummyItem() {
		List<Item> items = new ArrayList<>();

		items.add(new Item("새우깡", 110, 1100, 90, now().plusMonths(1), FOOD));
		items.add(new Item("포카칩", 50, 1200, 20, now().plusMonths(1), FOOD));
		items.add(new Item("오징어집", 10, 1300, 30, now().plusMonths(1), FOOD));
		items.add(new Item("오징어땅콩", 60, 1400, 40, now().plusMonths(1), FOOD));
		items.add(new Item("다이제", 100, 1500, 50, now().plusMonths(1), FOOD));
		items.add(new Item("초코파이", 70, 1600, 60, now().plusMonths(1), FOOD));

		items.add(new Item("모나미", 100, 1700, 70, now().plusMonths(1), STATIONERY));
		items.add(new Item("펜텔 스매쉬", 5000, 15000, 80, now().plusMonths(1), STATIONERY));
		items.add(new Item("펜텔 그래프", 6000, 12000, 90, now().plusMonths(1), STATIONERY));
		items.add(new Item("연습장", 50, 1100, 100, now().plusMonths(1), STATIONERY));

		items.add(new Item("셔츠", 1000, 44000, 110, null, CLOTHES));
		items.add(new Item("T셔츠", 100, 20000, 120, null, CLOTHES));
		items.add(new Item("면바지", 3000, 64000, 130, null, CLOTHES));
		items.add(new Item("청바지", 2100, 34000, 140, null, CLOTHES));

		for (Item item : items) {
			itemRepository.save(item);
			log.info("Created item: {}", item);
		}
	}

	@Test
	@Transactional
	@Commit
	@Order(2)
	void dummyProduct() {
		int count = 7_000_000;

		List<Item> items = itemRepository.findAll();

		for (int i = 0; i < count; i++) {
			int randomNo = getRandomNum(0, items.size());
			Item item = items.get(randomNo);
			Product product = new Product(item, 1);
			productRepository.save(product);
		}
	}

	private int getRandomNum(int min, int max) {
		Random random = new Random();
		return (int)((Math.random() * (max - min)) + min);
	}
}
