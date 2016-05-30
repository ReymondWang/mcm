package com.purplelight.mcm.api.parameter;

public class UpgradeParameter extends Parameter {
	private String appName;
	
	private String osType;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}
}
