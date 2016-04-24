package com.purplelight.mcm.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.purplelight.mcm.dao.IDictionaryNameDao;
import com.purplelight.mcm.entity.DictionaryName;

public class DictionaryNameDaoImpl extends BaseDaoImpl implements IDictionaryNameDao {

	@Override
	public List<DictionaryName> getAll() {
		@SuppressWarnings("unchecked")
		List<DictionaryName> retList = (List<DictionaryName>)getSession().createQuery("select dn from DictionaryName dn").list();
		if (retList != null){
			return retList;
		}
		return new ArrayList<DictionaryName>();
	}

	@Override
	public DictionaryName getById(int id) {
		Query query = getSession().createQuery("select dn from DictionaryName dn where dn.id = :id");
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		Iterator<DictionaryName> it = (Iterator<DictionaryName>)query.list().iterator();
		if (it.hasNext()){
			return it.next();
		}
		
		return null;
	}

	@Override
	public DictionaryName getByCode(String code) {
		Query query = getSession().createQuery("select dn from DictionaryName dn where dn.dictNameCode = :dictNameCode");
		query.setParameter("dictNameCode", code);
		
		@SuppressWarnings("unchecked")
		Iterator<DictionaryName> it = (Iterator<DictionaryName>)query.list().iterator();
		if (it.hasNext()){
			return it.next();
		}
		
		return null;
	}

	@Override
	public void addDictName(DictionaryName dictName) {
		getSession().save(dictName);
	}

	@Override
	public void updateDictName(DictionaryName dictName) {
		getSession().update(dictName);
	}

	@Override
	public void deleteDictName(DictionaryName dictName) {
		getSession().delete(dictName);
	}

}
