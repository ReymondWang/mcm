package com.purplelight.mcm.outtersystem.parameter;

public class SpecialCheckItemParameter extends Parameter {
	private int checkId;
	
	private boolean onlyUnchecked;
	
	private int page;
	
	private int numOfPage;

	public int getCheckId() {
		return checkId;
	}

	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}

	public boolean isOnlyUnchecked() {
		return onlyUnchecked;
	}

	public void setOnlyUnchecked(boolean onlyUnchecked) {
		this.onlyUnchecked = onlyUnchecked;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNumOfPage() {
		return numOfPage;
	}

	public void setNumOfPage(int numOfPage) {
		this.numOfPage = numOfPage;
	}
}
