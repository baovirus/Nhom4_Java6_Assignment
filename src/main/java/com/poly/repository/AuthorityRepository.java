package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;
import com.poly.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
	@Query("select distinct a from Authority a where a.account in ?1")
	List<Authority> authoritiesOf(List<Account> account);

}
