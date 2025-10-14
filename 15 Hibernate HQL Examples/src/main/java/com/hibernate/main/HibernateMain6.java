// example showing the concept of aggregate functions
package com.hibernate.main;

import java.sql.*;
import java.util.List;

import org.hibernate.*;
import org.hibernate.query.Query;

import com.hibernate.model.*;
import com.hibernate.utils.HibernateUtil;

public class HibernateMain6{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			/*
			String hqlQuery = "select count(*) from User u";
			Query<Long> query =  session.createQuery(hqlQuery,Long.class);			
			Long count = query.getSingleResult();
			System.out.println("No. of Records : "+count);
			*/
			
			/*
			String hqlQuery = "select min(u.salary) from User u";
			Query<Integer> query =  session.createQuery(hqlQuery,Integer.class);			
//			Integer minSalary = query.getSingleResult();
//			System.out.println("Minimum Salary: "+minSalary);
			List<Integer> data = query.getResultList();
			System.out.println("Minimum Salary: "+data.get(0));
			*/
			
			/*
			String hqlQuery = "select max(u.salary) from User u";
			Query<Integer> query =  session.createQuery(hqlQuery,Integer.class);			
			Integer maxSalary = query.getSingleResult();
			System.out.println("Maximum Salary: "+maxSalary);
			*/
			
			/*
			String hqlQuery = "select sum(u.salary) from User u";
			Query<Long> query =  session.createQuery(hqlQuery,Long.class);			
			Long sumSalary = query.getSingleResult();
			System.out.println("Sum of Salary: "+sumSalary);
			*/
			
			/*
			String hqlQuery = "select avg(u.salary) from User u";
			Query<Double> query =  session.createQuery(hqlQuery,Double.class);			
			Double avgSalary = query.getSingleResult();
			System.out.println("Average Salary: "+avgSalary);
			*/
			
			// example of group by
			
			String hqlQuery = "select u.address,count(*) from User u group by u.address";
			Query<Object[]> query =  session.createQuery(hqlQuery,Object[].class);			
			List<Object[]> list= query.getResultList();
			for(Object[] row : list) {
				System.out.println("Address : "+row[0]+" Count : "+row[1]);
			}
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
