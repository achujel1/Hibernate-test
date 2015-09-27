package org.roba.javabrains.dto;

import javax.persistence.Entity;

@Entity
// Commenting this part for TABLE_PER_CLASS strategy
// @DiscriminatorValue("Car")
public class FourWheeler extends Vehicle {

	private String SteeringWheel;

	public String getSteeringWheel() {
		return SteeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		SteeringWheel = steeringWheel;
	}

}
