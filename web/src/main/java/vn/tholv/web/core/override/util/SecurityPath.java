package vn.tholv.web.core.override.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SecurityPath {
	public List<SecurityDataSource> getPathSecurity() {
		ClassPathResource resource = new ClassPathResource("application.security");
		List<SecurityDataSource> securityDataSources = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			String line;
			SecurityDataSource securityDataSource = null;
			List<String> paths = new ArrayList<>();
			while ((line = reader.readLine()) != null) {
				if (line.trim().startsWith("#")) continue;

				if (line.startsWith("permitAll()") || line.startsWith("denyAll()") || line.startsWith("role") || line.startsWith("authenticated()")) {
					if (securityDataSource != null) {
						securityDataSource.getPaths().addAll(paths);
						securityDataSources.add(securityDataSource);
						paths.clear();
					}
					securityDataSource = new SecurityDataSource();
					if (line.startsWith("permitAll()")) {
						securityDataSource.setPermitAll(true);
					} else if (line.startsWith("denyAll()")) {
						securityDataSource.setDenyAll(true);
					} else if (line.startsWith("role")) {
						String role = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
						if (role.trim().length() > 0 && !role.contains("ROLE_")) {
							securityDataSource.setRole(role);
						}
					} else if (line.startsWith("authenticated()")) {
						securityDataSource.setAuthenticated(true);
					}
				} else if (line.startsWith("\t") && securityDataSource != null) {
					String path = line.trim();
					log.info("path security: {}", path);
					paths.add(path);
				}
			}
			if (securityDataSource != null) {
				securityDataSource.getPaths().addAll(paths);
				securityDataSources.add(securityDataSource);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return securityDataSources;
	}

}
