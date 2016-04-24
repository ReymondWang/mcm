package com.purplelight.mcm.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDaoImpl {
	private SessionFactory mSessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		mSessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return mSessionFactory.getCurrentSession();
	}
}
