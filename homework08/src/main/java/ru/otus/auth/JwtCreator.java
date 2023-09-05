package ru.otus.auth;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.model.User;

import java.security.interfaces.RSAPrivateKey;
import java.util.Map;

@Service
public class JwtCreator {
	private final RSAPrivateKey privateKey;

	public JwtCreator(@Value("${otus.auth.private}") String privateKey) {
		this.privateKey = KeysParser.getPrivateKeyFromString(privateKey);
	}

    public String createJwt(User user, String gameId) {
        return Jwts.builder()
                .setSubject(user.getName())
                .addClaims(Map.of(
                        "gameId", gameId))
                .signWith(privateKey)
                .compact();
    }
}
