package com.poly.service;

import java.util.List;

import com.poly.entity.Account;
import com.poly.security.CustomUserDetails;

public interface AccountService {
	List<Account> findAll();

	CustomUserDetails findById(String username);

	Account save(Account account);

	void delete(String username);
}
