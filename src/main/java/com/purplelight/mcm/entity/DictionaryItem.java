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
@Table(name="dictionary_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DictionaryItem implements Serializable {
	private static final long serialVersionUID = 4269878190944319812L;

	private int id;
	
	private String dictItemCode;
	
	private DictionaryName dictName;
	
	private String dictItemValue;
	
	private SystemUser inputUser;
	
	private Timestamp inputTime;
	
	private SystemUser updateUser;
	
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

	@Column(name="dict_item_code", length=50)
	public String getDictItemCode() {
		return dictItemCode;
	}

	public void setDictItemCode(String dictItemCode) {
		this.dictItemCode = dictItemCode;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dict_name_code", referencedColumnName="dict_name_code")
	public DictionaryName getDictName() {
		return dictName;
	}

	public void setDictName(DictionaryName dictName) {
		this.dictName = dictName;
	}

	@Column(name="dict_item_value", length=100)
	public String getDictItemValue() {
		return dictItemValue;
	}

	public void setDictItemValue(String dictItemValue) {
		this.dictItemValue = dictItemValue;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="input_user", referencedColumnName="id")
	public SystemUser getInputUser() {
		return inputUser;
	}

	public void setInputUser(SystemUser inputUser) {
		this.inputUser = inputUser;
	}

	@Column(name="input_time")
	public Timestamp getInputTime() {
		return inputTime;
	}

	public void setInputTime(Timestamp inputTime) {
		this.inputTime = inputTime;
	}

	@ManyToOne(fetch=FetchType.LAZY)
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
	
}
