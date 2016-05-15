package com.purplelight.mcm.api.result;

public class QuickRegisterResult extends Result {
	private boolean quickRegister = false;

	public boolean isQuickRegister() {
		return quickRegister;
	}

	public void setQuickRegister(boolean quickRegister) {
		this.quickRegister = quickRegister;
	}
}
