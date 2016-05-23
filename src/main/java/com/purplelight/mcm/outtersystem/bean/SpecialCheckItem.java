package com.purplelight.mcm.outtersystem.bean;

import java.io.Serializable;
import java.util.List;

public class SpecialCheckItem implements Serializable {
	private static final long serialVersionUID = -4295995933172234541L;
	
	private int Id;
	
	private String Catalog;
	
	private String ProjectName;
	
	private String PeriodName;
	
	private String PartitionName;
	
	private String CreateDate;
	
	private String Place1;
	
	private String Place2;
	
	private String Place3;
	
	private String BuildingId;
	
	private String Code;
	
	private String Name;
	
	private String PersonName;
	
	private String Remark;
	
	private String CheckDate;
	
	private int PassPercent;

	private List<ResultItem> ResultItems;
	
	private String Building;
	
	private List<String> Thumbnail;
	
	private List<String> Images;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCatalog() {
		return Catalog;
	}

	public void setCatalog(String catalog) {
		Catalog = catalog;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public String getPeriodName() {
		return PeriodName;
	}

	public void setPeriodName(String periodName) {
		PeriodName = periodName;
	}

	public String getPartitionName() {
		return PartitionName;
	}

	public void setPartitionName(String partitionName) {
		PartitionName = partitionName;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}

	public String getPlace1() {
		return Place1;
	}

	public void setPlace1(String place1) {
		Place1 = place1;
	}

	public String getPlace2() {
		return Place2;
	}

	public void setPlace2(String place2) {
		Place2 = place2;
	}

	public String getPlace3() {
		return Place3;
	}

	public void setPlace3(String place3) {
		Place3 = place3;
	}

	public String getBuildingId() {
		return BuildingId;
	}

	public void setBuildingId(String buildingId) {
		BuildingId = buildingId;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPersonName() {
		return PersonName;
	}

	public void setPersonName(String personName) {
		PersonName = personName;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getCheckDate() {
		return CheckDate;
	}

	public void setCheckDate(String checkDate) {
		CheckDate = checkDate;
	}

	public int getPassPercent() {
		return PassPercent;
	}

	public void setPassPercent(int passPercent) {
		PassPercent = passPercent;
	}

	public List<ResultItem> getResultItems() {
		return ResultItems;
	}

	public void setResultItems(List<ResultItem> resultItems) {
		ResultItems = resultItems;
	}

	public String getBuilding() {
		return Building;
	}

	public void setBuilding(String building) {
		Building = building;
	}

	public List<String> getThumbnail() {
		return Thumbnail;
	}

	public void setThumbnail(List<String> thumbnail) {
		Thumbnail = thumbnail;
	}

	public List<String> getImages() {
		return Images;
	}

	public void setImages(List<String> images) {
		Images = images;
	}
}
