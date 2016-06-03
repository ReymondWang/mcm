package com.purplelight.mcm.api.parameter;

public class BindUserParameter extends Parameter {
	private String userCode;
	
	private String password;
	
	private int systemId;
	
	private String meachineCode;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMeachineCode() {
		return meachineCode;
	}

	public void setMeachineCode(String meachineCode) {
		this.meachineCode = meachineCode;
	}
}
