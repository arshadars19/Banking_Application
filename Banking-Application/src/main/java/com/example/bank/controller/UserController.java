package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.LoginRequestDto;
import com.example.bank.dto.UserDto;
import com.example.bank.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public String registerUser(@RequestBody @Valid UserDto userDto) {
		return userService.registerUser(userDto);
	}

	@PostMapping("/login")
	public String loginUser(@RequestBody @Valid LoginRequestDto loginDto) {
		return userService.loginUser(loginDto);
	}
}
