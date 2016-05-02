package com.purplelight.mcm.service;

import java.util.List;

import com.purplelight.mcm.entity.DictionaryItem;
import com.purplelight.mcm.entity.DictionaryName;
import com.purplelight.mcm.entity.SystemUser;

public interface IDictionaryService {
	public List<DictionaryName> getAllDictNames();
	public List<DictionaryItem> getDictItemsByDictNameCode(String dictNameCode);
	public List<DictionaryItem> getDictItemsByDictNameCodeWithBlank(String dictNameCode);
	public DictionaryItem getDictItemByCode(String dictItemCode);
	public void addDictItem(DictionaryItem item, SystemUser loginedUser);
	public void updateDictItem(DictionaryItem item, SystemUser loginedUser) throws Exception;
	public void deleteDictItem(DictionaryItem item);
	public boolean hasDictItemCode(String dictItemCode);
}
