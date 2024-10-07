package com.project.webuserservice.validator;

import com.project.webuserservice.exception.ErrorMessage;
import java.util.Objects;
import java.util.regex.Pattern;

public class PasswordValidator {

	private static final Pattern PASSWORD_PATTERN =
			Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

	public static void validate(String password) {
		if (Objects.isNull(password) || !PASSWORD_PATTERN.matcher(password).matches()) {
			throw new IllegalArgumentException(ErrorMessage.INVALID_PASSWORD);
		}
	}

}
