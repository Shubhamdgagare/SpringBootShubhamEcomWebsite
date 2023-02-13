package com.shubham.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.ecom.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	

}
