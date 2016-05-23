package com.purplelight.mcm.outtersystem.parameter;

import java.util.List;

public class FixQualityItemParameter extends Parameter {
	private int id;
	private String action;
	private String donedate;
	private List<String> images;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public String getDonedate() {
		return donedate;
	}
	public void setDonedate(String donedate) {
		this.donedate = donedate;
	}
}
