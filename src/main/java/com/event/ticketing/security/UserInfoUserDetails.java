package com.event.ticketing.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.event.ticketing.entity.User;

public class UserInfoUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private boolean isActive;
	private boolean isLocked;
	private List<GrantedAuthority> authorities;
	
	public UserInfoUserDetails(User user) {
		name=user.getUsername();
		password=user.getPassword();
		authorities= Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		isActive=user.getIsActive();
		isLocked=user.getIsLocked();
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isActive;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isLocked;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

}
