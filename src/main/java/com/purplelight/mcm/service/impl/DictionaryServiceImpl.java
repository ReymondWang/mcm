package com.purplelight.mcm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.dao.IDictionaryItemDao;
import com.purplelight.mcm.dao.IDictionaryNameDao;
import com.purplelight.mcm.entity.DictionaryItem;
import com.purplelight.mcm.entity.DictionaryName;
import com.purplelight.mcm.service.IDictionaryService;

public class DictionaryServiceImpl implements IDictionaryService {

	@Resource
	private IDictionaryNameDao dictNameDao;
	
	@Resource
	private IDictionaryItemDao dictItemDao;
	
	@Override
	public List<DictionaryName> getAllDictNames() {
		return dictNameDao.getAll();
	}

	@Override
	public List<DictionaryItem> getDictItemsByDictNameCode(String dictNameCode) {
		return dictItemDao.getByDictNameCode(dictNameCode);
	}

	@Override
	public List<DictionaryItem> getDictItemsByDictNameCodeWithBlank(String dictNameCode) {
		List<DictionaryItem> retList = dictItemDao.getByDictNameCode(dictNameCode);
		
		DictionaryItem blankItem = new DictionaryItem();
		blankItem.setDictItemValue("外部系统类型");
		retList.add(0, blankItem);
		
		return retList;
	}

}
