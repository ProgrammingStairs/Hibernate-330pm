package com.hibernate.main;

import java.sql.*;
import java.util.List;

import org.hibernate.*;

import com.hibernate.model.*;
import com.hibernate.utils.HibernateUtil;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class HibernateMain{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		/*
		Department dobj = new Department();
		dobj.setDeptname("Management Department");
	    
		Employee e1 = new Employee();
		e1.setEmpname("Mathew Math");
		
		Employee e2 = new Employee();
		e2.setEmpname("Mark");
		
		dobj.addDepartment(e1);
		dobj.addDepartment(e2);
		*/
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			//session.persist(dobj);
			
		//	String query = "select e.empname, d.deptname from Employee e JOIN e.department d";
			//String query = "select e.empname, d.deptname from Employee e INNER JOIN e.department d";
			//String query = "select e.empname, d.deptname from Department d LEFT JOIN d.employee e";
			String query = "select e.empname, d.deptname from Employee e RIGHT JOIN e.department d";
			
			
			/*
			 Query is used in org.hibernate here Query is Generic and Non generic with some methods, needs to remember with whom it is generic and non generic
			 TypedQuery is used in jakarta.persistence which is generic
			 */
			
			TypedQuery<Object[]> query1 = session.createQuery(query,Object[].class);
			List<Object[]> list = query1.getResultList();
			for(Object[] row : list) {
				System.out.println("Employee name : "+row[0]+" Department name : "+row[1]);
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