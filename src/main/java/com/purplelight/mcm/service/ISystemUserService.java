package com.purplelight.mcm.service;

import com.purplelight.mcm.entity.SystemUser;

public interface ISystemUserService {
	public boolean hasThisUser(SystemUser user);
	public boolean login(SystemUser user, String password);
	public void addUser(SystemUser user, SystemUser loginedUser);
	public void udpateUser(SystemUser user, SystemUser loginedUser);
	public void deleteUser(SystemUser user, SystemUser loginedUser);
}
