package com.midas.marketservice.config;

import javax.persistence.EntityManager;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

import com.midas.marketservice.util.MessageUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class ServiceConfig {

	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}

	@Bean
	public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
		MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(messageSource);
		MessageUtils.setMessageSourceAccessor(messageSourceAccessor);
		return messageSourceAccessor;
	}
}
