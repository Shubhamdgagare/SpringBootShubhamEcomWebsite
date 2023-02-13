package com.shubham.ecom.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetail extends User implements UserDetails {
	
	public CustomUserDetail(User user) {
		super(user);
	}
	
//	Here we have override methods with alt+shift+S+V keys its for spring security authentification
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Here we will grant authority to user as per roll 
		
		List<GrantedAuthority> authorityList = new ArrayList<>();
		super.getRoles().forEach(role ->{
			authorityList.add(new SimpleGrantedAuthority(role.getName()));
		});
		
		return null;
	}

	@Override
	public String getUsername() {
		return super.getEmail();
	}
	
	@Override
	public String getPassword() {
		return super.getPassword();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
