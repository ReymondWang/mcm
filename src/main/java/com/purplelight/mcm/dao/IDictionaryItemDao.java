package com.purplelight.mcm.dao;

import java.util.List;

import com.purplelight.mcm.entity.DictionaryItem;

public interface IDictionaryItemDao extends IBaseDao<DictionaryItem, Integer> {
	public DictionaryItem getByCode(String code);
	public List<DictionaryItem>getByDictNameCode(String dictNameCode);
}
