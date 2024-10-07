package com.project.webuserservice.service;

import com.project.webuserservice.dto.UserDto;
import com.project.webuserservice.exception.ErrorMessage;
import com.project.webuserservice.exception.UserAlreadyExistsException;
import com.project.webuserservice.model.User;
import com.project.webuserservice.repository.UserRepository;
import com.project.webuserservice.validator.PasswordValidator;
import com.project.webuserservice.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserDto register(UserDto userDto) {
		UserValidator.validate(userDto.getUsername());
		PasswordValidator.validate(userDto.getPassword());

		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new UserAlreadyExistsException(ErrorMessage.USERNAME_EXISTS);
		}

		User createdUser = userRepository.save(User.builder()
				.username(userDto.getUsername())
				.password(passwordEncoder.encode(userDto.getPassword()))
				.build());

		return UserDto.builder().username(createdUser.getUsername()).build();
	}

}
