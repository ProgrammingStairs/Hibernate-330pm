package com.hibernate.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="vehicle_table_per_class")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Vehicle{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Important Line
	int vid;
	
	@Column(name="vehiclename")
	String vehicleName;

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
	
	
}