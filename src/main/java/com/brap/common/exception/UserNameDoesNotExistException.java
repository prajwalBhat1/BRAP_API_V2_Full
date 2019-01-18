/**
 * 
 */
package com.brap.common.exception;

/**
 * @author prajwbhat
 *
 */
public class UserNameDoesNotExistException extends SimpleBrapException {

	public UserNameDoesNotExistException(String message, String userErrorMessage) {
		super(message, userErrorMessage);
	}

}
