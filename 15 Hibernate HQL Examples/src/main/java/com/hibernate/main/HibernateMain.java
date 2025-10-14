package com.hibernate.main;

import java.sql.*;
import org.hibernate.*;
import com.hibernate.model.*;
import com.hibernate.utils.HibernateUtil;
import java.util.Scanner;

public class HibernateMain{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter username : ");
		String username = sc.nextLine();
		
		System.out.println("Enter email: ");
		String email = sc.next();
		
		System.out.println("Enter password: ");
		String password = sc.next();
		
		sc.nextLine();
		System.out.println("Enter address: ");
		String address = sc.nextLine();
		
		System.out.println("Enter salary: ");
		int salary = sc.nextInt();
		
		User obj = new User();
		obj.setUsername(username);
		obj.setEmail(email);
		obj.setPassword(password);
		obj.setAddress(address);
		obj.setSalary(salary);
		
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.persist(obj);
			tx.commit();
			System.out.println("Data Inserted Successfully");

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
