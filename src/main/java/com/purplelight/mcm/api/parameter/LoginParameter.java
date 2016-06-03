package com.purplelight.mcm.api.parameter;

public class LoginParameter extends Parameter {
	private String password;

	private String meachineCode;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMeachineCode() {
		return meachineCode;
	}

	public void setMeachineCode(String meachineCode) {
		this.meachineCode = meachineCode;
	}
}
