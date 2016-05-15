package com.purplelight.mcm.api.result;

import java.util.List;

import com.purplelight.mcm.bean.OutterSystemBindInfo;

public class OutterSystemResult extends Result {
	private List<OutterSystemBindInfo> systemList;

	public List<OutterSystemBindInfo> getSystemList() {
		return systemList;
	}

	public void setSystemList(List<OutterSystemBindInfo> systemList) {
		this.systemList = systemList;
	}
}
