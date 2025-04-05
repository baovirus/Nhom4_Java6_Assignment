package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entity.Account;
import com.poly.entity.Category;
import com.poly.service.AccountService;
import com.poly.service.CategoryService;

@Controller
public class ShoppingCartController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AccountService accountService;

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

	@RequestMapping("/my-cart")
	public String my_cart(Model model) {
		addUserInfoToModel(model);

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		return ("cart/my-cart");
	}

	@RequestMapping("/my-order")
	public String my_order(Model model) {
		addUserInfoToModel(model);
		return ("order/my-order");
	}

}
