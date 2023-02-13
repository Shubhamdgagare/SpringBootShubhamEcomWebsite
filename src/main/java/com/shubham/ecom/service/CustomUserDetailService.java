package com.shubham.ecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shubham.ecom.model.CustomUserDetail;
import com.shubham.ecom.model.User;
import com.shubham.ecom.repository.UserRepository;

public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByEmail(email);
		
		user.orElseThrow(()-> new UsernameNotFoundException("User nahi bhetla "));
		return user.map(CustomUserDetail::new).get();
	}
	
	

}
