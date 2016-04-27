package com.purplelight.mcm.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.dao.ISystemUserDao;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;
import com.purplelight.mcm.service.ISystemUserService;
import com.purplelight.mcm.util.McmConstant;
import com.purplelight.mcm.util.StringUtil;
import com.purplelight.mcm.util.UpdateUtil;

public class SystemUserServiceImpl implements ISystemUserService {

	private final static String ORIGINAL_PASSWORD = "123456";
	
	@Resource
	private ISystemUserDao systemUserDao;
	
	/**
	 * 新增用户信息
	 */
	public void addUser(SystemUser user, SystemUser loginedUser) {
		user.setPassword(systemUserDao.getPassword(ORIGINAL_PASSWORD));
		user.setInputUser(loginedUser.getId());
		user.setInputTime(new Timestamp(System.currentTimeMillis()));
		user.setUpdateUser(loginedUser.getId());
		user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		systemUserDao.addUser(user);
	}

	public void updateUser(SystemUser user, SystemUser loginedUser) throws Exception {
		SystemUser orgUser = systemUserDao.getById(user.getId());
		orgUser = UpdateUtil.copyNotNullOrEmptyValue(orgUser, user);
		
		// 设定更新用户和更新时间戳
		orgUser.setUpdateUser(loginedUser.getId());
		orgUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		systemUserDao.updateUser(orgUser);
	}

	@Override
	public void deleteUser(SystemUser user) {
		systemUserDao.deleteUser(user);
	}

	@Override
	public void deleteUserById(int id) {
		SystemUser user = systemUserDao.getById(id);
		deleteUser(user);
	}

	@Override
	public void deleteUserByIdArr(int[] idArr) {
		for(int id : idArr){
			deleteUserById(id);
		}
	}

	@Override
	public void deleteUserByIdStr(String idStr) {
		String[] idStrArr = idStr.split("\\,");
		for(String id : idStrArr){
			if (!StringUtil.IsNullOrEmpty(id)){
				deleteUserById(Integer.valueOf(id).intValue());
			}
		}
	}

	@Override
	public boolean hasThisUser(SystemUser user) {
		if (user.getId() != 0){
			return systemUserDao.getById(user.getId()) != null;
		} else if (StringUtil.IsNullOrEmpty(user.getUserCode())){
			return systemUserDao.getByUserCode(user.getUserCode()) != null;
		} else if (StringUtil.IsNullOrEmpty(user.getEmail())){
			return systemUserDao.getByEmail(user.getEmail()) != null;
		} else if (StringUtil.IsNullOrEmpty(user.getPhone())){
			return systemUserDao.getByPhone(user.getPhone()) != null;
		}
		
		return false;
	}

	@Override
	public SystemUser login(SystemUser user, String password) {
		if (!StringUtil.IsNullOrEmpty(user.getUserCode())){
			user = systemUserDao.getByUserCode(user.getUserCode());
		} else if (!StringUtil.IsNullOrEmpty(user.getEmail())){
			user = systemUserDao.getByEmail(user.getEmail());
		} else if (!StringUtil.IsNullOrEmpty(user.getPhone())){
			user = systemUserDao.getByPhone(user.getPhone());
		}
		if (user != null && systemUserDao.getPassword(password).equals(user.getPassword())){
			return user;
		}
		
		return null;
	}

	@Override
	public PageInfo<SystemUser> query(Strategy strategy, int pageNo) throws Exception {
		int pageSize = McmConstant.PAGE_SIZE;
		int startPos = (pageNo - 1) * pageSize;
		
		return systemUserDao.findByPageInfo(strategy, startPos, pageNo, pageSize);
	}

	@Override
	public SystemUser getUser(SystemUser user) throws Exception {
		SystemUser retUser = new SystemUser();
		
		if (!StringUtil.IsNullOrEmpty(user.getUserCode())){
			retUser = systemUserDao.getByUserCode(user.getUserCode());
		} else if (!StringUtil.IsNullOrEmpty(user.getEmail())){
			retUser = systemUserDao.getByEmail(user.getEmail());
		} else if (!StringUtil.IsNullOrEmpty(user.getPhone())){
			retUser = systemUserDao.getByPhone(user.getPhone());
		} else {
			List<SystemUser> list = systemUserDao.find(new Strategy(user, "u"));
			if (list.size() > 0){
				retUser = list.get(0);
			}
		}
		
		return retUser;
	}

}
