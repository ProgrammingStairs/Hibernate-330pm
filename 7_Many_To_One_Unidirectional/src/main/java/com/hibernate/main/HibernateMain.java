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
/* Note : 
 * Many to one bidirectional <---is equivalent to---> one to many bidirectional 
 * */
public class HibernateMain{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		
		Department dobj = new Department();
		dobj.setDeptname("Finance Department");
	    
		Employee e1 = new Employee();
		e1.setEmpname("Andrew Anderson");
		e1.setDepartment(dobj);
		
		Employee e2 = new Employee();
		e2.setEmpname("Peter Parker");
		e2.setDepartment(dobj);
		
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.persist(dobj);
			session.persist(e1);
			session.persist(e2);
			
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