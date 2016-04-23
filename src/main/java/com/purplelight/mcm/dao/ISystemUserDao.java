package com.purplelight.mcm.dao;

import java.util.List;

import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;

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
	public List<SystemUser> find(Strategy queryStrategy) throws Exception;
	public PageInfo<SystemUser> findByPageInfo(Strategy queryStrategy, int startPos, int pageNo, int pageSize) throws Exception;
}
