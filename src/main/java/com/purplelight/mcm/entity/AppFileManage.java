package com.purplelight.mcm.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="app_file_manage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppFileManage implements Serializable {
	private static final long serialVersionUID = 4791666822763065640L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique=true)
	private int id;
	
	@Column(name="app_type", length=50)
	private String appType;
	
	@Column(name="app_name", length=50)
	private String appName;
	
	@Column(name="version_code")
	private int versionCode;
	
	@Column(name="version_name", length=50)
	private String versionName;
	
	@Column(name="version_description", length=500)
	private String versionDescription;
	
	@Column(name="file_name", length=100)
	private String fileName;
	
	@Column(name="is_using")
	private int isUsing;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="input_user", referencedColumnName="id")
	private SystemUser inputUser;
	
	@Column(name="input_time")
	private Timestamp inputTime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="update_user", referencedColumnName="id")
	private SystemUser updateUser;
	
	@Column(name="update_time")
	private Timestamp updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionDescription() {
		return versionDescription;
	}

	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(int isUsing) {
		this.isUsing = isUsing;
	}

	public SystemUser getInputUser() {
		return inputUser;
	}

	public void setInputUser(SystemUser inputUser) {
		this.inputUser = inputUser;
	}

	public Timestamp getInputTime() {
		return inputTime;
	}

	public void setInputTime(Timestamp inputTime) {
		this.inputTime = inputTime;
	}

	public SystemUser getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(SystemUser updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
