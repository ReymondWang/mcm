package com.purplelight.mcm.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.purplelight.mcm.dao.IDictionaryItemDao;
import com.purplelight.mcm.entity.DictionaryItem;

public class DictionaryItemDaoImpl extends BaseDaoImpl implements IDictionaryItemDao {

	@Override
	public List<DictionaryItem> getAll() {
		@SuppressWarnings("unchecked")
		List<DictionaryItem> retList = (List<DictionaryItem>)getSession().createQuery("select di from DictionaryItem di").list();
		if (retList != null){
			return retList;
		}
		return new ArrayList<DictionaryItem>();
	}

	@Override
	public DictionaryItem getById(int id) {
		Query query = getSession().createQuery("select di from DictionaryItem di where di.id = :id");
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		Iterator<DictionaryItem> i = (Iterator<DictionaryItem>)query.iterate();
		if (i.hasNext()){
			return i.next();
		}
		
		return null;
	}

	@Override
	public DictionaryItem getByCode(String code) {
		Query query = getSession().createQuery("select di from DictionaryItem di where di.dictItemCode = :dictItemCode");
		query.setParameter("dictItemCode", code);
		
		@SuppressWarnings("unchecked")
		Iterator<DictionaryItem> i = (Iterator<DictionaryItem>)query.iterate();
		if (i.hasNext()){
			return i.next();
		}
		
		return null;
	}

	@Override
	public List<DictionaryItem> getByDictNameCode(String dictNameCode) {
		Query query = getSession().createQuery("select di from DictionaryItem di where di.dictNameCode = :dictNameCode");
		query.setParameter("dictNameCode", dictNameCode);
		
		@SuppressWarnings("unchecked")
		List<DictionaryItem> retList = (List<DictionaryItem>)query.list();
		if (retList != null){
			return retList;
		}
		
		return new ArrayList<DictionaryItem>();
	}

	@Override
	public void addDictItem(DictionaryItem dictItem) {
		getSession().save(dictItem);
	}

	@Override
	public void updateDictItem(DictionaryItem dictItem) {
		getSession().update(dictItem);
	}

	@Override
	public void deleteDictItem(DictionaryItem dictItem) {
		getSession().delete(dictItem);
	}

}
