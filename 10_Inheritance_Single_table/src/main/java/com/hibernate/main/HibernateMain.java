package com.hibernate.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Car;
import com.hibernate.model.Truck;
import com.hibernate.model.Vehicle;

import com.hibernate.utils.HibernateUtil;

public class HibernateMain{
	public static void main(String args[]) {
		createDatabaseIfNotExist();
		Vehicle v1 = new Vehicle();
		v1.setVehicleName("FourWheeler");
		
		Car c1 = new Car();
		c1.setDoors(4);
		c1.setVehicleName("Maruti");
		
		Truck t1 = new Truck();
		t1.setContainer(4);
		t1.setVehicleName("ForceMotors");
		
		Transaction tx = null;
		try {
			Session session =  HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();

			session.persist(c1);
			session.persist(t1);
			session.persist(v1);
			
			tx.commit();
			
			List<Vehicle> list = session.createQuery("From Vehicle", Vehicle.class).getResultList();
			System.out.println(list);
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