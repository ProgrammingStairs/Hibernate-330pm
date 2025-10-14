package com.hibernate.main;

import java.sql.*;
import java.util.List;

import org.hibernate.*;
import org.hibernate.query.Query;

import com.hibernate.model.*;
import com.hibernate.utils.HibernateUtil;

public class HibernateMain4{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

//			String hqlQuery = "select u from User u where u.salary>:sal";
//			String hqlQuery = "select u from User u where u.salary>:sal or u.address=:add";
			String hqlQuery = "select u from User u where u.salary>:sal and u.address=:add";

			Query<User> list =  session.createQuery(hqlQuery,User.class);
			list.setParameter("sal", 1000);
			list.setParameter("add", "Indore Madhya Pradesh");

			List<User> data = list.getResultList();
			for(User user : data) {
				System.out.println("\nUsername : "+user.getUsername());
			}

			
			/*
			String hqlQuery = "select u.username from User u";
			Query<String> list =  session.createQuery(hqlQuery,String.class);
			List<String> data = list.getResultList();
			for(String user : data) {
				System.out.println("\nUsername : "+user);
			}
			*/
			/*
			String hqlQuery = "select u.salary from User u";
			Query<Integer> list =  session.createQuery(hqlQuery,Integer.class);
			List<Integer> data = list.getResultList();
			for(Integer salary : data) {
				System.out.println("\nSalary: "+salary);
			}
			*/
			/*
			String hqlQuery = "select u.username,u.salary from User u";
			Query<Object[]> list =  session.createQuery(hqlQuery,Object[].class);
			List<Object[]> data = list.getResultList();
			for(Object[] row : data) {
				System.out.println(row[0]);
				System.out.println(row[1]);
			}
			*/
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
