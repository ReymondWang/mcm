package com.purplelight.mcm.api.bean;

import java.io.Serializable;

public class AppFuncInfo implements Serializable{
	private static final long serialVersionUID = -2269226606779545938L;

	private int id;
	
	private int fragment;
	
	private int part;
	
	private int functionType;
	
	private String titleImgPath;
	
	private String title;
	
	private String contentUrl;
	
	private String statUrl;
	
	private int outterSystemId;
	
	private String callMethod;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFragment() {
		return fragment;
	}

	public void setFragment(int fragment) {
		this.fragment = fragment;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}

	public int getFunctionType() {
		return functionType;
	}

	public void setFunctionType(int functionType) {
		this.functionType = functionType;
	}

	public String getTitleImgPath() {
		return titleImgPath;
	}

	public void setTitleImgPath(String titleImgPath) {
		this.titleImgPath = titleImgPath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public String getStatUrl() {
		return statUrl;
	}

	public void setStatUrl(String statUrl) {
		this.statUrl = statUrl;
	}

	public int getOutterSystemId() {
		return outterSystemId;
	}

	public void setOutterSystemId(int outterSystemId) {
		this.outterSystemId = outterSystemId;
	}

	public String getCallMethod() {
		return callMethod;
	}

	public void setCallMethod(String callMethod) {
		this.callMethod = callMethod;
	}
}
