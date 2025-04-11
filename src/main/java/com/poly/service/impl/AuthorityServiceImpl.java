package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.repository.AccountRepository;
import com.poly.repository.AuthorityRepository;
import com.poly.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Authority> findAll() {
		return authorityRepository.findAll();
	}

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> account = accountRepository.getAdministrators();
		return authorityRepository.authoritiesOf(account);
	}

	@Override
	public Authority findById(Integer id) {
		return authorityRepository.findById(id).orElse(null);
	}

	@Override
	public Authority save(Authority authority) {
		return authorityRepository.save(authority);
	}

	@Override
	public void delete(Integer id) {
		authorityRepository.deleteById(id);
	}
}
