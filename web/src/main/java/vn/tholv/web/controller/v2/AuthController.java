package vn.tholv.web.controller.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tholv.web.core.base.constant.Token;
import vn.tholv.web.core.base.dto.AuthRequest;
import vn.tholv.web.core.base.service.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/v2")
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
			if (Boolean.FALSE.equals(authRequest.getRememberMe())) {
				String token = jwtService.generateToken(authRequest.getUsername());
				Map<String, String> result = Map.of(Token.ACCESS_TOKEN, Token.TOKEN_TYPE_BEARER + token);
				return result;
			} else {
				String token = jwtService.generateRefreshToken(authRequest.getUsername());
				Map<String, String> result = Map.of(Token.REFRESH_TOKEN, Token.TOKEN_TYPE_BEARER + token);
				return result;
			}
		} else {
			throw new RuntimeException("Xác thực thất bại");
		}
	}
}
