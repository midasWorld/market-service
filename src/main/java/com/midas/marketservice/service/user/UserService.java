package com.midas.marketservice.service.user;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.StringUtils.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midas.marketservice.domain.user.User;
import com.midas.marketservice.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public User join(String email, String password, String name) {
		checkArgument(isNotEmpty(email), "email must be provided.");
		checkArgument(isNotEmpty(password), "password must be provided.");
		checkArgument(isNotEmpty(name), "name must be provided.");

		User user = new User(email, password, name);

		return userRepository.save(user);
	}

	@Transactional
	public User login(String email, String password) {
		checkArgument(isNotEmpty(email), "email must be provided.");
		checkArgument(isNotEmpty(password), "password must be provided.");

		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new IllegalArgumentException("Could not found User with email=" + email));

		user.verifyPassword(password);

		return user;
	}

	public Optional<User> findById(Long id) {
		checkArgument(id != null, "userId must be provided.");

		return userRepository.findById(id);
	}

	public Optional<User> findByEmail(String email) {
		checkArgument(isNotEmpty(email), "email must be provided.");

		return userRepository.findByEmail(email);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
}
