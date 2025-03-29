package com.poly.service;

import java.util.List;
import com.poly.entity.OrderDetail;

public interface OrderDetailService {
    List<OrderDetail> findAll();
    OrderDetail findById(Long id);
    OrderDetail save(OrderDetail orderDetail);
    void delete(Long id);
}
