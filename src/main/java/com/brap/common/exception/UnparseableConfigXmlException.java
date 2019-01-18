package com.brap.common.exception;

public class UnparseableConfigXmlException extends SimpleBrapException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnparseableConfigXmlException(String message, String userErrorMessage) {
		super(message, userErrorMessage);
	}

}
