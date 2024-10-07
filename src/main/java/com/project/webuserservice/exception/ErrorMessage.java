package com.project.webuserservice.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessage {

	public static final String INVALID_PASSWORD = "Password must be at least 8 characters long and contain at least 1 number, 1 uppercase and 1 lowercase character.";
	public static final String INVALID_USERNAME_FORMAT = "Username must be alphanumeric and at least 5 characters long";
	public final static String USERNAME_EXISTS = "Username already exists";

}
