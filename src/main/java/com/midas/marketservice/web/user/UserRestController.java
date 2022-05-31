package com.midas.marketservice.web.user;

import static com.midas.marketservice.web.ApiResponse.*;
import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.midas.marketservice.service.user.UserService;
import com.midas.marketservice.web.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserRestController {

	private final UserService userService;

	@PostMapping("/join")
	public ApiResponse<UserDto> join(@RequestBody JoinRequest request) {
		return OK(
			new UserDto(
				userService.join(
					request.getEmail(),
					request.getPassword(),
					request.getName()
				)
			)
		);
	}

	@PostMapping("/me")
	public ApiResponse<UserDto> login(@RequestBody LoginRequest request) {
		return OK(
			new UserDto(
				userService.login(request.getPrincipal(), request.getCredentials())
			)
		);
	}

	@GetMapping("/exists")
	public ApiResponse<Boolean> checkEmail(@RequestParam String email) {
		return OK(
			userService.findByEmail(email).isPresent()
		);
	}

	@GetMapping
	public ApiResponse<List<UserDto>> findAll() {
		return OK(
			userService.findAll().stream()
				.map(UserDto::new)
				.collect(toList())
		);
	}
}
