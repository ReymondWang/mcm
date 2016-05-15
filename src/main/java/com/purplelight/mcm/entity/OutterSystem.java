package com.purplelight.mcm.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="outter_system")
public class OutterSystem {
	private int id;
	
	private String systemCode;
	
	private String systemName;
	
	private String systemType;
	
	// 系统主要的地址
	private String systemUrl;
	
	// 系统是否启用
	private int startUsing;
	
	// 系统用户认证的地址
	private String validationUrl;
	
	private String systemDescription;

	private SystemUser inputUser;
	
	private Timestamp inputTime;
	
	private SystemUser updateUser;
	
	private Timestamp updateTime;
	
	private Set<UserBindSystem> bindUsers;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="outterSystem")
	public Set<UserBindSystem> getBindUsers() {
		return bindUsers;
	}

	public void setBindUsers(Set<UserBindSystem> bindUsers) {
		this.bindUsers = bindUsers;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique=true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="system_code", length=50)
	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	@Column(name="system_name", length=100)
	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	@Column(name="system_type", length=50)
	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	@Column(name="system_description", length=1000)
	public String getSystemDescription() {
		return systemDescription;
	}

	public void setSystemDescription(String systemDescription) {
		this.systemDescription = systemDescription;
	}

	@Column(name="input_time")
	public Timestamp getInputTime() {
		return inputTime;
	}

	public void setInputTime(Timestamp inputTime) {
		this.inputTime = inputTime;
	}

	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumn(name="input_user", referencedColumnName="id")
	public SystemUser getInputUser() {
		return inputUser;
	}

	public void setInputUser(SystemUser inputUser) {
		this.inputUser = inputUser;
	}

	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumn(name="update_user", referencedColumnName="id")
	public SystemUser getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(SystemUser updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name="update_time")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name="system_url", length=100)
	public String getSystemUrl() {
		return systemUrl;
	}

	public void setSystemUrl(String systemUrl) {
		this.systemUrl = systemUrl;
	}

	@Column(name="start_using")
	public int getStartUsing() {
		return startUsing;
	}

	public void setStartUsing(int startUsing) {
		this.startUsing = startUsing;
	}

	@Column(name="validation_url", length=100)
	public String getValidationUrl() {
		return validationUrl;
	}

	public void setValidationUrl(String validationUrl) {
		this.validationUrl = validationUrl;
	}
}
