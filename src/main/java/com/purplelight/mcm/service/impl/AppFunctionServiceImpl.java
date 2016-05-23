package com.purplelight.mcm.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;

import com.purplelight.mcm.api.bean.AppFuncInfo;
import com.purplelight.mcm.dao.IAppFunctionDao;
import com.purplelight.mcm.entity.AppFunction;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.Strategy;
import com.purplelight.mcm.service.IAppFunctionService;
import com.purplelight.mcm.util.ConvertUtil;
import com.purplelight.mcm.util.UpdateUtil;

public class AppFunctionServiceImpl implements IAppFunctionService {

	@Resource
	private IAppFunctionDao appFuncDao;
	
	@Override
	public void addAppFunction(AppFunction appFunction, SystemUser loginedUser) {
		appFunction.setInputUser(loginedUser);
		appFunction.setInputTime(new Timestamp(System.currentTimeMillis()));
		appFunction.setUpdateUser(loginedUser);
		appFunction.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		appFuncDao.save(appFunction);
	}

	@Override
	public void updateAppFunction(AppFunction appFunction, SystemUser loginedUser) throws Exception {
		List<String> alwaysUpdateFileds = new ArrayList<>();
		alwaysUpdateFileds.add("OutterSystem");
		
		AppFunction orgFunc = appFuncDao.getById(appFunction.getId());
		orgFunc = UpdateUtil.copyNotNullOrEmptyValue(orgFunc, appFunction, alwaysUpdateFileds);
		
		orgFunc.setUpdateUser(loginedUser);
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

	@Override
	public List<AppFuncInfo> getAppFuncByFragmentAndPart(int fragment, int part, int userId) {
		String hql = "select f.id"
				+ ", f.fragment"
				+ ", f.part"
				+ ", f.functionType"
				+ ", f.titleImgPath"
				+ ", f.title"
				+ ", f.contentUrl"
				+ ", f.statUrl"
				+ ", f.outterSystem.id"
				+ ", f.callMethod"
				+ " from AppFunction f left join f.outterSystem s left join s.bindUsers b with b.user.id = :userId"
				+ " where (f.functionType in (1, 2, 3) or b.id is not null)"
				+ " and f.fragment = :fragment"
				+ " and f.part = :part";
		Query query = appFuncDao.getSession().createQuery(hql);
		query.setParameter("fragment", fragment);
		query.setParameter("part", part);
		query.setParameter("userId", userId);
		
		@SuppressWarnings("rawtypes")
		List list = query.list();
		List<AppFuncInfo> retList = new ArrayList<>();
		if (list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				Object[] valArr = (Object[])query.list().get(i);
				AppFuncInfo item = new AppFuncInfo();
				item.setId(ConvertUtil.toInt(valArr[0]));
				item.setFragment(ConvertUtil.toInt(valArr[1]));
				item.setPart(ConvertUtil.toInt(valArr[2]));
				item.setFunctionType(ConvertUtil.toInt(valArr[3]));
				item.setTitleImgPath(ConvertUtil.toString(valArr[4]));
				item.setTitle(ConvertUtil.toString(valArr[5]));
				item.setContentUrl(ConvertUtil.toString(valArr[6]));
				item.setStatUrl(ConvertUtil.toString(valArr[7]));
				item.setOutterSystemId(ConvertUtil.toInt(valArr[8]));
				item.setCallMethod(ConvertUtil.toString(valArr[9]));
				
				retList.add(item);
			}
		}
		
		return retList;
	}

}
