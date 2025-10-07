package com.hibernate.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Department;
import com.hibernate.model.Employee;
import com.hibernate.utils.HibernateUtil;

public class HibernateMain{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		
		Department dobj = new Department();
		dobj.setDeptname("Finance Department");
	    
		Employee e1 = new Employee();
		e1.setEmpname("Andrew Anderson");
		
		Employee e2 = new Employee();
		e2.setEmpname("Peter Parker");
		
		dobj.getEmployee().add(e1);
		dobj.getEmployee().add(e2);
		
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.persist(dobj); // we can write session.persist(dobj); after session.persist(e1); and session.persist(e2); also, it works in similar manner 

			session.persist(e1); // we only need to write this line when we do not write cascade in annotation
			session.persist(e2); // we only need to write this line when we do not write cascade in annotation

			
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