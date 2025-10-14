package com.hibernate.main;

import java.sql.*;
import java.util.List;

import org.hibernate.*;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import com.hibernate.model.*;
import com.hibernate.utils.HibernateUtil;

public class HibernateMain2{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			String hqlQuery = "update User set username=:uname, password=:pass, address=:address, salary=:salary where email=:email";
//			Query<User> query = session.createQuery(hqlQuery);
//			Query<User> query = session.createQuery(hqlQuery,null);
			MutationQuery query = session.createMutationQuery(hqlQuery);
			query.setParameter("uname", "Andy");
			query.setParameter("pass", "12345");
			query.setParameter("address", "Austria");
			query.setParameter("salary", 8989);
			query.setParameter("email", "andrew@gmail.com");
			
			int affectedRow = query.executeUpdate();
			System.out.println("Data Updated successfully : "+affectedRow);
			
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
