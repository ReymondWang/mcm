package com.purplelight.mcm.api.result;

public class WebBanner {

	private String id;
	
	private String image;
	
	private String type;
	
	private String url;
	
	private String label;
	
	private int outterSystem;
	
	private String callMethod;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOutterSystem() {
		return outterSystem;
	}

	public void setOutterSystem(int outterSystem) {
		this.outterSystem = outterSystem;
	}

	public String getCallMethod() {
		return callMethod;
	}

	public void setCallMethod(String callMethod) {
		this.callMethod = callMethod;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
