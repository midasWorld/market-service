package com.midas.marketservice.domain.item;

import static com.midas.marketservice.domain.item.QProduct.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProductQueryRepository {

	private final JPAQueryFactory queryFactory;

	public List<Product> findByItemName(String itemName) {
		return queryFactory
			.selectFrom(product)
			.where(product.item.name.eq(itemName))
			.fetch();
	}

	public List<Product> findByItemNameV2(String itemName) {
		return queryFactory
			.selectFrom(product)
			.innerJoin(product.item)
			.where(product.item.name.eq(itemName))
			.fetch();
	}
}
