package vn.tholv.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static vn.tholv.web.core.override.util.SecurityPath.SECURITY_PATH_PERMIT_ALL;
import static vn.tholv.web.core.override.util.SecurityPath.getPathSecurity;

@SpringBootApplication
public class WebApplication {
	public static void main(String[] args) {
		SECURITY_PATH_PERMIT_ALL = getPathSecurity();
		SpringApplication.run(WebApplication.class, args);
	}
}
