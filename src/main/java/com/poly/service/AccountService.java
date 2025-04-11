package com.poly.service;

import java.util.List;
import java.util.Optional;

import com.poly.entity.Account;
import com.poly.security.CustomUserDetails;

public interface AccountService {
	List<Account> findAll();

	CustomUserDetails findById(String username);

	Optional<Account> findByUsername(String username);

	Account findAccountById(String username);

	Account save(Account account);

	void delete(String username);

	List<Account> getAdministrators();
}
