package com.poly.service;

import java.util.List;
import com.poly.entity.Order;

public interface OrderService {
    List<Order> findAll();
    Order findById(Long id);
    Order save(Order order);
    void delete(Long id);
}
