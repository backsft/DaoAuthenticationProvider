package com.authorizeSystem.MyContorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String homepage() {		
		return " welcome to homepage";
	}
	
	
	@GetMapping("/test")
	public String test() {		
		return " welcome to test page";
	}
	
	@GetMapping("/admin")
	public String admin() {		
		return " welcome to admin page";
	}

}
