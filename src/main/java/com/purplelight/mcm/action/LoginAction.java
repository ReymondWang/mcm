package com.purplelight.mcm.action;

import javax.annotation.Resource;

import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.ISystemUserService;

public class LoginAction extends BaseAction{

	private static final long serialVersionUID = 2715055935954602192L;
	
	private String loginId;
	
	private String password;
	
	@Resource
	private ISystemUserService systemUserService;
	
	@Override
	public String execute() throws Exception {
		SystemUser mUser = new SystemUser();
		
		// 尝试使用用户编号登录
		mUser.setUserCode(loginId);
		boolean loginSuccessed = systemUserService.login(mUser, password);
		
		// 如果失败，尝试使用邮箱登录
		if (!loginSuccessed){
			mUser.setUserCode("");
			mUser.setEmail(loginId);
			loginSuccessed = systemUserService.login(mUser, password);
		}
		
		// 如果失败，尝试使用手机号登录
		if (!loginSuccessed){
			mUser.setEmail("");
			mUser.setPhone(loginId);
			loginSuccessed = systemUserService.login(mUser, password);
		}
		
		// 如果仍然失败，则返回错误，否则返回成功。
		if (!loginSuccessed){
			setMessageFromResource("msg_login_failed");
			
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
