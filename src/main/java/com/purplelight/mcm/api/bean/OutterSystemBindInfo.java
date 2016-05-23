package com.purplelight.mcm.api.bean;

import java.io.Serializable;

/**
 * 根据用户编号取得全部已经的外部系统信息，并判断是否已经绑定
 * @author wangyn
 *
 */
public class OutterSystemBindInfo implements Serializable {
	private static final long serialVersionUID = -4280511824420571304L;

	private int systemId;
	
	private String systemName;
	
	private String systemType;
	
	private String systemUrl;
	
	private int startUsing;
	
	private String validationUrl;
	
	private String systemDescription;
	
	private boolean isBinded;

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getSystemUrl() {
		return systemUrl;
	}

	public void setSystemUrl(String systemUrl) {
		this.systemUrl = systemUrl;
	}

	public int getStartUsing() {
		return startUsing;
	}

	public void setStartUsing(int startUsing) {
		this.startUsing = startUsing;
	}

	public String getValidationUrl() {
		return validationUrl;
	}

	public void setValidationUrl(String validationUrl) {
		this.validationUrl = validationUrl;
	}

	public String getSystemDescription() {
		return systemDescription;
	}

	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
	}

	public boolean isBinded() {
		return isBinded;
	}

	public void setBinded(boolean isBinded) {
		this.isBinded = isBinded;
	}
}
