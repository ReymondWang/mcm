package com.purplelight.mcm.dao;

import com.purplelight.mcm.entity.SystemUser;

public interface ISystemUserDao extends IBaseDao<SystemUser, Integer> {
	public SystemUser getByUserCode(String userCode);
	public SystemUser getByEmail(String email);
	public SystemUser getByPhone(String phone);
	public String getPassword(String orgStr);
}
