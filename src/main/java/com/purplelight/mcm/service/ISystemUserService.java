package com.purplelight.mcm.service;

import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;

public interface ISystemUserService {
	public boolean hasThisUser(SystemUser user);
	public boolean login(SystemUser user, String password);
	public void addUser(SystemUser user, SystemUser loginedUser);
	public void updateUser(SystemUser user, SystemUser loginedUser) throws Exception;
	public void deleteUser(SystemUser user);
	public void deleteUserById(int id);
	public void deleteUserByIdArr(int[] idArr);
	public void deleteUserByIdStr(String idStr);
	public SystemUser getUser(SystemUser user) throws Exception;
	public PageInfo<SystemUser> query(Strategy strategy, int pageNo) throws Exception;
}
