package com.purplelight.mcm.outtersystem.bean;

import java.io.Serializable;
import java.util.List;

public class QualityCheckItem implements Serializable {
	private static final long serialVersionUID = 6062863141487549834L;
	
	private String Id;
	
	private String CheckId;
	
	private String ProjectId;
	
	private String Catalog;
	
	private String Character;
	
	private String Descript;
	
	private String Partition;
	
	private String ImprovementAction;
	
	private String InChargePerson;
	
	private String CheckPerson;
	
	private String PlanDate;
	
	private String BeginDate;
	
	private String EndDate;
	
	private String InChargePersonName;
	
	private String CheckPersonName;
	
	private String ProjectName;
	
	private String PeriodName;
	
	private List<String> Thumbnail;
	
	private List<String> Images;
	
	private List<String> FixedThumbnail;
	
	private List<String> FixedImages;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getCheckId() {
		return CheckId;
	}

	public void setCheckId(String checkId) {
		CheckId = checkId;
	}

	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	public String getCatalog() {
		return Catalog;
	}

	public void setCatalog(String catalog) {
		Catalog = catalog;
	}

	public String getCharacter() {
		return Character;
	}

	public void setCharacter(String character) {
		Character = character;
	}

	public String getDescript() {
		return Descript;
	}

	public void setDescript(String descript) {
		Descript = descript;
	}

	public String getPartition() {
		return Partition;
	}

	public void setPartition(String partition) {
		Partition = partition;
	}

	public String getImprovementAction() {
		return ImprovementAction;
	}

	public void setImprovementAction(String improvementAction) {
		ImprovementAction = improvementAction;
	}

	public String getInChargePerson() {
		return InChargePerson;
	}

	public void setInChargePerson(String inChargePerson) {
		InChargePerson = inChargePerson;
	}

	public String getCheckPerson() {
		return CheckPerson;
	}

	public void setCheckPerson(String checkPerson) {
		CheckPerson = checkPerson;
	}

	public String getPlanDate() {
		return PlanDate;
	}

	public void setPlanDate(String planDate) {
		PlanDate = planDate;
	}

	public String getBeginDate() {
		return BeginDate;
	}

	public void setBeginDate(String beginDate) {
		BeginDate = beginDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getInChargePersonName() {
		return InChargePersonName;
	}

	public void setInChargePersonName(String inChargePersonName) {
		InChargePersonName = inChargePersonName;
	}

	public String getCheckPersonName() {
		return CheckPersonName;
	}

	public void setCheckPersonName(String checkPersonName) {
		CheckPersonName = checkPersonName;
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

	public List<String> getFixedThumbnail() {
		return FixedThumbnail;
	}

	public void setFixedThumbnail(List<String> fixedThumbnail) {
		FixedThumbnail = fixedThumbnail;
	}

	public List<String> getFixedImages() {
		return FixedImages;
	}

	public void setFixedImages(List<String> fixedImages) {
		FixedImages = fixedImages;
	}
}
