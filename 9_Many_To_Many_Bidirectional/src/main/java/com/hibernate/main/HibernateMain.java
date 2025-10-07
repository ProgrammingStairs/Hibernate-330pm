package com.hibernate.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Course;
import com.hibernate.model.Student;
import com.hibernate.utils.HibernateUtil;

public class HibernateMain{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		
		Student s1 = new Student();
		s1.setName("Andrew Anderson");
		
		Student s2 = new Student();
		s2.setName("Peter Parker");
		
		Course c1 = new Course();
		c1.setName("Java Programming Language");
		
		Course c2 = new Course();
		c2.setName("C Programming Language");
		
		s1.addCourse(c1);
		s1.addCourse(c2);
		
		s2.addCourse(c1);
		
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.persist(c1);
			session.persist(c2);
			session.persist(s1);
			session.persist(s2);
			
			tx.commit();
		}catch(Exception e) {
			System.out.println("Exception : "+e);
			if(tx!=null) {
				tx.rollback();
				System.out.println("Rollback takes place..!!");
			}
		}
	}
	public static void createDatabaseIfNotExist() {
		String URL = "jdbc:mysql://localhost:3306/";
		String DATABASE = "hibernate330";
		String USER = "root";
		String PASS = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			if(con!=null)
				System.out.println("Connection established Successfully");
			String query = "create database if not exists "+DATABASE;
			Statement stmt = con.createStatement();
			stmt.execute(query);
			System.out.println("Database created succesfully");
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("Exception : "+e);
		}
	}
}