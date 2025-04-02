package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.service.AccountService;
import com.poly.service.CategoryService;
import com.poly.service.ProductService;

@Controller
public class DefaultController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AccountService accountService;

	@RequestMapping("/")
	public String index(Model model) {

		List<Category> categories = categoryService.findAll();
		List<Product> products = productService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		return ("product/list");
	}

	/*
	 * @RequestMapping("/category/{id}") public String
	 * FindProductByCategory(@PathVariable String id, Model model) {
	 * 
	 * List<Category> categories = categoryService.findAll(); List<Product> products
	 * = productService.getProductsByCategory(id); model.addAttribute("categories",
	 * categories); model.addAttribute("products", products); return
	 * ("product/list"); }
	 */

	/*
	 * @RequestMapping("/product/{id}") public String ProductDetail(@PathVariable
	 * Integer id, Model model) {
	 * 
	 * List<Category> categories = categoryService.findAll(); Product product =
	 * productService.findById(id); // Lấy danh sách sản phẩm cùng danh mục
	 * List<Product> relatedProducts =
	 * productService.getProductsByCategory(product.getCategory().getId());
	 * model.addAttribute("categories", categories); model.addAttribute("product",
	 * product); model.addAttribute("relatedProducts", relatedProducts); return
	 * ("product/detail"); }
	 */

	@RequestMapping("/template")
	public String template() {
		return ("template");
	}

	/*
	 * /* @RequestMapping("/login") public String login() { return
	 * ("account/login"); }
	 * 
	 * @GetMapping("/register") public String registerForm(Model model) {
	 * model.addAttribute("user", new Account()); return "account/register"; }
	 * 
	 * @PostMapping("/register") public String
	 * processRegister(@ModelAttribute("user") Account user, BindingResult result,
	 * Model model) { if (result.hasErrors()) { return "account/register"; }
	 * 
	 * try { accountService.save(user); } catch (RuntimeException e) {
	 * model.addAttribute("error", e.getMessage()); return "account/register"; }
	 * 
	 * return "redirect:/register?success"; }
	 * 
	 * @RequestMapping("/forgot-password") public String forgot_password() { return
	 * ("account/forgot-password"); }
	 * 
	 * @RequestMapping("/profile") public String profile() { return
	 * ("account/profile"); }
	 * 
	 * @RequestMapping("/my-cart") public String my_cart() { return
	 * ("cart/my-cart"); }
	 * 
	 * @RequestMapping("/my-order") public String my_order() { return
	 * ("order/my-order"); }
	 */
}
