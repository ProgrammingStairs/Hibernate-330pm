package com.hibernate.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.*;
import com.hibernate.utils.HibernateUtils;
public class HibernateMain{
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
			// If exception occurs, then there will be chances of rollback
//			session.flush();
			tx.commit();
		}catch(Exception e) {
			System.out.println("Exception : "+e);
			if(tx!=null) {
				tx.rollback();
				System.out.println("Rollback occurs");
			}
		}finally {
			session.close();
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