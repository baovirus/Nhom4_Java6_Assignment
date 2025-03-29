package com.poly.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Categories")
public class Category {
    @Id
    @Column(length = 4, nullable = false)
    private String id;

    @Column(length = 50, nullable = false)
    private String name;
}
