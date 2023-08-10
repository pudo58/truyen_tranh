package vn.tholv.web.core.override.util;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SecurityPath {
	public static List<String> SECURITY_PATH_PERMIT_ALL;

	public static String[] toArray() {
		return SECURITY_PATH_PERMIT_ALL.toArray(new String[0]);
	}

	public static List<String> getPathSecurity(){
		List<String> paths = new ArrayList<>();
		ClassPathResource resource = new ClassPathResource("application.security");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if(line.trim().startsWith("#")) continue;
				if (line.startsWith("permitAll()")) {
					while ((line = reader.readLine()) != null && line.trim().startsWith("/")) {
						paths.add(line.trim());
					}
				}
				if(line.startsWith("denyAll()")){
					while ((line = reader.readLine()) != null && line.trim().startsWith("/")) {
						paths.remove(line.trim());
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return paths;
	}
}
