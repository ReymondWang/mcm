package com.purplelight.mcm.api.result;

import java.util.ArrayList;
import java.util.List;

import com.purplelight.mcm.entity.AppFunction;

public class AppFunctionResult extends Result {
	
	private List<AppFunction> topList = new ArrayList<>();
	
	private List<AppFunction> bodyList = new ArrayList<>();
	
	private List<AppFunction> footList = new ArrayList<>();

	public List<AppFunction> getTopList() {
		return topList;
	}

	public void setTopList(List<AppFunction> topList) {
		this.topList = topList;
	}

	public List<AppFunction> getBodyList() {
		return bodyList;
	}

	public void setBodyList(List<AppFunction> bodyList) {
		this.bodyList = bodyList;
	}

	public List<AppFunction> getFootList() {
		return footList;
	}

	public void setFootList(List<AppFunction> footList) {
		this.footList = footList;
	}
	
}
