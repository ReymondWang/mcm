package com.purplelight.mcm.dao.impl;

import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.purplelight.mcm.dao.ISystemUserDao;
import com.purplelight.mcm.entity.SystemUser;

@SuppressWarnings("unchecked")
public class SystemUserDaoImpl extends BaseDaoImpl<SystemUser, Integer> implements ISystemUserDao {

	public SystemUser getByUserCode(String userCode) {
		Query query = getSession().createQuery("select u from SystemUser u where u.userCode = :userCode");
		query.setParameter("userCode", userCode);
		Iterator<SystemUser> i = (Iterator<SystemUser>)query.iterate();
		if (i.hasNext()){
			return i.next();
		}
		
		return null;
	}

	public SystemUser getByEmail(String email) {
		Query query = getSession().createQuery("select u from SystemUser u where u.email = :email");
		query.setParameter("email", email);
		Iterator<SystemUser> i = (Iterator<SystemUser>)query.iterate();
		if (i.hasNext()){
			return i.next();
		}
		
		return null;
	}

	public SystemUser getByPhone(String phone) {
		Query query = getSession().createQuery("select u from SystemUser u where u.phone = :phone");
		query.setParameter("phone", phone);
		Iterator<SystemUser> i = (Iterator<SystemUser>)query.iterate();
		if (i.hasNext()){
			return i.next();
		}
		
		return null;
	}

	@Override
	public String getPassword(String orgStr) {
		String sql = "{? = call password(?)}";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setString(0, orgStr);
		return String.valueOf(query.uniqueResult());
	}

	@Override
	public void updatePassword(int userId, String password) {
		SystemUser user = getById(userId);
		user.setPassword(getPassword(password));
		saveOrUpdate(user);
	}

}
