/**
 * 
 */
package com.brap.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author prajwbhat
 *
 */
@Entity
public class BrapUserInfo {

	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private Timestamp createdDate;
	private Boolean active;
	private Timestamp passwordLastUpdatedDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getPasswordLastUpdatedDate() {
		return passwordLastUpdatedDate;
	}

	public void setPasswordLastUpdatedDate(Timestamp passwordLastUpdatedDate) {
		this.passwordLastUpdatedDate = passwordLastUpdatedDate;
	}

}
