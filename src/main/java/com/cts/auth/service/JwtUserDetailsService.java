package com.cts.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.auth.dao.UserDao;
import com.cts.auth.model.MyUserDetails;
import com.cts.auth.model.User;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
		System.out.println("loadUserByUsername"+username);

		Optional<User> optionaluserList = userDao.findByUserName(username);
		if(optionaluserList.isPresent()) {
			
			User user=optionaluserList.get();
			System.out.println(user);
			return new MyUserDetails(user);
		}else {
			throw new UsernameNotFoundException("User not found with username: " + username);

		}
		

	}


}