package com.purplelight.mcm.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.purplelight.mcm.dao.ISystemUserDao;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;

public class SystemUserDaoImpl extends BaseDaoImpl implements ISystemUserDao {

	public void addUser(SystemUser user) {
		getSession().save(user);
	}

	public void updateUser(SystemUser user) {
		getSession().update(user);
	}

	public void deleteUser(SystemUser user) {
		getSession().delete(user);
	}

	@SuppressWarnings("unchecked")
	public List<SystemUser> getAll() {
		List<SystemUser> list = (List<SystemUser>)getSession().createQuery("select u from SystemUser u").list();
		if (list != null && list.size() > 0){
			return list;
		}
		
		return new ArrayList<>();
	}

	@SuppressWarnings("unchecked")
	public SystemUser getById(int id) {
		Query query = getSession().createQuery("select u from SystemUser u where u.id = :id");
		query.setParameter("id", id);
		Iterator<SystemUser> i = (Iterator<SystemUser>)query.iterate();
		if (i.hasNext()){
			return i.next();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public SystemUser getByUserCode(String userCode) {
		Query query = getSession().createQuery("select u from SystemUser u where u.userCode = :userCode");
		query.setParameter("userCode", userCode);
		Iterator<SystemUser> i = (Iterator<SystemUser>)query.iterate();
		if (i.hasNext()){
			return i.next();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public SystemUser getByEmail(String email) {
		Query query = getSession().createQuery("select u from SystemUser u where u.email = :email");
		query.setParameter("email", email);
		Iterator<SystemUser> i = (Iterator<SystemUser>)query.iterate();
		if (i.hasNext()){
			return i.next();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	public SystemUser getByPhone(String phone) {
		Query query = getSession().createQuery("select u from SystemUser u where u.phone = :phone");
		query.setParameter("phone", phone);
		Iterator<SystemUser> i = (Iterator<SystemUser>)query.iterate();
		if (i.hasNext()){
			return i.next();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SystemUser> find(Strategy queryStrategy) throws Exception {
		Query query = getSession().createQuery(queryStrategy.generateHql());
		query.setProperties(queryStrategy.getQueryProperties());
		
		return (List<SystemUser>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	public PageInfo<SystemUser> findByPageInfo(Strategy queryStrategy, int startPos, int pageNo, int pageSize) throws Exception{
		PageInfo<SystemUser> page = new PageInfo<SystemUser>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		String hql = queryStrategy.generateHql();
		String hqlForCount = queryStrategy.getHqlForCount();
		
		Query cntQuery = getSession().createQuery(hqlForCount);
		cntQuery.setProperties(queryStrategy.getQueryProperties());
		page.setTotalCount(new Integer(cntQuery.uniqueResult().toString()).intValue());
		
		Query query = getSession().createQuery(hql);
		query.setProperties(queryStrategy.getQueryProperties());
		query.setFirstResult(startPos);
		query.setMaxResults(pageNo * pageSize);
		page.setResult((List<SystemUser>)query.list());
		
		return page;
	}

	@Override
	public String getPassword(String orgStr) {
		String sql = "{? = call password(?)}";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setString(0, orgStr);
		return String.valueOf(query.uniqueResult());
	}

}
