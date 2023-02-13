package com.shubham.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.ecom.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
//	Creating method for finding ID of selected category in productAdd page 
	List<Product> findAllByCategory_Id(int id);
}
