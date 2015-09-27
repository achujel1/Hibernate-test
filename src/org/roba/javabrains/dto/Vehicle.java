package org.roba.javabrains.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE")
@Inheritance(strategy = InheritanceType.JOINED)
// Commenting this part for TABLE_PER_CLASS strategy
//@DiscriminatorColumn(name = "VEHICLE_TYPE", discriminatorType = DiscriminatorType.STRING)

public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int vehicleId;
	private String vehicleName;
	@ManyToMany(mappedBy = "vehicle")
	private Collection<UserDetails> listOfUsers = new ArrayList<UserDetails>();

	public Collection<UserDetails> getListOfUsers() {
		return listOfUsers;
	}

	public void setListOfUsers(Collection<UserDetails> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}

	// Commenting this part of @ManyToMany test
	// @ManyToOne
	// private UserDetails user;
	//
	// public UserDetails getUser() {
	// return user;
	// }
	//
	// public void setUser(UserDetails user) {
	// this.user = user;
	// }

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

}
