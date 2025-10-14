// example showing the concept of named query 
package com.hibernate.main;

import java.sql.*;
import java.util.List;

import org.hibernate.*;
import org.hibernate.query.Query;

import com.hibernate.model.*;
import com.hibernate.utils.HibernateUtil;

public class HibernateMain7{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
//			Query<User1> query =  session.createNamedQuery("User1.findByEmailId",User1.class);
//			query.setParameter("email", "peter@gmail.com");
			
			Query<User1> query =  session.createNamedQuery("User1.findByName",User1.class);
			query.setParameter("name", "peter");
			
			
//			List<User1> data = query.getResultList();
//			for(User1 user : data) {
//				System.out.println("\nUsername : "+user.getUsername());
//				System.out.println("Email : "+user.getEmail());
//				System.out.println("Password : "+user.getPassword());
//				System.out.println("Address : "+user.getAddress());
//				System.out.println("Salary : "+user.getSalary());
//			}
			User1 obj = query.getSingleResult();
			System.out.println("\nUsername : "+obj.getUsername());
			System.out.println("Email : "+obj.getEmail());
			System.out.println("Password : "+obj.getPassword());
			System.out.println("Address : "+obj.getAddress());
			System.out.println("Salary : "+obj.getSalary());
		
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
