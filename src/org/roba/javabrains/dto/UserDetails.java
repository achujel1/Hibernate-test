package org.roba.javabrains.dto;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Test class to work with hibernate (databases)
 * 
 * @author roba
 *
 */
// @Entity(name = "USER_DETAILS")
// You can also use Entity annotation and name your table
@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

	@Id
	// Commenting this for future tests
	// @Column(name = "USER_ID")
	private int userId;
	// Commenting this for future tests
	// @Column(name = "USER_NAME")
	@Basic
	@Transient 
	
	private String userName;
	private Date joinedDate;
	private String address;
	private String description;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return userId;
	}

	public void setId(int id) {
		this.userId = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
