package com.purplelight.mcm.service;

import java.util.List;

import com.purplelight.mcm.api.result.LoginResult;
import com.purplelight.mcm.bean.OutterSystemBindInfo;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.entity.UserBindSystem;

public interface IUserBindSystemService {
	public void addBindInfo(UserBindSystem entity, SystemUser loginedUser);
	public void updateBindInfo(UserBindSystem entity, SystemUser loginedUser) throws Exception;
	public void deleteBindInfo(UserBindSystem entity);
	public void deleteBindInfo(int userId, int systemId);
	public LoginResult bindWithCreate(String userCode, String password) throws Exception;
	public List<OutterSystemBindInfo> getUserBindInfo(int userId);
}
