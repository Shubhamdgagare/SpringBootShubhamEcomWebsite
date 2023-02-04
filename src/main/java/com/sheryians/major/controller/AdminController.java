package com.sheryians.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sheryians.major.model.Category;
import com.sheryians.major.service.CategoryService;

@Controller
public class AdminController {
	
	@Autowired
	CategoryService categoryService;
	
//	getting admin page
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
//	getting category page and showing list of category from database
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	
//	getting add category page with list of data
//	we cant send it blank because we have requested object with name if 'category' in categoriesAdd.html so sending blank Category object
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
//	after submitting form with post method we will be saving that data in database and redirected to categories page
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
}
