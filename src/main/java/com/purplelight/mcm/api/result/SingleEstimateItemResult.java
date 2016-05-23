package com.purplelight.mcm.api.result;

import com.purplelight.mcm.api.bean.EstimateItem;

public class SingleEstimateItemResult extends Result {
	private EstimateItem item;

	public EstimateItem getItem() {
		return item;
	}

	public void setItem(EstimateItem item) {
		this.item = item;
	}
}
