package com.hibernate.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQuery(
		name="User1.findByEmailId",
		query="select u from User1 u where email=:email"
)
@NamedQuery(
		name="User1.findByName",
		query="select u from User1 u where username=:name"
)
@NamedNativeQuery(		
		name="User1.findByNativeName",
		query="select * from User1 where username=:name",
		resultClass = User1.class
)
@NamedNativeQuery(		
		name="User1.printNameAndEmail",
		query="select username,email from User1"
)

@Table(name="user1")
public class User1{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int uid;
	
	@Column(name="username")
	String username;
	
	@Column(name="email")
	String email;
	
	@Column(name="password")
	String password;
	
	@Column(name="address")
	String address;
	
	@Column(name="salary")
	int salary;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
}