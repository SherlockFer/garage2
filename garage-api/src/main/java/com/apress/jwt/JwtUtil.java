package com.apress.jwt;

import java.security.Key;
import java.security.SignatureException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.apress.dto.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

	@Value("${jwt.secret}")
	private String JWT_SECRET;

	public String encode(UserDTO userDTO) {
		Instant expirationTime = Instant.now().plus(1, ChronoUnit.DAYS);
		Date expirationDate = Date.from(expirationTime);

		Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

		String jwtToken = Jwts.builder()
				.claim("user_id", userDTO.getId())
				.claim("role", userDTO.getRole())
				.claim("full_name", userDTO.getFullName())
				.setExpiration(expirationDate)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();

		return jwtToken;
	}

	public Claims decode(String jwtToken) throws SignatureException {
		Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();
	}
}