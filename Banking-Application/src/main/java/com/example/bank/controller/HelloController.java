package com.example.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String getHello()
	{
		return "Hello, Arsh! Welcome to your Banking Application 🚀";
	}

}
