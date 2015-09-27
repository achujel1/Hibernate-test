package org.roba.javabrains.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
// Commenting this part for TABLE_PER_CLASS strategy
//@DiscriminatorValue("Bike")
public class TwoWheeler extends Vehicle {

	private String SteeringHandle;

	public String getSteeringHandle() {
		return SteeringHandle;
	}

	public void setSteeringHandle(String steeringHandle) {
		SteeringHandle = steeringHandle;
	}

}
