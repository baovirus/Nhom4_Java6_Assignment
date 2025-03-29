package com.poly.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String image;

    @Column(nullable = false)
    private Double price;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date createDate;

    @Column(nullable = false)
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "CategoryId", nullable = false)
    private Category category;
}
