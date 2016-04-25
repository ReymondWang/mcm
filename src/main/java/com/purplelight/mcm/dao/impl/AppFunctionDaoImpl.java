package com.purplelight.mcm.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.purplelight.mcm.dao.IAppFunctionDao;
import com.purplelight.mcm.entity.AppFunction;
import com.purplelight.mcm.query.Strategy;

public class AppFunctionDaoImpl extends BaseDaoImpl implements IAppFunctionDao {

	@Override
	public List<AppFunction> getAll() {
		@SuppressWarnings("unchecked")
		List<AppFunction> retList = (List<AppFunction>)getSession().createQuery("select af from AppFunction af").list();
		if (retList != null){
			return retList;
		}
		
		return new ArrayList<AppFunction>();
	}

	@Override
	public AppFunction getById(int id) {
		Query query = getSession().createQuery("select af from AppFunction af where af.id = :id");
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		Iterator<AppFunction> it = (Iterator<AppFunction>)query.iterate();
		if (it.hasNext()){
			return it.next();
		}
		
		return null;
	}

	@Override
	public List<AppFunction> getByFragment(int fragment) {
		Query query = getSession().createQuery("select af from AppFunction af where af.fragment = :fragment");
		query.setParameter("fragment", fragment);
		
		@SuppressWarnings("unchecked")
		List<AppFunction> retList = (List<AppFunction>)query.list();
		if (retList != null){
			return retList;
		}
		
		return new ArrayList<AppFunction>();
	}

	@Override
	public List<AppFunction> getByFragmentAndPart(int fragment, int part) {
		Query query = getSession().createQuery("select af from AppFunction af where af.fragment = :fragment and af.part = :part");
		query.setParameter("fragment", fragment);
		query.setParameter("part", part);
		
		@SuppressWarnings("unchecked")
		List<AppFunction> retList = (List<AppFunction>)query.list();
		if (retList != null){
			return retList;
		}
		
		return new ArrayList<AppFunction>();
	}

	@Override
	public List<AppFunction> getByFragmentAndPartAndType(int fragment, int part, int functionType) {
		Query query = getSession().createQuery("select af from AppFunction af where af.fragment = :fragment and af.part = :part and af.functionType = :functionType");
		query.setParameter("fragment", fragment);
		query.setParameter("part", part);
		query.setParameter("functionType", functionType);
		
		@SuppressWarnings("unchecked")
		List<AppFunction> retList = (List<AppFunction>)query.list();
		if (retList != null){
			return retList;
		}
		
		return new ArrayList<AppFunction>();
	}

	@Override
	public List<AppFunction> find(Strategy strategy) throws Exception {
		Query query = getSession().createQuery(strategy.generateHql());
		query.setProperties(strategy.getQueryProperties());
		
		@SuppressWarnings("unchecked")
		List<AppFunction> retList = (List<AppFunction>)query.list();
		if (retList != null){
			return retList;
		}
		
		return new ArrayList<AppFunction>();
	}

	@Override
	public void add(AppFunction appFunction) {
		getSession().save(appFunction);
	}

	@Override
	public void update(AppFunction appFunction) {
		getSession().update(appFunction);
	}

	@Override
	public void delete(AppFunction appFunction) {
		getSession().delete(appFunction);
	}

}
