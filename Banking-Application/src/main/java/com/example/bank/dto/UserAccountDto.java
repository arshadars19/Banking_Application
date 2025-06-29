package com.example.bank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDto {
	@NotBlank(message = "Account holder name is required")
	private String accountHolderName;

	@NotBlank(message = "Account Number is required")
	private String accountNumber;

	private Double initialDeposit;
}
