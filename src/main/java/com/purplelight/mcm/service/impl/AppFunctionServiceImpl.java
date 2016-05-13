package com.purplelight.mcm.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.dao.IAppFunctionDao;
import com.purplelight.mcm.entity.AppFunction;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.Strategy;
import com.purplelight.mcm.service.IAppFunctionService;
import com.purplelight.mcm.util.UpdateUtil;

public class AppFunctionServiceImpl implements IAppFunctionService {

	@Resource
	private IAppFunctionDao appFuncDao;
	
	@Override
	public void addAppFunction(AppFunction appFunction, SystemUser loginedUser) {
		appFunction.setInputUser(loginedUser.getId());
		appFunction.setInputTime(new Timestamp(System.currentTimeMillis()));
		appFunction.setUpdateUser(loginedUser.getId());
		appFunction.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		appFuncDao.save(appFunction);
	}

	@Override
	public void updateAppFunction(AppFunction appFunction, SystemUser loginedUser) throws Exception {
		AppFunction orgFunc = appFuncDao.getById(appFunction.getId());
		orgFunc = UpdateUtil.copyNotNullOrEmptyValue(orgFunc, appFunction);
		
		orgFunc.setUpdateUser(loginedUser.getId());
		orgFunc.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		appFuncDao.update(orgFunc);
	}

	@Override
	public void deleteAppFunction(AppFunction appFunction) {
		appFuncDao.delete(appFunction);
	}

	@Override
	public AppFunction getAppFunc(AppFunction appFunc) throws Exception {
		List<AppFunction> list = appFuncDao.find(new Strategy(appFunc, "af"));
		if (list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<AppFunction> getAppFuncByFragment(int fragment) {
		return appFuncDao.getByFragment(fragment);
	}

	@Override
	public List<AppFunction> getAppFuncByFragmentAndPart(int fragment, int part) {
		return appFuncDao.getByFragmentAndPart(fragment, part);
	}

	@Override
	public List<AppFunction> getAppFuncByFragmentAndPartAndType(int fragment, int part, int type) {
		return appFuncDao.getByFragmentAndPartAndType(fragment, part, type);
	}

}
