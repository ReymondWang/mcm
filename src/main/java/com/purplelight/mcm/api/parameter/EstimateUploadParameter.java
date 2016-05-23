package com.purplelight.mcm.api.parameter;

import java.util.ArrayList;
import java.util.List;

public class EstimateUploadParameter extends Parameter {
	private int systemId;

    private int itemId;

    private String improvementAction;

    private String date;

    private List<String> imageFileNames = new ArrayList<>();

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getImprovementAction() {
		return improvementAction;
	}

	public void setImprovementAction(String improvementAction) {
		this.improvementAction = improvementAction;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getImageFileNames() {
		return imageFileNames;
	}

	public void setImageFileNames(List<String> imageFileNames) {
		this.imageFileNames = imageFileNames;
	}
}
