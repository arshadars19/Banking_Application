package com.example.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

	@Email(message = "Invalid email")
	@NotBlank(message = "Email is required")
	private String email;
	@NotBlank(message = "Password is required")
	private String password;

}
