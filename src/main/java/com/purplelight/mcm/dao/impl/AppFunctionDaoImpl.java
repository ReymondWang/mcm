package com.purplelight.mcm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.purplelight.mcm.dao.IAppFunctionDao;
import com.purplelight.mcm.entity.AppFunction;

@SuppressWarnings("unchecked")
public class AppFunctionDaoImpl extends BaseDaoImpl<AppFunction, Integer> implements IAppFunctionDao {

	@Override
	public List<AppFunction> getByFragment(int fragment) {
		Criteria criteria = getSession().createCriteria(AppFunction.class);
		criteria.add(Restrictions.eq("fragment", fragment));
		List<AppFunction> retList = criteria.list();
		if (retList != null){
			return retList;
		}
		
		return new ArrayList<AppFunction>();
	}

	@Override
	public List<AppFunction> getByFragmentAndPart(int fragment, int part) {
		Criteria criteria = getSession().createCriteria(AppFunction.class);
		criteria.add(Restrictions.eq("fragment", fragment));
		criteria.add(Restrictions.eq("part", part));
		List<AppFunction> retList = criteria.list();
		if (retList != null){
			return retList;
		}
		
		return new ArrayList<AppFunction>();
	}

	@Override
	public List<AppFunction> getByFragmentAndPartAndType(int fragment, int part, int functionType) {
		Criteria criteria = getSession().createCriteria(AppFunction.class);
		criteria.add(Restrictions.eq("fragment", fragment));
		criteria.add(Restrictions.eq("part", part));
		criteria.add(Restrictions.eq("functionType", functionType));
		List<AppFunction> retList = criteria.list();
		if (retList != null){
			return retList;
		}
		
		return new ArrayList<AppFunction>();
	}
}
