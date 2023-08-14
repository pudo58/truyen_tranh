package vn.tholv.web.core.override.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class SecurityDataSource {
	private String role;
	private List<String> paths = new ArrayList<>();
	private boolean permitAll = false;
	private boolean denyAll = false;
	private boolean authenticated = false;
}
