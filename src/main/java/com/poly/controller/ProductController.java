package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entity.Account;
import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.service.AccountService;
import com.poly.service.CategoryService;
import com.poly.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
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

	@RequestMapping("/list")
	public String index(Model model) {
		addUserInfoToModel(model);

		List<Category> categories = categoryService.findAll();
		List<Product> products = productService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		return ("product/list");
	}

	@RequestMapping("/{id}")
	public String ProductDetail(@PathVariable Integer id, Model model) {
		addUserInfoToModel(model);

		List<Category> categories = categoryService.findAll();
		Product product = productService.findById(id);
		// Lấy danh sách sản phẩm cùng danh mục
		List<Product> relatedProducts = productService.getProductsByCategory(product.getCategory().getId());
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		model.addAttribute("relatedProducts", relatedProducts);
		return ("product/detail");
	}

	@RequestMapping("/category/{id}")
	public String FindProductByCategory(@PathVariable String id, Model model) {
		addUserInfoToModel(model);

		List<Category> categories = categoryService.findAll();
		List<Product> products = productService.getProductsByCategory(id);
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		return ("product/list");
	}

	// Chức năng tìm kiếm
	@GetMapping("/search")
	public String search(@RequestParam("keyword") String keyword, Model model) {
		addUserInfoToModel(model);
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		List<Product> results = productService.searchByKeyword(keyword);
		model.addAttribute("products", results);
		model.addAttribute("keyword", keyword); // để hiển thị lại từ khóa tìm kiếm
		return "product/list"; // Trang kết quả tìm kiếm
	}

}
