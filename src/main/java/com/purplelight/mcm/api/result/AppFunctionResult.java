package com.purplelight.mcm.api.result;

import java.util.ArrayList;
import java.util.List;

import com.purplelight.mcm.api.bean.AppFuncInfo;

public class AppFunctionResult extends Result {
	
	private List<AppFuncInfo> topList = new ArrayList<>();
	
	private List<AppFuncInfo> bodyList = new ArrayList<>();
	
	private List<AppFuncInfo> footList = new ArrayList<>();

	public List<AppFuncInfo> getTopList() {
		return topList;
	}

	public void setTopList(List<AppFuncInfo> topList) {
		this.topList = topList;
	}

	public List<AppFuncInfo> getBodyList() {
		return bodyList;
	}

	public void setBodyList(List<AppFuncInfo> bodyList) {
		this.bodyList = bodyList;
	}

	public List<AppFuncInfo> getFootList() {
		return footList;
	}

	public void setFootList(List<AppFuncInfo> footList) {
		this.footList = footList;
	}
	
}
