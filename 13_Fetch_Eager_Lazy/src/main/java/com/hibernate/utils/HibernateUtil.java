package com.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.StudentDemo;
import com.hibernate.model.PassportDemo;

public class HibernateUtil{
	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = null;
		try {
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			configuration.addAnnotatedClass(StudentDemo.class);
			configuration.addAnnotatedClass(PassportDemo.class);
			sessionFactory = configuration.buildSessionFactory();
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}
		return sessionFactory;
	}
}