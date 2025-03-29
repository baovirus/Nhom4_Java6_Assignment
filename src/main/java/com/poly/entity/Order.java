package com.poly.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Username", nullable = false)
    private Account account;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createDate;

    @Column(length = 100, nullable = false)
    private String address;
}
