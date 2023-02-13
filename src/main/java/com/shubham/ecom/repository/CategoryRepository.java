package com.shubham.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.ecom.model.Category;

// Using jpa to transfer data between database category to service layer
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
