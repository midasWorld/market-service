package com.midas.marketservice.domain.order;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("select o from Order o join fetch o.orderItems ois join fetch ois.item join fetch o.orderer")
	List<Order> findAll();

	@Query("select o from Order o join fetch OrderItem ot where o.id = :id")
	Optional<Order> findById(Long id);
}
