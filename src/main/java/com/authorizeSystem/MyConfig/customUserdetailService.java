package com.authorizeSystem.MyConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.authorizeSystem.MyEntity.CustomUser;
import com.authorizeSystem.MyEntity.Users;
import com.authorizeSystem.MyRepo.UserRepo;

@Component
public class customUserdetailService implements UserDetailsService{
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByMobileNo(username);
	
		System.out.println(user);
		if (user == null) {
			throw new UsernameNotFoundException("Mobile no not found");
		} else {
			return new CustomUser(user);
		}
		
	}
	
	

}
