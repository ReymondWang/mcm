package com.purplelight.mcm.service;

import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;

public interface ISystemUserService {
	public boolean hasThisUser(SystemUser user);
	public SystemUser login(SystemUser user, String password);
	public void addUser(SystemUser user, SystemUser loginedUser);
	public void updateUser(SystemUser user, SystemUser loginedUser) throws Exception;
	public void deleteUser(SystemUser user);
	public void deleteUserById(int id);
	public void deleteUserByIdArr(int[] idArr);
	public void deleteUserByIdStr(String idStr);
	public SystemUser getUser(SystemUser user) throws Exception;
	public SystemUser getUserbyId(int id);
	public PageInfo<SystemUser> query(Strategy strategy, int pageNo) throws Exception;
	public boolean checkPassword(int id, String password);
	public void modifyPassword(int id, String password);
}
