package com.shubham.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubham.ecom.model.Product;
import com.shubham.ecom.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
//	for select all query
	public List<Product> getAllProduct(){ return productRepository.findAll(); }
	
//	adding products in database
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
//	For remove/delete product
	public void removeProductById(long id) {
		productRepository.deleteById(id);
	}
	
//	for finding product by id
	public Optional<Product> getProductById(long id){
		return productRepository.findById(id);
	}
	
//	Getting all category in add product option and later selecting one for product
	public List<Product> getAllProductsByCategoryId(int id){
		return productRepository.findAllByCategory_Id(id);
	}
	 
}
