package com.brap.common.exception;

public class InvalidPasswordException extends SimpleBrapException {

	public InvalidPasswordException(String message, String userErrorMessage) {
		super(message, userErrorMessage);
	}

}
