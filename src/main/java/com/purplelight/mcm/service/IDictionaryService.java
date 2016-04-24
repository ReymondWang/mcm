package com.purplelight.mcm.service;

import java.util.List;

import com.purplelight.mcm.entity.DictionaryItem;
import com.purplelight.mcm.entity.DictionaryName;

public interface IDictionaryService {
	public List<DictionaryName> getAllDictNames();
	public List<DictionaryItem> getDictItemsByDictNameCode(String dictNameCode);
	public List<DictionaryItem> getDictItemsByDictNameCodeWithBlank(String dictNameCode);
}
