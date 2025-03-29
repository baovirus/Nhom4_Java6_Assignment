package com.poly.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Accounts")
public class Account {
    @Id
    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String fullname;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String photo;
}
