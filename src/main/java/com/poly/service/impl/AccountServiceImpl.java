package com.poly.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entity.Account;
import com.poly.repository.AccountRepository;
import com.poly.security.CustomUserDetails;
import com.poly.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Optional<Account> findByUsername(String username) {
		return accountRepository.findById(username); // username là @Id
	}

	@Override
	public CustomUserDetails findById(String username) {
		Optional<Account> accountOpt = accountRepository.findById(username);
		if (accountOpt.isPresent()) {
			Account account = accountOpt.get();
			return new CustomUserDetails(account.getUsername(), account.getPassword(), new ArrayList<>() // Cung cấp
																											// danh sách
																											// quyền nếu
																											// cần
			);
		}
		return null; // Hoặc ném ngoại lệ nếu không tìm thấy
	}

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public void delete(String username) {
		accountRepository.deleteById(username);
	}
}
