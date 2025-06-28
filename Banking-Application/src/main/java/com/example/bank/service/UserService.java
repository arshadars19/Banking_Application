package com.example.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bank.Util.JwtUtil;
import com.example.bank.dto.LoginRequestDto;
import com.example.bank.dto.UserDto;
import com.example.bank.entity.UserEntity;
import com.example.bank.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtUtil jwtUtil;

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public String registerUser(UserDto userDto) {
		// Check if user with email already exists
		if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
			return "Email already registered.";
		}
		// 2. Hash password
		String encodedPassword = passwordEncoder.encode(userDto.getPassword());

		// 3. Create User entity and save
		UserEntity user = UserEntity.builder().name(userDto.getName()).email(userDto.getEmail())
				.password(encodedPassword).build();

		userRepository.save(user);

		return "✅ User registered successfully!";
	}

	public String loginUser(LoginRequestDto dto) {
		Optional<UserEntity> user = userRepository.findByEmail(dto.getEmail());
		if (user.isEmpty()) {
			return "❌ Invalid email or password";
		}
		UserEntity userEntity = user.get();
		boolean passwordMatch = passwordEncoder.matches(dto.getPassword(), userEntity.getPassword());
//		return passwordMatch ? "✅ Login successful!" : "❌ Invalid email or password";
		if (!passwordMatch) {
			return "❌ Invalid email or password";
		}

		String token = jwtUtil.generateToken(userEntity.getEmail());
		return "✅ Login successful! \nToken: " + token;

	}
}
