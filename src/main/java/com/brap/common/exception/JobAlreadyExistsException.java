/**
 * 
 */
package com.brap.common.exception;

/**
 * @author prajwbhat
 *
 */
public class JobAlreadyExistsException extends SimpleBrapException {

	public JobAlreadyExistsException() {
		super();
	}

	public JobAlreadyExistsException(String message, String userErrorMessage) {
		super(message, userErrorMessage);
	}
}
