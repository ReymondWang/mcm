package com.purplelight.mcm.outtersystem.bean;

import java.io.Serializable;

public class Project implements Serializable {
	private static final long serialVersionUID = -5899662292405606951L;
	
	private String Id;
	
	private String Name;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
