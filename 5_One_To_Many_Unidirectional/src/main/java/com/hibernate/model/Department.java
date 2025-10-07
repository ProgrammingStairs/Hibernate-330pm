package com.hibernate.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="dep_onemany_uni")
public class Department{ // owning side
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deptid;
	
	@Column(name="dept_name")
	private String deptname;
	
//	@OneToMany(cascade = CascadeType.ALL)
	/*
	  @OneToMany(cascade = CascadeType.ALL) if we write cascade, then in this 
	  case we do not need to write session.persist(e1); session.persist(e2); 
	  explicitly for (setting or inserting)making entry of employee in employee
	  table, hibernate implicitly do entries of employee into database for you
	  when you write cascade in annotation onetomany.
	 */
	
	@OneToMany
	/*
	   if we do not write cascade here in onetomany, then in this case only 
	   department entry is going to be inserted into database, employee entries 
	   are not implicitly done. we need to write session.persist(e1); 
	   session.persist(e2); explicitly to make entry of employee objects in 
	   database. 
	 */
	
	// joincolumn represents owning side
	@JoinColumn(name="dept_id") // foreign key is going to create in employee table
	public Set<Employee> employee = new HashSet<Employee>();
	
	// foreign key is always on many side, hibernate internally set foreign key in employee table
	
	public Set<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
	
}