package com.purplelight.mcm.outtersystem.bean;

import java.io.Serializable;

public class ResultItem implements Serializable {
	private static final long serialVersionUID = 5906324026444149551L;

	private String name;
	
	private String checkresult;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCheckresult() {
		return checkresult;
	}

	public void setCheckresult(String checkresult) {
		this.checkresult = checkresult;
	}
}
