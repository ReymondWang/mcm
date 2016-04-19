package com.purplelight.mcm.dao;

import java.util.List;

import com.purplelight.mcm.entity.SystemUser;

public interface ISystemUserDao {
	public List<SystemUser> getAll();
	public SystemUser getById(int id);
	public SystemUser getByUserCode(String userCode);
	public SystemUser getByEmail(String email);
	public SystemUser getByPhone(String phone);
	public String getPassword(String orgStr);
	public void addUser(SystemUser user);
	public void updateUser(SystemUser user);
	public void deleteUser(SystemUser user);
}
