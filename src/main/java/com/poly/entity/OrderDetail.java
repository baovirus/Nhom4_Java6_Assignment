package com.poly.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "OrderDetails")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OrderId", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;
}
