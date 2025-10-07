package com.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Car;
import com.hibernate.model.Truck;
import com.hibernate.model.Vehicle;

public class HibernateUtil{
	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = null;
		try {
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			configuration.addAnnotatedClass(Car.class);
			configuration.addAnnotatedClass(Truck.class);
			configuration.addAnnotatedClass(Vehicle.class);
			sessionFactory = configuration.buildSessionFactory();
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}
		return sessionFactory;
	}
}