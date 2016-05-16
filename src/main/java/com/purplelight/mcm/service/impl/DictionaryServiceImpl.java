package com.purplelight.mcm.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.dao.IDictionaryItemDao;
import com.purplelight.mcm.dao.IDictionaryNameDao;
import com.purplelight.mcm.entity.DictionaryItem;
import com.purplelight.mcm.entity.DictionaryName;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.IDictionaryService;
import com.purplelight.mcm.util.UpdateUtil;

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

	@Override
	public DictionaryItem getDictItemByCode(String dictItemCode) {
		return dictItemDao.getByCode(dictItemCode);
	}

	@Override
	public void addDictItem(DictionaryItem item, SystemUser loginedUser) {
		item.setInputUser(loginedUser);
		item.setInputTime(new Timestamp(System.currentTimeMillis()));
		item.setUpdateUser(loginedUser);
		item.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		dictItemDao.save(item);
	}

	@Override
	public void updateDictItem(DictionaryItem item, SystemUser loginedUser) throws Exception {
		DictionaryItem orgItem = dictItemDao.getById(item.getId());
		orgItem = UpdateUtil.copyNotNullOrEmptyValue(orgItem, item);
		orgItem.setUpdateUser(loginedUser);
		orgItem.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		dictItemDao.update(orgItem);
	}

	@Override
	public void deleteDictItem(DictionaryItem item) {
		dictItemDao.delete(item);
	}

	@Override
	public boolean hasDictItemCode(String dictItemCode) {
		DictionaryItem item = dictItemDao.getByCode(dictItemCode);
		return item != null;
	}

}
