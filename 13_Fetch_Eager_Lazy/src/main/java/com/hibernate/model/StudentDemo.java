package com.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="StudentDemo")
public class StudentDemo{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sid")
	int sid;
	
	@Column(name="studentname")
	String name;
	
	@OneToOne(fetch = FetchType.EAGER)
//	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="passport_id",unique = true)
	PassportDemo passportDemo;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PassportDemo getPassportDemo() {
		return passportDemo;
	}

	public void setPassportDemo(PassportDemo passportDemo) {
		this.passportDemo = passportDemo;
	}
	
	
}