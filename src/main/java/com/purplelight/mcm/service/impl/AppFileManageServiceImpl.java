package com.purplelight.mcm.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.dao.IAppFileManageDao;
import com.purplelight.mcm.dao.IDictionaryItemDao;
import com.purplelight.mcm.entity.AppFileManage;
import com.purplelight.mcm.entity.DictionaryItem;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.exception.McmException;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;
import com.purplelight.mcm.service.IAppFileManageService;
import com.purplelight.mcm.util.McmConstant;
import com.purplelight.mcm.util.StringUtil;
import com.purplelight.mcm.util.UpdateUtil;

public class AppFileManageServiceImpl extends BaseServiceImpl implements IAppFileManageService {

	@Resource
	private IAppFileManageDao appFileManageDao;
	
	@Resource
	private IDictionaryItemDao dictItemDao;
	
	@Override
	public void save(AppFileManage entity, SystemUser loginedUser) {
		entity.setInputUser(loginedUser);
		entity.setInputTime(new Timestamp(System.currentTimeMillis()));
		entity.setUpdateUser(loginedUser);
		entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		appFileManageDao.save(entity);
	}

	@Override
	public void update(AppFileManage entity, SystemUser loginedUser) throws Exception {
		List<String> alwaysUpdateFields = new ArrayList<String>();
		alwaysUpdateFields.add("IsUsing");
		AppFileManage orgEntity = appFileManageDao.getById(entity.getId());
		UpdateUtil.copyNotNullOrEmptyValue(orgEntity, entity, alwaysUpdateFields);
		
		orgEntity.setUpdateUser(loginedUser);
		orgEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		appFileManageDao.update(entity);
	}

	@Override
	public void saveOrUpdate(AppFileManage entity, SystemUser loginedUser) throws Exception {
		if (entity.getId() == 0){
			entity.setInputUser(loginedUser);
			entity.setInputTime(new Timestamp(System.currentTimeMillis()));
			entity.setUpdateUser(loginedUser);
			entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			
			appFileManageDao.save(entity);
		} else {
			List<String> alwaysUpdateFields = new ArrayList<String>();
			alwaysUpdateFields.add("IsUsing");
			AppFileManage orgEntity = appFileManageDao.getById(entity.getId());
			UpdateUtil.copyNotNullOrEmptyValue(orgEntity, entity, alwaysUpdateFields);
			entity.setUpdateUser(loginedUser);
			entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			
			appFileManageDao.update(orgEntity);
		}
	}

	@Override
	public void delete(AppFileManage entity) {
		appFileManageDao.delete(entity);
	}

	@Override
	public PageInfo<AppFileManage> getByOsType(String osType, int pageNo) {
		AppFileManage fileManage = new AppFileManage();
		fileManage.setAppType(osType);
		Strategy strategy = new Strategy(fileManage, "fm");
		PageInfo<AppFileManage> pageInfo = new PageInfo<AppFileManage>();
		try {
			int pageSize = McmConstant.PAGE_SIZE;
			int startPos = (pageNo - 1) * pageSize;
			pageInfo = appFileManageDao.find(strategy, startPos, pageNo, pageSize);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (McmException e) {
			e.printStackTrace();
		}
		
		return pageInfo;
	}

	@Override
	public AppFileManage getById(int id) {
		return appFileManageDao.getById(id);
	}

	@Override
	public AppFileManage getUpgrateInfo(String appName, String osType) {
		AppFileManage result = new AppFileManage();
		
		DictionaryItem queryEntity = new DictionaryItem();
		queryEntity.setDictItemValue(osType);
		Strategy strategy = new Strategy(queryEntity, "di");
		try {
			String dictCode = "";
			List<DictionaryItem> itemList = dictItemDao.find(strategy);
			if (itemList != null && itemList.size() > 0) {
				dictCode = itemList.get(0).getDictItemCode();
			}
			if (!StringUtil.IsNullOrEmpty(dictCode)) {
				AppFileManage queryAFM = new AppFileManage();
				queryAFM.setAppName(appName);
				queryAFM.setAppType(dictCode);
				queryAFM.setIsUsing(1);

				Strategy strategyAFM = new Strategy(queryAFM, "afm");
				List<AppFileManage> list = appFileManageDao.find(strategyAFM);
				if (list != null && list.size() > 0){
					result = list.get(0);
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | McmException e) {
			e.printStackTrace();
		}

		return result;
	}
	
}
