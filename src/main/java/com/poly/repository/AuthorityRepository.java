package com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
