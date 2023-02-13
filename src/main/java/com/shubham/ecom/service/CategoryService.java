package com.shubham.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.ecom.model.Category;
import com.shubham.ecom.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
//	for showing list of category on category page sending to admin controller page
	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
	}
	
//	for saving category in database
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
// Service layer for deleting category from database with given id send from form
//	sending this method to controll
	public void removeCategoryById(int id) { categoryRepository.deleteById(id);}

	
//	Query for updating category with ID
//	Optional<T> = It provides a clear and explicit way to convey the message that there may not be a value, without using null.
//	we use optional to avoid error if cant find id with given input
	public Optional<Category> getCategoryById(int id) { return categoryRepository.findById(id);}
}
