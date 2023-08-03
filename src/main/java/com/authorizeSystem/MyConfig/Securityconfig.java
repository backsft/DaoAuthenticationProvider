package com.authorizeSystem.MyConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)

public class Securityconfig {
	//can use the Bean or can do autowire but we can not use new instance beucase there will repository autowired in customUserdeatilservice which willbe null
	@Autowired
	customUserdetailService customUserdetailService;
	
	
	@Bean PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authprovider=new DaoAuthenticationProvider();
		authprovider.setUserDetailsService(detailsService());
		authprovider.setPasswordEncoder(passwordEncoder());
		
		return authprovider;
		
	}
	
	@Bean
	
	UserDetailsService detailsService() {
		return new customUserdetailService();
	}
	
	
	@Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
			http
			
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth-> {
				auth.requestMatchers("/").permitAll();
				auth.requestMatchers("/admin").hasAnyRole("ADMIN","USER");
				auth.requestMatchers("/superadmin").hasAnyRole("ADMIN","SUPERADMIN");
				auth.requestMatchers("/test").hasAnyRole("READER","WRITER");
				auth.anyRequest().authenticated();
				
				}				
		);
			http.formLogin(Customizer.withDefaults());
			
				
		
		
		return http.build();
	}
	
	

}
