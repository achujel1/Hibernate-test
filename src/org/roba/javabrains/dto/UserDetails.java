package org.roba.javabrains.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	// Creating a list of addresses
	// @ElementCollection
	// private Set<Address> listOfAddresses = new HashSet();
	// public Set<Address> getListOfAddresses() {
	// return listOfAddresses;
	// }
	//
	// public void setListOfAddresses(Set<Address> listOfAddresses) {
	// this.listOfAddresses = listOfAddresses;
	// }

	// Creating a collection of addresses to test primary keys in a collection
	// @ElementCollection(fetch=FetchType.EAGER)
	// @JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name =
	// "USER_ID"))
	// @GenericGenerator(name = "hilo-gen", strategy = "hilo")
	// @CollectionId(columns = { @Column(name = "ADDRESS_ID") }, generator =
	// "hilo-gen", type = @Type(type = "long"))
	// private Collection<Address> listOfAddresses = new ArrayList<Address>();

	// Testing fetchTypes
	@ElementCollection(fetch = FetchType.EAGER)
	// @ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
	private Collection<Address> listOfAddresses = new ArrayList<Address>();

	public Collection<Address> getListOfAddresses() {
		return listOfAddresses;
	}

	public void setListOfAddresses(Collection<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}

	// This annotation will only show date (without timestamp) in the table
	@Temporal(TemporalType.DATE)
	private Date joinedDate;

	@Embedded
	private Address address;

	// Commenting for future tests
	/* From here */

	// @Embedded
	// @AttributeOverrides({
	// @AttributeOverride(name = "street", column = @Column(name =
	// "HOME_STREET_NAME")),
	// @AttributeOverride(name = "city", column = @Column(name =
	// "HOME_CITY_NAME")),
	// @AttributeOverride(name = "state", column = @Column(name =
	// "HOME_STATE_NAME")),
	// @AttributeOverride(name = "pincode", column = @Column(name =
	// "HOME_PIN_CODE")) })
	// private Address homeAddress;
	//
	// @Embedded
	// @AttributeOverrides({
	// @AttributeOverride(name = "street", column = @Column(name =
	// "WORK_STREET_NAME")),
	// @AttributeOverride(name = "city", column = @Column(name =
	// "WORK_CITY_NAME")),
	// @AttributeOverride(name = "state", column = @Column(name =
	// "WORK_STATE_NAME")),
	// @AttributeOverride(name = "pincode", column = @Column(name =
	// "WORK_PIN_CODE")) })
	// private Address workAddress;

	// public Address getHomeAddress() {
	// return homeAddress;
	// }
	//
	// public void setHomeAddress(Address homeAddress) {
	// this.homeAddress = homeAddress;
	// }
	//
	// public Address getWorkAddress() {
	// return workAddress;
	// }
	//
	// public void setWorkAddress(Address workAddress) {
	// this.workAddress = workAddress;
	// }

	/* To here */

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
