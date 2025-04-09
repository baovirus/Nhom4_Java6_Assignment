package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("Select o from Order o Where o.account.username=?1")
	List<Order> findByUsername(String username); // ID là Long
}
