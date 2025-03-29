package com.poly.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Roles")
public class Role {
    @Id
    @Column(length = 10, nullable = false)
    private String id;

    @Column(length = 50, nullable = false)
    private String name;
}
