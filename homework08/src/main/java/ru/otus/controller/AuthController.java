package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.auth.JwtCreator;
import ru.otus.model.User;
import ru.otus.service.GameService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private static final List<User> users = List.of(
			new User().setName("vasya").setPassword("123"),
			new User().setName("petya").setPassword("123"),
			new User().setName("stepa").setPassword("123"));

	private final JwtCreator jwtCreator;
	private final GameService gameService;

	@PostMapping("/api/authenticate/{gameId}")
	public String authenticate(@PathVariable String gameId, @RequestBody User user) {

		var gameObject = gameService.getGameObject(UUID.fromString(gameId));

		if (gameObject.getGamers().get(user.getName()) == null) {
			throw new AuthError();
		}

		return users.stream()
				.filter(e -> e.getName().equals(user.getName()) && e.getPassword().equals(user.getPassword()))
				.findFirst()
				.map(it -> jwtCreator.createJwt(user, gameId))
				.orElseThrow(AuthError::new);
	}

	@PostMapping("/api/create-new-battle")
	public String createNewBattle(@RequestBody Map<String, User> gamers) {
		var gameObject = gameService.createGame(gamers);
		UUID gameId = gameObject.getGameId();
		return gameId.toString();
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	public static class AuthError extends RuntimeException {
	}

	;
}
