package br.com.happyhour.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTService {
	
	@Value("${spring.jwt.subscription-key}")
	private String subscriptionKey;
	
	@Value("${spring.jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String email) {
		return Jwts.builder()
		.setSubject(email)
		.setExpiration(new Date(System.currentTimeMillis() + expiration))
		.signWith(SignatureAlgorithm.HS512, subscriptionKey)
		.compact();
	}
	
	public String getEmailUser(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		
		return null;
	}
	
	public boolean tokenIsValid(String token) {
		Claims claims = getClaims(token);
		System.out.println(claims);
		if(claims != null) {
			String userEmail = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date dateNow = new Date(System.currentTimeMillis());
			if(userEmail != null && expirationDate != null && dateNow.before(expirationDate)) {
				System.out.println(userEmail +" "+ expirationDate +" "+ dateNow);
				return true;
			}
		}
		
		return false;
	}
	
	private Claims getClaims(String token) {
		try {
			return Jwts
					.parser()
					.setSigningKey(subscriptionKey)
					.parseClaimsJws(token).getBody();
		}catch(Exception e) {
			return null;
		}
	}

}
