package com.purplelight.mcm.dao;

import com.purplelight.mcm.entity.DictionaryName;

public interface IDictionaryNameDao extends IBaseDao<DictionaryName, Integer> {
	public DictionaryName getByCode(String code);
}
