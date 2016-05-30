package com.purplelight.mcm.api.bean;

import java.io.Serializable;
import java.util.List;

public class EstimateItem implements Serializable {
	private static final long serialVersionUID = 1489247962473978573L;
	
	// 主键
	private String id;
	
	// 所属报告编号
	private String reportId;
	
	// 项目编号
	private String projectId;
	
	// 项目名称
	private String projectName;
	
	// 区域名称
	private String areaName;
	
	// 问题分类
	private String category;
	
	// 问题性质
	private String character;
	
	// 是否重大
	private String level;
	
	// 所属分部工程
	private String partition;
	
	// 问题描述
	private String description;
	
	// 缩略图
	private List<String> thumbs;
	
	// 原始图片
	private List<String> images;
	
	// 整改责任人编号
	private String inChargePersonId;
	
	// 整改责任人姓名
	private String inChargePersonName;
	
	// 验收责任人编号
	private String checkPersonId;
	
	// 验收责任人姓名
	private String checkPersonName;
	
	// 计划完成日期
	private String planDate;
	
	// 开始日期
	private String beginDate;
	
	// 实际完成日期
	private String endDate;
	
	// 整改措施
	private String improvmentAction;
	
	// 整改缩略图
	private List<String> fixedThumbs;
	
	// 整改原始图片
	private List<String> fixedImages;

	// 业务状态
	private int status;
	
	private int outterSystemId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPartition() {
		return partition;
	}

	public void setPartition(String partition) {
		this.partition = partition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getThumbs() {
		return thumbs;
	}

	public void setThumbs(List<String> thumbs) {
		this.thumbs = thumbs;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getInChargePersonId() {
		return inChargePersonId;
	}

	public void setInChargePersonId(String inChargePersonId) {
		this.inChargePersonId = inChargePersonId;
	}

	public String getInChargePersonName() {
		return inChargePersonName;
	}

	public void setInChargePersonName(String inChargePersonName) {
		this.inChargePersonName = inChargePersonName;
	}

	public String getCheckPersonId() {
		return checkPersonId;
	}

	public void setCheckPersonId(String checkPersonId) {
		this.checkPersonId = checkPersonId;
	}

	public String getCheckPersonName() {
		return checkPersonName;
	}

	public void setCheckPersonName(String checkPersonName) {
		this.checkPersonName = checkPersonName;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getImprovmentAction() {
		return improvmentAction;
	}

	public void setImprovmentAction(String improvmentAction) {
		this.improvmentAction = improvmentAction;
	}

	public List<String> getFixedThumbs() {
		return fixedThumbs;
	}

	public void setFixedThumbs(List<String> fixedThumbs) {
		this.fixedThumbs = fixedThumbs;
	}

	public List<String> getFixedImages() {
		return fixedImages;
	}

	public void setFixedImages(List<String> fixedImages) {
		this.fixedImages = fixedImages;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOutterSystemId() {
		return outterSystemId;
	}

	public void setOutterSystemId(int outterSystemId) {
		this.outterSystemId = outterSystemId;
	}
}
