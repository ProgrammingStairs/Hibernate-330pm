package com.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Department;
import com.hibernate.model.Employee;

public class HibernateUtil{
	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = null;
		try {
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			configuration.addAnnotatedClass(Department.class);
			configuration.addAnnotatedClass(Employee.class);
			sessionFactory = configuration.buildSessionFactory();
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}
		return sessionFactory;
	}
}