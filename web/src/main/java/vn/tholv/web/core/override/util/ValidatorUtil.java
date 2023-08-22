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

    public static final boolean validateUsername(String username) {
        return username.matches("^[a-zA-Z0-9_]{6,20}$");
    }

    public static final boolean validatePassword(String password) {
        return password.matches("^[a-zA-Z0-9_]{6,}$");
    }

    public static final boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)+$");
    }

	public boolean isMatch(String str) {
		return str.matches(pattern);
	}
}
