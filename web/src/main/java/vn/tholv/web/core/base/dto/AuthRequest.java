package vn.tholv.web.core.base.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {
	private String username;
	private String password;
}
