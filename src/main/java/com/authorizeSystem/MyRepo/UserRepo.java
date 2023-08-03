package com.authorizeSystem.MyRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authorizeSystem.MyEntity.Users;



public interface UserRepo extends JpaRepository<Users, Integer>{
	
	Users findByEmail(String email);
	Users findByMobileNo(String mobileNo);

}
