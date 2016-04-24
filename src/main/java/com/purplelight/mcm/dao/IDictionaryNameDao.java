package com.purplelight.mcm.dao;

import java.util.List;

import com.purplelight.mcm.entity.DictionaryName;

public interface IDictionaryNameDao {
	public List<DictionaryName> getAll();
	public DictionaryName getById(int id);
	public DictionaryName getByCode(String code);
	public void addDictName(DictionaryName dictName);
	public void updateDictName(DictionaryName dictName);
	public void deleteDictName(DictionaryName dictName);
}
