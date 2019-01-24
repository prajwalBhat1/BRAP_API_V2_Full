package com.brap.common.exception;

public class UnableToUploadFileException extends SimpleBrapException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnableToUploadFileException(String message, String userErrorMessage) {
		super(message, userErrorMessage);
	}
}


