package com.poly.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
	public List<Account> getAdministrators() {
		return accountRepository.getAdministrators();
	}

	@Override
	public Optional<Account> findByUsername(String username) {
		return accountRepository.findById(username);
	}

	@Override
	public CustomUserDetails findById(String username) {
		Optional<Account> accountOpt = accountRepository.findById(username);
		if (accountOpt.isPresent()) {
			Account account = accountOpt.get();

			// ⚠️ Chuyển authorities sang dạng Spring hiểu được
			List<GrantedAuthority> authorities = account.getAuthorities().stream()
					.map(auth -> new SimpleGrantedAuthority(auth.getRole().getId())) // Ví dụ: "STAF", "DIRE"
					.collect(Collectors.toList());

			return new CustomUserDetails(account.getUsername(), account.getPassword(), authorities);
		}
		return null;
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
