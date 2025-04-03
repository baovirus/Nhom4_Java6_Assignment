package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

	@RequestMapping("/login")
	public String login() {
		return ("account/login");
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login";
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
			accountService.save(user);
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "account/register";
		}

		return "redirect:/register?success";
	}

	@RequestMapping("/forgot-password")
	public String forgot_password() {
		return ("account/forgot-password");
	}

	@RequestMapping("/profile")
	public String profile() {
		return ("account/profile");
	}

}
