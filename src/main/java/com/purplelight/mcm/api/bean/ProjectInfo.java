package com.purplelight.mcm.api.bean;

import java.io.Serializable;

public class ProjectInfo implements Serializable {
	private static final long serialVersionUID = -3686047239116706108L;
	
	private String projectId;
	
	private String projectName;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
