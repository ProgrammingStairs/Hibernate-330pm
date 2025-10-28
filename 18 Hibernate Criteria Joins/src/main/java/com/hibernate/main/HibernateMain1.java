// first level caching example
package com.hibernate.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Department;
import com.hibernate.model.Employee;
import com.hibernate.utils.HibernateUtil;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public class HibernateMain1{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			// on first time it runs query
			Department d =  session.get(Department.class, 1);
			System.out.println("Department name : "+d.getDeptname());
			
			// on second time it doesn't run query, it fetch data from hibernate cache, which is known as first level caching
			Department d1 =  session.get(Department.class, 1);
			System.out.println("Department name : "+d1.getDeptname());

			/*
			 * Hibernate: 
    select
        d1_0.deptid,
        d1_0.dept_name 
    from
        dep_onemany_bi d1_0 
    where
        d1_0.deptid=?
Department name : Finance Department
Department name : Finance Department
			 * */
			
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