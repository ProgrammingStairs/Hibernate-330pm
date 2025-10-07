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
@Table(name="dep_onemany_bi")
public class Department{ // inverse side or non-owning side
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deptid;
	
	@Column(name="dept_name")
	private String deptname;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL) 
	public Set<Employee> employee = new HashSet<Employee>();
	
	//---------------------------
	
	// either
//	public void addDepartment(Employee e) {
//		e.setDepartment(this);
//		this.employee.add(e); // Recommended approach
//	}

	// or
	public void addDepartment(Employee e) {
		e.setDepartment(this);
		employee.add(e);             
		this.setEmployee(employee);		
	}
/*
 	obj.x = 100;
 	or
 	obj.setX(100);
 	both do the same thing
 */
	//----------------------------
	
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