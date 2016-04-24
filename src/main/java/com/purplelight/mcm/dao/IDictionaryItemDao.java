package com.purplelight.mcm.dao;

import java.util.List;

import com.purplelight.mcm.entity.DictionaryItem;

public interface IDictionaryItemDao {
	public List<DictionaryItem> getAll();
	public DictionaryItem getById(int id);
	public DictionaryItem getByCode(String code);
	public List<DictionaryItem>getByDictNameCode(String dictNameCode);
	public void addDictItem(DictionaryItem dictItem);
	public void updateDictItem(DictionaryItem dictItem);
	public void deleteDictItem(DictionaryItem dictItem);
}
