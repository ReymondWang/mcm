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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="system_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SystemUser implements Serializable {
	private static final long serialVersionUID = -6106324928219264721L;

	private int id;
	
	private String userCode;
	
	private String userName;
	
	private String password;
	
	private String headImgPath;
	
	private int sex;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private String token;
	
	private int inputUser;
	
	private Timestamp inputTime;
	
	private int updateUser;
	
	private Timestamp updateTime;
	
	private Set<UserBindSystem> outterSystems = new HashSet<>();

	private Set<UserFunction> functions = new HashSet<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique=true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="user_code", length=50)
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name="user_name", length=100)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="password", length=50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="head_img_path", length=100)
	public String getHeadImgPath() {
		return headImgPath;
	}

	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
	}

	@Column(name="sex", length=1)
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Column(name="phone", length=50)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="email", length=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="address", length=200)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Column(name="token", length=100)
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="user")
	public Set<UserBindSystem> getOutterSystems() {
		return outterSystems;
	}

	public void setOutterSystems(Set<UserBindSystem> outterSystems) {
		this.outterSystems = outterSystems;
	}

	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch=FetchType.LAZY, mappedBy="user")
	public Set<UserFunction> getFunctions() {
		return functions;
	}

	public void setFunctions(Set<UserFunction> functions) {
		this.functions = functions;
	}
}
