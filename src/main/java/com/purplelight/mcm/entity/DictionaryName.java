package com.purplelight.mcm.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
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
@Table(name="dictionary_name")
public class DictionaryName implements Serializable {
	private static final long serialVersionUID = -1787519063628897097L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique=true)
	private int id;
	
	@Column(name="dict_name_code", length=50)
	private String dictNameCode;
	
	@Column(name="dict_name_value", length=100)
	private String dictNameValue;

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
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="dictName")
	private Set<DictionaryItem> dictItems = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDictNameCode() {
		return dictNameCode;
	}

	public void setDictNameCode(String dictNameCode) {
		this.dictNameCode = dictNameCode;
	}

	public String getDictNameValue() {
		return dictNameValue;
	}

	public void setDictNameValue(String dictNameValue) {
		this.dictNameValue = dictNameValue;
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

	public Set<DictionaryItem> getDictItems() {
		return dictItems;
	}

	public void setDictItems(Set<DictionaryItem> dictItems) {
		this.dictItems = dictItems;
	}
}
