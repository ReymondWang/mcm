package com.purplelight.mcm.api.bean;

import java.io.Serializable;

public class SpecialItemCheckResult implements Serializable {
	private static final long serialVersionUID = 7567383189971591156L;
	
	private String name;
	
	private int result;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
