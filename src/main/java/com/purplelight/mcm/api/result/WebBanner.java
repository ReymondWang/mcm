package com.purplelight.mcm.api.result;

public class WebBanner {

	private String id;
	
	private String image;
	
	private String url;
	
	private String label;
	
	private String outterSystem;
	
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

	public String getOutterSystem() {
		return outterSystem;
	}

	public void setOutterSystem(String outterSystem) {
		this.outterSystem = outterSystem;
	}

	public String getCallMethod() {
		return callMethod;
	}

	public void setCallMethod(String callMethod) {
		this.callMethod = callMethod;
	}
	
}