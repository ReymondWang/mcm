package com.purplelight.mcm.service;

import com.purplelight.mcm.entity.AppFileManage;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.PageInfo;

public interface IAppFileManageService {
	public void save(AppFileManage entity, SystemUser loginedUser);
	public void update(AppFileManage entity, SystemUser loginedUser) throws Exception;
	public void saveOrUpdate(AppFileManage entity, SystemUser loginedUser) throws Exception;
	public void delete(AppFileManage entity);
	public PageInfo<AppFileManage> getByOsType(String osType, int pageNo);
	public AppFileManage getUpgrateInfo(String appName, String osType);
	public AppFileManage getById(int id);
}
