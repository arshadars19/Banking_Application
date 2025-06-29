package com.example.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.LoginRequestDto;
import com.example.bank.dto.UserAccountDto;
import com.example.bank.dto.UserDto;
import com.example.bank.entity.UserAccountEntity;
import com.example.bank.service.UserAccountService;
import com.example.bank.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserAccountService userAccountService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody @Valid UserDto userDto) {
		String registerUser = userService.registerUser(userDto);
		return ResponseEntity.ok(registerUser);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody @Valid LoginRequestDto loginDto) {
		String loginUser = userService.loginUser(loginDto);
		return ResponseEntity.ok(loginUser);

	}

	@PostMapping("/deposit")
	public ResponseEntity<UserAccountEntity> deposit(@RequestBody @Valid UserAccountDto dto) {
		UserAccountEntity created = userAccountService.deposit(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetMapping("/account/{accountNumber}")
	public ResponseEntity<UserAccountEntity> getUserDetails(@PathVariable String accountNumber) {
		UserAccountEntity account = userAccountService.getUserDetails(accountNumber);
		return ResponseEntity.ok(account);
	}

	@GetMapping("/allUserInfo")
	public ResponseEntity<List<UserAccountEntity>> getAllUserInfo() {
		List<UserAccountEntity> account = userAccountService.getAllUserInfo();
		return ResponseEntity.ok(account);
	}
}
