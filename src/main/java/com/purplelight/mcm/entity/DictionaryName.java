package com.purplelight.mcm.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dictionary_name")
public class DictionaryName {
	private int id;
	
	private String dictNameCode;
	
	private String dictNameValue;

	private int inputUser;
	
	private Timestamp inputTime;
	
	private int updateUser;
	
	private Timestamp updateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique=true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="dict_name_code", length=50)
	public String getDictNameCode() {
		return dictNameCode;
	}

	public void setDictNameCode(String dictNameCode) {
		this.dictNameCode = dictNameCode;
	}

	@Column(name="dict_name_value", length=100)
	public String getDictNameValue() {
		return dictNameValue;
	}

	public void setDictNameValue(String dictNameValue) {
		this.dictNameValue = dictNameValue;
	}
	
	@Column(name="input_user")
	public int getInputUser() {
		return inputUser;
	}

	public void setInputUser(int inputUser) {
		this.inputUser = inputUser;
	}

	@Column(name="input_time")
	public Timestamp getInputTime() {
		return inputTime;
	}

	public void setInputTime(Timestamp inputTime) {
		this.inputTime = inputTime;
	}

	@Column(name="update_user")
	public int getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(int updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name="update_time")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
