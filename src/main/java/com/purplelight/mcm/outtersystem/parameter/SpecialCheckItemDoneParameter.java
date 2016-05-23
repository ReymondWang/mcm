package com.purplelight.mcm.outtersystem.parameter;

import java.util.List;

import com.purplelight.mcm.outtersystem.bean.ResultItem;

public class SpecialCheckItemDoneParameter extends Parameter {
	private int id;
	
	private List<ResultItem> items;
	
	private List<String> images;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ResultItem> getItems() {
		return items;
	}

	public void setItems(List<ResultItem> items) {
		this.items = items;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
}
