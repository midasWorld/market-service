package com.midas.marketservice.domain.delivery;

import static com.midas.marketservice.domain.item.QProduct.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Repository
public class DeliveryQueryRepository {

	private final JPAQueryFactory queryFactory;

	public ProductQueryDto findOne(String barcode) {
		return queryFactory
			.select(Projections.constructor(ProductQueryDto.class,
					product.id,
					product.barcode,
					product.item.id,
					product.qty))
			.from(product)
			.where(product.barcode.eq(barcode))
			.fetchFirst();
	}

	public Boolean findExistsProductV2(String barcode) {
		Integer exists = queryFactory
			.selectOne()
			.from(product)
			.where(
				product.barcode.eq(barcode)
			)
			.fetchFirst();

		return exists != null;
	}
}
