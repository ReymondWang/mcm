package com.purplelight.mcm.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.purplelight.mcm.dao.IOutterSystemDao;
import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;

public class OutterSystemDaoImpl extends BaseDaoImpl implements IOutterSystemDao {

	@Override
	public List<OutterSystem> getAll() {
		@SuppressWarnings("unchecked")
		List<OutterSystem> retList = (List<OutterSystem>)getSession().createQuery("select os from OutterSystem os").list();
		if (retList != null){
			return retList;
		}
		
		return null;
	}

	@Override
	public OutterSystem getById(int id) {
		Query query = getSession().createQuery("select os from OutterSystem os where os.id = :id");
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		Iterator<OutterSystem> it = (Iterator<OutterSystem>)query.list().iterator();
		if (it.hasNext()){
			return it.next();
		}
		
		return null;
	}

	@Override
	public OutterSystem getByCode(String code) {
		Query query = getSession().createQuery("select os from OutterSystem os where os.systemCode = :systemCode");
		query.setParameter("systemCode", code);
		
		@SuppressWarnings("unchecked")
		Iterator<OutterSystem> it = (Iterator<OutterSystem>)query.list().iterator();
		if (it.hasNext()){
			return it.next();
		}
		
		return null;
	}

	@Override
	public List<OutterSystem> getBySystemTypeCode(String typeCode) {
		Query query = getSession().createQuery("select os from OutterSystem os where os.systemType = :systemType");
		query.setParameter("systemType", typeCode);
		
		@SuppressWarnings("unchecked")
		List<OutterSystem> retList = (List<OutterSystem>)query.list();
		if (retList != null){
			return retList;
		}
		
		return null;
	}

	@Override
	public void addOutterSystem(OutterSystem outterSystem) {
		getSession().save(outterSystem);
	}

	@Override
	public void updateOutterSystem(OutterSystem outterSystem) {
		getSession().update(outterSystem);
	}

	@Override
	public void deleteOutterSystem(OutterSystem outterSystem) {
		getSession().delete(outterSystem);
	}

	@Override
	public List<OutterSystem> find(Strategy strategy) throws Exception {
		Query query = getSession().createQuery(strategy.generateHql());
		query.setProperties(strategy.getQueryProperties());
		
		@SuppressWarnings("unchecked")
		List<OutterSystem> retList = (List<OutterSystem>)query.list();
		if (retList != null){
			return retList;
		}
		
		return new ArrayList<OutterSystem>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<OutterSystem> findByPageInfo(Strategy queryStrategy, int startPos, int pageNo, int pageSize)
			throws Exception {
		PageInfo<OutterSystem> page = new PageInfo<OutterSystem>();
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
		page.setResult((List<OutterSystem>)query.list());
		
		return page;
	}

}
