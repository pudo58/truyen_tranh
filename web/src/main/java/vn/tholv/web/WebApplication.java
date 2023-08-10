package vn.tholv.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.tholv.web.core.override.util.SecurityPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class WebApplication {
	public static void main(String[] args) throws FileNotFoundException {
		SecurityPath.SECURITY_PATH_PERMIT_ALL = getPathSecurity();
		SpringApplication.run(WebApplication.class, args);
	}

	private static List<String> getPathSecurity() throws FileNotFoundException {
		File file = new File("src/main/resources/application.security");
		Scanner sc = new Scanner(file);
		List<String> list = new ArrayList<>();
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			list.add(data);
		}
		sc.close();
		return list;
	}
}
