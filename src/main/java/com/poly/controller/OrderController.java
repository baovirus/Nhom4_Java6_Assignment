package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entity.Account;
import com.poly.entity.Category;
import com.poly.service.AccountService;
import com.poly.service.CategoryService;
import com.poly.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private OrderService orderService;

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

	@RequestMapping("/order/checkout")
	public String checkout(Model model, HttpServletRequest request) {
		addUserInfoToModel(model);

		String remoteUser = request.getRemoteUser(); // hoặc request.getUserPrincipal().getName()
		model.addAttribute("remoteUser", remoteUser);

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		return "order/checkout";
	}

	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		addUserInfoToModel(model);
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "order/list";
	}

	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		addUserInfoToModel(model);
		model.addAttribute("order", orderService.findById(id));
		return "order/detail";
	}
}
