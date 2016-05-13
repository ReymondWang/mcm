package com.purplelight.mcm.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.purplelight.mcm.dao.IOutterSystemDao;
import com.purplelight.mcm.entity.OutterSystem;

public class OutterSystemDaoImpl extends BaseDaoImpl<OutterSystem, Integer> implements IOutterSystemDao {

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

}
