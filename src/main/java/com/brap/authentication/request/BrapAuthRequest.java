/**
 * 
 */
package com.brap.authentication.request;

import java.io.Serializable;

/**
 * @author prajwbhat
 *
 */
public class BrapAuthRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userName;
	private String passWord;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return passWord;
	}

	public void setPassword(String password) {
		this.passWord = password;
	}

}
