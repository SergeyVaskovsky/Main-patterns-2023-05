package ru.otus.auth;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ClaimsAuthentication implements Authentication {
	private final Claims claims;
	private UUID gameId;

	public ClaimsAuthentication(Claims claims) {
		this.claims = claims;
		gameId = UUID.fromString((String) claims.get("gameId"));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(Role.ADMIN);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return claims;
	}

	@Override
	public Object getPrincipal() {
		return getName();
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

	}

	@Override
	public String getName() {
		return claims.getSubject();
	}
}
