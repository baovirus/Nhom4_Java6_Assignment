package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Account;
import com.poly.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {

	@Autowired
	AccountService accountService;

	@GetMapping
	public List<Account> getAll() {
		return accountService.findAll();
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Account account) {
		if (account.getUsername() == null || account.getUsername().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username không được để trống!");
		}

		if (accountService.existsById(account.getUsername())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Username đã tồn tại!");
		}

		try {
			Account created = accountService.save(account);
			return ResponseEntity.ok(created);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Lỗi khi tạo tài khoản: " + e.getMessage());
		}
	}

	@PutMapping("{username}")
	public Account update(@PathVariable("username") String username, @RequestBody Account account) {
		return accountService.save(account);
	}

	@DeleteMapping("{username}")
	public void delete(@PathVariable("username") String username) {
		accountService.delete(username);
	}
}
