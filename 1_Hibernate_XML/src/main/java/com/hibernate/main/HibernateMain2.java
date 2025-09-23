package com.hibernate.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.*;
import com.hibernate.utils.HibernateUtils;
public class HibernateMain2{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		Student student = new Student();
		student.setName("Andrew Anderson");
		student.setEmail("andrew@gmail.com");
		student.setPassword("andrew@123");
		student.setAddress("Indore MP");
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.persist(student);			
			tx.commit();
		}catch(Exception e) {
			System.out.println("Exception : "+e);
			if(tx!=null) {
				tx.rollback();
				System.out.println("Rollback occurs");
			}
		}
//		finally {
//			session.close();
//		}
		Student s = session.get(Student.class, student.getSid());
		if(s==null)
			System.out.println("No  record found");
		else {
			System.out.println("Student Id : "+s.getSid());
			System.out.println("Student Name : "+s.getName());
			System.out.println("Student Email : "+s.getEmail());
			System.out.println("Student Password : "+s.getPassword());
			System.out.println("Student Address: "+s.getAddress());
		}
	}
	public static void createDatabaseIfNotExist() {
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/";
		String USER = "root";
		String PASS = "root";
		String DATABASE = "hibernate330";		
		try{
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL,USER,PASS);
			Statement stmt = con.createStatement();
			
			String query = "create database if not exists "+DATABASE;
			stmt.execute(query);
			System.out.println("Database created successfully");
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("Exception : "+e);
		}
	}
}