package com.hibernate.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="stud_manymany_bi")
public class Student{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int sid;
	
	@Column(name="studname")
	String name;

	@ManyToMany
	@JoinTable(
			name="student_course",
			joinColumns = @JoinColumn(name="stud_id"),
			inverseJoinColumns = @JoinColumn(name="course_id")
	)
	List<Course> course = new ArrayList<Course>();
	
	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

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
	
	public void addCourse(Course course) {
		this.course.add(course);
		course.getStudent().add(this);
	}
	
}