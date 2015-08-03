package org.roba.javabrains.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Test class to work with hibernate (databases)
 * 
 * @author roba
 *
 */
@Entity(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@Column(name = "USER_ID")
	private int id;

	@Column(name = "USER_NAME")
	private String userName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
