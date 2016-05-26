package com.purplelight.mcm.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.dao.IAppFileManageDao;
import com.purplelight.mcm.entity.AppFileManage;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.exception.McmException;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;
import com.purplelight.mcm.service.IAppFileManageService;
import com.purplelight.mcm.util.McmConstant;
import com.purplelight.mcm.util.UpdateUtil;

public class AppFileManageServiceImpl implements IAppFileManageService {

	@Resource
	private IAppFileManageDao appFileManageDao;
	
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
	
}
