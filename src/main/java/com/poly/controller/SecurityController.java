package com.poly.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entity.Account;
import com.poly.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private void addUserInfoToModel(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			String username = userDetails.getUsername(); // Lấy username từ UserDetails

			// Lấy thông tin Account từ database
			Optional<Account> accountOptional = accountService.findByUsername(username);
			if (accountOptional.isPresent()) {
				Account account = accountOptional.get();
				model.addAttribute("user", account); // Có thể truy xuất ${user.fullname}, ${user.email}, ...
				model.addAttribute("name", account.getFullname()); // Gửi fullname tới Thymeleaf (ví dụ ở navbar)
			}
		}
	}

	@RequestMapping("/login")
	public String login() {
		return ("account/login");
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login?logout";
	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new Account());
		return "account/register";
	}

	@PostMapping("/register")
	public String processRegister(@ModelAttribute("user") Account user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "account/register";
		}

		try {
			user.setFullname("Anonymous");
			user.setPhoto("user.png");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			accountService.save(user);
		} catch (RuntimeException e) {
			model.addAttribute("error", "Tạo tài khoản thất bại!");
			return "account/register";
		}

		return "redirect:/register?success";
	}

	@RequestMapping("/forgot-password")
	public String forgot_password() {
		return ("account/forgot-password");
	}

	@RequestMapping("/profile")
	public String profile(Model model) {
		addUserInfoToModel(model);
		return ("account/profile");
	}

}
