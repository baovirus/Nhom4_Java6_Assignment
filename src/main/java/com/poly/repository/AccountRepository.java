package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
	@Query("select distinct ar.account from Authority ar where ar.role.id in ('DIRE', 'STAF')")
	List<Account> getAdministrators();
}
