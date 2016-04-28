package com.purplelight.mcm.api.parameter;

import com.purplelight.mcm.entity.SystemUser;

public class UpdateUserInfoParameter extends Parameter {
	private SystemUser user = new SystemUser();

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}
}
