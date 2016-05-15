package com.purplelight.mcm.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_bind_system")
public class UserBindSystem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY, optional=true)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private SystemUser user;
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumn(name="system_id", referencedColumnName="id")
	private OutterSystem outterSystem;
	
	@Column(name="token", length=100)
	private String token;
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumn(name="input_user", referencedColumnName="id")
	private SystemUser inputUser;
	
	@Column(name="input_time")
	private Timestamp inputTime;
	
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
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

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public OutterSystem getOutterSystem() {
		return outterSystem;
	}

	public void setOutterSystem(OutterSystem outterSystem) {
		this.outterSystem = outterSystem;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
