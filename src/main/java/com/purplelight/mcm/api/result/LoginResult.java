package com.purplelight.mcm.api.result;

import com.purplelight.mcm.api.bean.SystemUserInfo;

public class LoginResult extends Result {
	private SystemUserInfo user;

	public SystemUserInfo getUser() {
		return user;
	}

	public void setUser(SystemUserInfo user) {
		this.user = user;
	}
}
