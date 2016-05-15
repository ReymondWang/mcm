package com.purplelight.mcm.api.result;

import com.purplelight.mcm.entity.SystemUser;

public class RegisterResult extends Result {
	private SystemUser user;

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}
}
