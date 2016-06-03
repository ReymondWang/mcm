package com.purplelight.mcm.api.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpecialItem implements Serializable {
	private static final long serialVersionUID = 147352539714782886L;

	private int id;
	
	private int checkType;
	
	private int systemId;
	
	private String category;
	
	private String projectName;
	
	private String areaName;
	
	private String createDate;
	
	private List<String> places = new ArrayList<>();
	
	private String buildingId;
	
	private String code;
	
	private List<String> names = new ArrayList<>();
	
	private String personName;
	
	private String remark;
	
	private String checkDate;
	
	private int passPercent;

	private List<SpecialItemCheckResult> resultItems = new ArrayList<>();
	
	private String building;
	
	private List<String> thumbnail = new ArrayList<>();
	
	private List<String> images = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCheckType() {
		return checkType;
	}

	public void setCheckType(int checkType) {
		this.checkType = checkType;
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<String> getPlaces() {
		return places;
	}

	public void setPlaces(List<String> places) {
		this.places = places;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public int getPassPercent() {
		return passPercent;
	}

	public void setPassPercent(int passPercent) {
		this.passPercent = passPercent;
	}

	public List<SpecialItemCheckResult> getResultItems() {
		return resultItems;
	}

	public void setResultItems(List<SpecialItemCheckResult> resultItems) {
		this.resultItems = resultItems;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public List<String> getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(List<String> thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
}
