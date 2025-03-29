package com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> { // ID là Long
}
