package com.purplelight.mcm.api.result;

import com.purplelight.mcm.api.bean.UpgradeInfo;

public class UpgradeResult extends Result {
	private UpgradeInfo upgradeInfo = new UpgradeInfo();

	public UpgradeInfo getUpgradeInfo() {
		return upgradeInfo;
	}

	public void setUpgradeInfo(UpgradeInfo upgradeInfo) {
		this.upgradeInfo = upgradeInfo;
	}
}
