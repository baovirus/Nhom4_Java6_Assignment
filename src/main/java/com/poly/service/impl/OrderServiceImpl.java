package com.poly.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.repository.OrderDetailRepository;
import com.poly.repository.OrderRepository;
import com.poly.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public List<Order> findByUsername(String username) {
		return orderRepository.findByUsername(username);
	}

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order create(JsonNode orderData) {
		try {
			System.out.println("Đã gọi hàm create");
			ObjectMapper mapper = new ObjectMapper();

			// Chuyển đổi dữ liệu order từ JSON sang entity
			Order order = mapper.convertValue(orderData, Order.class);
			orderRepository.save(order); // Lưu và lấy ID

			// Lấy danh sách OrderDetail từ JSON và gắn đơn hàng vào từng chi tiết
			TypeReference<List<OrderDetail>> type = new TypeReference<>() {
			};
			List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type).stream()
					.peek(d -> d.setOrder(order)).collect(Collectors.toList());

			// Lưu tất cả chi tiết
			orderDetailRepository.saveAll(details);

			return order;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Lỗi khi tạo đơn hàng", e);
		}
	}

	@Override
	public void delete(Long id) {
		orderRepository.deleteById(id);
	}
}
