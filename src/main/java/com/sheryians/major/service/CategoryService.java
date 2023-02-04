package com.sheryians.major.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.Category;
import com.sheryians.major.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
//	for saving category in database
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
//	for showing list of category on category page sending to admin controller page
	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
	}

}
