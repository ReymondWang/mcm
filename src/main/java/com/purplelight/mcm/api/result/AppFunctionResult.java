package com.purplelight.mcm.api.result;

import java.util.ArrayList;
import java.util.List;

public class AppFunctionResult extends Result {
	
	private List<WebBanner> topList = new ArrayList<>();
	
	private List<WebBanner> bodyList = new ArrayList<>();
	
	private List<WebBanner> footList = new ArrayList<>();

	public List<WebBanner> getTopList() {
		return topList;
	}

	public void setTopList(List<WebBanner> topList) {
		this.topList = topList;
	}

	public List<WebBanner> getBodyList() {
		return bodyList;
	}

	public void setBodyList(List<WebBanner> bodyList) {
		this.bodyList = bodyList;
	}

	public List<WebBanner> getFootList() {
		return footList;
	}

	public void setFootList(List<WebBanner> footList) {
		this.footList = footList;
	}
	
}
