package com.hibernate.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Passport;
import com.hibernate.model.Student;
import com.hibernate.utils.HibernateUtils;
public class HibernateMain{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		Student student = new Student();
		student.setName("Mark");
		student.setEmail("mark@gmail.com");
		student.setPassword("mark@123");
		student.setAddress("Indore");
		
		Passport passport = new Passport();
		passport.setPassportNumber("STUD123PASS104");
		passport.setStudent(student);
		student.setPassport(passport);
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			// session.persist(passport); // if student.setPassport(passport); is commented and  session.persist(passport); is also commented then passport table will not add entry but if student.setPassport(passport); is commented and session.persist(passport); is uncommented then entry will takes place 
			session.persist(student);
			// If exception occurs, then there will be chances of rollback
//			session.flush();
			tx.commit();
			
			System.out.println("pid : "+passport.getPid());
			Passport pass = session.get(Passport.class, passport.getPid());
			System.out.println("pass : "+pass);

			System.out.println("Passport Id : "+pass.getPid());
			System.out.println("Passport Number : "+pass.getPassportNumber());
			System.out.println("Passport Student : "+pass.getStudent());
			

			Student stud = session.get(Student.class, student.getSid());
			System.out.println("Student Id : "+stud.getSid());
			System.out.println("Student Name : "+stud.getName());
			System.out.println("Student Email : "+stud.getEmail());
			System.out.println("Student Password : "+stud.getPassword());
			System.out.println("Student Address : "+stud.getAddress());
			System.out.println("Student passport: "+stud.getPassport());

			
		}catch(Exception e) {
			System.out.println("Exception : "+e);
			if(tx!=null) {
				tx.rollback();
				System.out.println("Rollback occurs");
			}
		}finally {
			session.close();
		}
	}
	public static void createDatabaseIfNotExist() {
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/";
		String USER = "root";
		String PASS = "root";
		String DATABASE = "hibernate330";		
		try{
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL,USER,PASS);
			Statement stmt = con.createStatement();
			
			String query = "create database if not exists "+DATABASE;
			stmt.execute(query);
			System.out.println("Database created successfully");
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println("Exception : "+e);
		}
	}
}