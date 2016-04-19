package com.purplelight.mcm.service;

import java.sql.Timestamp;

import javax.annotation.Resource;

import com.purplelight.mcm.dao.ISystemUserDao;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.util.StringUtil;

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

	public void udpateUser(SystemUser user, SystemUser loginedUser) {
		user.setUpdateUser(loginedUser.getId());
		user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		systemUserDao.updateUser(user);
	}

	public void deleteUser(SystemUser user, SystemUser loginedUser) {
		systemUserDao.deleteUser(user);
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
	public boolean login(SystemUser user, String password) {
		if (!StringUtil.IsNullOrEmpty(user.getUserCode())){
			user = systemUserDao.getByUserCode(user.getUserCode());
		} else if (!StringUtil.IsNullOrEmpty(user.getEmail())){
			user = systemUserDao.getByEmail(user.getEmail());
		} else if (!StringUtil.IsNullOrEmpty(user.getPhone())){
			user = systemUserDao.getByPhone(user.getPhone());
		}
		if (user != null && systemUserDao.getPassword(password).equals(user.getPassword())){
			return true;
		}
		
		return false;
	}

}
