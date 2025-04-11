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
import com.poly.entity.Product;
import com.poly.service.AccountService;
import com.poly.service.CategoryService;
import com.poly.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
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

	@RequestMapping("/")
	public String index(Model model) {
		addUserInfoToModel(model);

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		List<Product> products = productService.findAll();
		model.addAttribute("products", products);
		return ("product/list");
	}

	@RequestMapping({ "/admin", "/admin/home/index" })
	public String admin(Model model) {
		addUserInfoToModel(model);
		return ("forward:/admin/index.html");
	}

	@RequestMapping("/template")
	public String template(Model model) {
		addUserInfoToModel(model);
		return ("template");
	}

}
