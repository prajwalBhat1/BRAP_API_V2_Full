/**
 * 
 */
package com.brap.common.exception;

/**
 * @author prajwbhat
 *
 */
public class SimpleBrapException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String userErrorMessage;

	public SimpleBrapException() {
		super();
	}

	public SimpleBrapException(String message) {
		super(message);
	}

	public SimpleBrapException(String message, String userErrorMessage) {
		super(message);
		this.userErrorMessage = userErrorMessage;
	}

	public String getMessage() {
		return message;
	}

	public String getUserErrorMessage() {
		return userErrorMessage;
	}
}
