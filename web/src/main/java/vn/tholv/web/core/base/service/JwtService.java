package vn.tholv.web.core.base.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

public interface JwtService {
	String extractUsername(String token);

	Date extractExpiration(String token);

	<T> T extractClaim(String token, Function<Claims, T> claimsResolver);

	Boolean validateToken(String token, UserDetails userDetails);

	String generateToken(String userName);
}
