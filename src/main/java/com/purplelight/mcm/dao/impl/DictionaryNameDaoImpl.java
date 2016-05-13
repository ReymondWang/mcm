package com.purplelight.mcm.dao.impl;

import java.util.Iterator;
import org.hibernate.Query;

import com.purplelight.mcm.dao.IDictionaryNameDao;
import com.purplelight.mcm.entity.DictionaryName;

@SuppressWarnings("unchecked")
public class DictionaryNameDaoImpl extends BaseDaoImpl<DictionaryName, Integer> implements IDictionaryNameDao {

	@Override
	public DictionaryName getByCode(String code) {
		Query query = getSession().createQuery("select dn from DictionaryName dn where dn.dictNameCode = :dictNameCode");
		query.setParameter("dictNameCode", code);
		
		Iterator<DictionaryName> it = (Iterator<DictionaryName>)query.list().iterator();
		if (it.hasNext()){
			return it.next();
		}
		
		return null;
	}

}
