package com.purplelight.mcm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.purplelight.mcm.dao.IBaseDao;
import com.purplelight.mcm.exception.McmException;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T, ID extends Serializable> implements IBaseDao<T, ID> {
	private SessionFactory mSessionFactory;
	
	private Class<T> mEntityClass;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		mSessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return mSessionFactory.getCurrentSession();
	}
	
	public Class<T> getEntityClass(){
		if (mEntityClass == null) {
			mEntityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return mEntityClass;
	}

	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	@Override
	public T loadById(ID id) {
		return (T)getSession().load(getEntityClass(), id);
	}

	@Override
	public T getById(ID id) {
		return (T)getSession().get(getEntityClass(), id);
	}

	@Override
	public boolean contains(T t) {
		return getSession().contains(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}

	@Override
	public boolean deleteById(ID id) {
		T t = getById(id);
		if (t != null){
			delete(t);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll(Collection<T> list) {
		for(T entity : list){
			delete(entity);
		}
	}

	@Override
	public List<T> find(Strategy strategy) throws McmException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Query query = getSession().createQuery(strategy.generateHql());
		query.setProperties(strategy.getQueryProperties());
		return (List<T>)query.list();
	}

	@Override
	public PageInfo<T> find(Strategy strategy, int startPos, int pageNo, int pageSize) 
			throws McmException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		PageInfo<T> page = new PageInfo<T>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		String hql = strategy.generateHql();
		String hqlForCount = strategy.getHqlForCount();
		
		Query cntQuery = getSession().createQuery(hqlForCount);
		cntQuery.setProperties(strategy.getQueryProperties());
		page.setTotalCount(new Integer(cntQuery.uniqueResult().toString()).intValue());
		
		Query query = getSession().createQuery(hql);
		query.setProperties(strategy.getQueryProperties());
		query.setFirstResult(startPos);
		query.setMaxResults(pageNo * pageSize);
		
		page.setResult((List<T>)query.list());
		
		return page;
	}

	@Override
	public List<T> getAll() {
		return (List<T>)getSession().createCriteria(getEntityClass()).list();
	}
}
