package com.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.hibernate.model.*;
public class HibernateUtils {
	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = null;
		try {
			Configuration configuration = new Configuration().configure();
			configuration.addAnnotatedClass(Student.class);
			configuration.addAnnotatedClass(Passport.class);
			sessionFactory = configuration.buildSessionFactory();
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}
		return sessionFactory;
	}
}