package vn.tholv.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.tholv.web.core.base.dto.AuthRequest;
import vn.tholv.web.core.base.service.JwtService;

import java.util.Map;

@RestController
public class AuthController {
	private AuthenticationManager authenticationManager;
	private JwtService jwtService;
	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	@PostMapping("/auth")
	public Map authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			Map<String, String> token = Map.of("token", jwtService.generateToken(authRequest.getUsername()));
			return token;
		} else {
			throw new RuntimeException("Authentication failed");
		}
	}
}
