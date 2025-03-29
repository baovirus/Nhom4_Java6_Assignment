package com.poly.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Username", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "RoleId", nullable = false)
    private Role role;
}
