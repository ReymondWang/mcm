package com.purplelight.mcm.outtersystem.result;

import java.util.ArrayList;
import java.util.List;

import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.outtersystem.bean.Project;

public class RsProjectResult extends Result {
	private List<Project> obj = new ArrayList<>();

	public List<Project> getObj() {
		return obj;
	}

	public void setObj(List<Project> obj) {
		this.obj = obj;
	}
}
