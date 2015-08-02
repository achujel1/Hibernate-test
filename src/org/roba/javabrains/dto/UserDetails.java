package org.roba.javabrains.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetails {

	@Id
	private int id;
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
