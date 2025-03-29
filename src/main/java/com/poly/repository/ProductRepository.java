package com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> { // ID là Integer
}
