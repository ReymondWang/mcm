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

@Entity
@Table(name="app_function")
public class AppFunction implements Serializable {
	private static final long serialVersionUID = 624947710487604266L;

	private int id;
	
	// 定义该功能显示在APP的那个只要模块分页上，该数据从0开始，不设定上限。
	private int fragment;
	
	// 定义该功能是数据APP模块分页的那个部分上，现在预定义三个部分McmConstant.FragmentPart
	private int part;
	
	// 定义该功能的类型，McmConstant。FunctionType
	private int functionType;
	
	// 显示的图标或图片的地址
	private String titleImgPath;
	
	// 显示的标题
	private String title;
	
	// APP的内部注册功能
	private String appFuncName;
	
	// 应用的地址，根据应用的类型不同，设定不同的地址
	// 内部文章：文章编号
	// 内部轻应用：应用编号
	// 内部原生应用：应用编号
	// 外部文章：连接地址
	// 外部轻应用：外部的应用地址
	// 外部原生应用：外部原生应用的下载地址
	private String contentUrl;
	
	// 统计数据用的Url，如果存在这个字段，则通过这个字段进行数据统计，并将统计好的数据以提醒的方式显示的图标上。
	private String statUrl;
	
	// 如果本应用为外部应用，则需要在这里注明外部系统。
	private OutterSystem outterSystem;
	
	// 如果为外部原生应用，则需要写明白调用的具体方式。
	private String callMethod;
	
	// 功能排序
	private String appOrder; 
	
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

	@Column(name="fragment")
	public int getFragment() {
		return fragment;
	}

	public void setFragment(int fragment) {
		this.fragment = fragment;
	}

	@Column(name="part")
	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}

	@Column(name="function_type")
	public int getFunctionType() {
		return functionType;
	}

	public void setFunctionType(int functionType) {
		this.functionType = functionType;
	}

	@Column(name="title_img_path", length=100)
	public String getTitleImgPath() {
		return titleImgPath;
	}

	public void setTitleImgPath(String titleImgPath) {
		this.titleImgPath = titleImgPath;
	}

	@Column(name="title", length=50)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="app_func_name", length = 100)
	public String getAppFuncName() {
		return appFuncName;
	}

	public void setAppFuncName(String appFuncName) {
		this.appFuncName = appFuncName;
	}

	@Column(name="content_url", length=500)
	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="outter_system_id", referencedColumnName="id")
	public OutterSystem getOutterSystem() {
		return outterSystem;
	}

	public void setOutterSystem(OutterSystem outterSystem) {
		this.outterSystem = outterSystem;
	}

	@Column(name="call_method", length=500)
	public String getCallMethod() {
		return callMethod;
	}

	public void setCallMethod(String callMethod) {
		this.callMethod = callMethod;
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

	@Column(name="stat_url", length=100)
	public String getStatUrl() {
		return statUrl;
	}

	public void setStatUrl(String statUrl) {
		this.statUrl = statUrl;
	}

	@Column(name="app_order", length=50)
	public String getAppOrder() {
		return appOrder;
	}

	public void setAppOrder(String appOrder) {
		this.appOrder = appOrder;
	}
}
