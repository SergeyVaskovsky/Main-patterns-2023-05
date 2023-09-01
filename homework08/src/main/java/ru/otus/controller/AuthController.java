package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private static final List<User> users = List.of(
			new User().setName("vasya").setPassword("123"),
			new User().setName("petya").setPassword("123"),
			new User().setName("stepa").setPassword("123"));

	private final JwtCreator jwtCreator;

	@PostMapping("/api/authenticate")
	public String authenticate(@RequestBody User user) {
		return users.stream()
				.filter(e -> e.getName().equals(user.getName()) && e.getPassword().equals(user.getPassword()))
				.findFirst()
				.map(jwtCreator::createJwt)
				.orElseThrow(AuthError::new);
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	public static class AuthError extends RuntimeException {};
}
