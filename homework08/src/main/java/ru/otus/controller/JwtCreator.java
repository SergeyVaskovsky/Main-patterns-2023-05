package ru.otus.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;

@Service
public class JwtCreator {
	private final RSAPrivateKey privateKey;

	public JwtCreator(@Value("${otus.auth.private}") String privateKey) {
		this.privateKey = KeysParser.getPrivateKeyFromString(privateKey);
	}

	public String createJwt(User user) {
		return Jwts.builder()
				.setSubject(user.getName())
				.signWith(privateKey)
				.compact();
	}
}
