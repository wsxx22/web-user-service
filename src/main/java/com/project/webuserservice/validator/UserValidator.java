package com.project.webuserservice.validator;

import com.project.webuserservice.exception.ErrorMessage;
import java.util.regex.Pattern;

public class UserValidator {

	private static final Pattern USERNAME_PATTERN =
			Pattern.compile("^[a-zA-Z0-9]{5,}$");

	public static void validate(String username) {
		if (!USERNAME_PATTERN.matcher(username).matches()) {
			throw new IllegalArgumentException(ErrorMessage.INVALID_USERNAME_FORMAT);
		}
	}

}
