package com.example.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.bank.dto.UserAccountDto;
import com.example.bank.entity.UserAccountEntity;
import com.example.bank.repository.UserAccountRepository;

@Service
public class UserAccountService {
	@Autowired
	private UserAccountRepository userAccountRepository;

	public UserAccountEntity deposit(UserAccountDto dto) {

		UserAccountEntity account = UserAccountEntity.builder().accountHolderName(dto.getAccountHolderName())
				.accountNumber(dto.getAccountNumber())
				.balance(dto.getInitialDeposit()).build();

		return userAccountRepository.save(account);
	}

	public UserAccountEntity getUserDetails(String accountNumber) {
		return userAccountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Account not found for number: " + accountNumber));
	}

	public List<UserAccountEntity> getAllUserInfo() {
		return userAccountRepository.findAll();
	}

}