package com.purplelight.mcm.outtersystem.bean;

public class Licence {
	private int Id;
	
	private int CatalogId;
	
	private String Catalog;
	
	private String ProjectName;
	
	private String Name;
	
	private String ResourceNames;
	
	private String LicenceDate;
	
	private String LicenceEndDate;
	
	private String Remark;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getCatalogId() {
		return CatalogId;
	}

	public void setCatalogId(int catalogId) {
		CatalogId = catalogId;
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

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getResourceNames() {
		return ResourceNames;
	}

	public void setResourceNames(String resourceNames) {
		ResourceNames = resourceNames;
	}

	public String getLicenceDate() {
		return LicenceDate;
	}

	public void setLicenceDate(String licenceDate) {
		LicenceDate = licenceDate;
	}

	public String getLicenceEndDate() {
		return LicenceEndDate;
	}

	public void setLicenceEndDate(String licenceEndDate) {
		LicenceEndDate = licenceEndDate;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}
}
