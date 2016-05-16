package com.purplelight.mcm.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.purplelight.mcm.dao.IDictionaryItemDao;
import com.purplelight.mcm.entity.DictionaryItem;

@SuppressWarnings("unchecked")
public class DictionaryItemDaoImpl extends BaseDaoImpl<DictionaryItem, Integer> implements IDictionaryItemDao {

	@Override
	public DictionaryItem getByCode(String code) {
		Query query = getSession().createQuery("select di from DictionaryItem di where di.dictItemCode = :dictItemCode");
		query.setParameter("dictItemCode", code);
		
		Iterator<DictionaryItem> i = (Iterator<DictionaryItem>)query.iterate();
		if (i.hasNext()){
			return i.next();
		}
		
		return null;
	}

	@Override
	public List<DictionaryItem> getByDictNameCode(String dictNameCode) {
		Query query = getSession().createQuery("select di from DictionaryItem di where di.dictName.dictNameCode = :dictNameCode");
		query.setParameter("dictNameCode", dictNameCode);
		
		List<DictionaryItem> retList = (List<DictionaryItem>)query.list();
		if (retList != null){
			return retList;
		}
		
		return new ArrayList<DictionaryItem>();
	}
	
}
