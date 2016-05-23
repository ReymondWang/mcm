package com.purplelight.mcm.api.result;

import java.util.List;

import com.purplelight.mcm.api.bean.SpecialItem;

public class SpecialItemResult extends Result {
	private List<SpecialItem> items;

	public List<SpecialItem> getItems() {
		return items;
	}

	public void setItems(List<SpecialItem> items) {
		this.items = items;
	}
}
