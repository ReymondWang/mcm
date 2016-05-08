package com.purplelight.mcm.api.parameter;

import com.purplelight.mcm.entity.SystemUser;

public class UserInfoParameter extends Parameter {
	private SystemUser user = new SystemUser();

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}
}
