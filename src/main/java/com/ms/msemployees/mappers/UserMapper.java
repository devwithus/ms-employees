package com.ms.msemployees.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ms.msemployees.models.User;
import com.ms.msemployees.security.beans.UserPrincipal;

public class UserMapper {

	public static UserPrincipal userToPrincipal(User user) {
		
		List<SimpleGrantedAuthority> authorities = user.getRoles()
												.stream()
												.map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName()))
												.collect(Collectors.toList());
		
		
		return new UserPrincipal()
					.setUsername(user.getUsername())
					.setPassword(user.getPassword())
					.setEnabled(user.isEnabled())
					.setAuthorities(authorities);
	}

}
