package com.javaee.Hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
	
	private Session session;

	public HibernateSession() {
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
	}
	
	public Session getSession() {
        return session;
	}
	
	public void closeSession() {
		session.close();
	}

}
