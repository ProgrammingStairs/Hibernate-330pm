package com.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.User;
import com.hibernate.model.User1;

public class HibernateUtil{
	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = null;
		try {
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(User1.class);
			
			sessionFactory = configuration.buildSessionFactory();
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}
		return sessionFactory;
	}
}