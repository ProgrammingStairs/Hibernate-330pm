package com.hibernate.main;

import java.sql.*;
import java.util.List;

import org.hibernate.*;
import org.hibernate.query.Query;

import com.hibernate.model.*;
import com.hibernate.utils.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class HibernateMain7{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			//query.select(root).orderBy(criteriaBuilder.asc(root.get("salary")));	
			query.select(root).orderBy(criteriaBuilder.desc(root.get("salary")));	
			
			
			Query<User> list =  session.createQuery(query);
			List<User> data = list.getResultList();
			for(User user : data) {
				System.out.println("\nUserId : "+user.getUid());
				System.out.println("Username : "+user.getUsername());
				System.out.println("Email: "+user.getEmail());
				System.out.println("Password: "+user.getPassword());
				System.out.println("Address  : "+user.getAddress());
				System.out.println("Salary : "+user.getSalary());
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
