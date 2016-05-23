package com.purplelight.mcm.api.result;

import java.util.ArrayList;
import java.util.List;

import com.purplelight.mcm.api.bean.EstimateItem;

public class EstimateItemResult extends Result {
	private List<EstimateItem> items = new ArrayList<>();

	public List<EstimateItem> getItems() {
		return items;
	}

	public void setItems(List<EstimateItem> items) {
		this.items = items;
	}
}
