package com.ms.msemployees.security.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ms.msemployees.mappers.UserMapper;
import com.ms.msemployees.models.User;
import com.ms.msemployees.repositories.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userrep;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userrep.findByUsername(username)
						   .orElseThrow(()-> new UsernameNotFoundException("User NOT Found !"));
		
		return UserMapper.userToPrincipal(user);
	}

}
