package vn.tholv.web.core.override.util;

public class ValidatorUtil {
	private String pattern;

	public ValidatorUtil(String pattern) {
		this.pattern = pattern;
	}
	public static final boolean isNull(Object obj) {
		if (obj instanceof String str) {
			return str == null || str.trim().isEmpty();
		}
		return obj == null;
	}

	public boolean isMatch(String str) {
		return str.matches(pattern);
	}
}
