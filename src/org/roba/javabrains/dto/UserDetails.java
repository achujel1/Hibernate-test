package org.roba.javabrains.dto;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@GeneratedValue
	// Commenting this for future tests
	// @Column(name = "USER_ID")
	private int userId;
	// Commenting this for future tests
	// @Column(name = "USER_NAME")
	// @Basic
	// This property means that the value won't be initialized in the table
	// @Transient
	// Here we will skip userName initialization in the table
	private String userName;
	// This annotation will only show date (without timestamp) in the table
	@Temporal(TemporalType.DATE)
	private Date joinedDate;
	
	@Embedded
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Found on the internet:
	 * 
	 * @Lob saves the data in BLOB or CLOB. Let�s understand what is BLOB and
	 *      CLOB
	 * 
	 *      CLOB(Character Large Object): If data is text and is not enough to
	 *      save in VARCHAR, then that data should be saved in CLOB.
	 * 
	 *      BLOB(Binary Large Object): In case of double byte character large
	 *      data is saved in BLOB data type.
	 * 
	 *      In case of Character[],char[] and String data is saved in CLOB. And
	 *      the data type Byte[], byte[] will be stored in BLOB.
	 * 
	 *      I still haven't used it. I should learn how to use this thing. Might
	 *      be useful.
	 */
	@Lob
	private Character[] descriptionLob;
	private String description;

	public Character[] getDescriptionLob() {
		return descriptionLob;
	}

	public void setDescriptionLob(Character[] descriptionLob) {
		this.descriptionLob = descriptionLob;
	}

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
