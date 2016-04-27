package com.purplelight.mcm.api.result;

public class Result{
	public static final String ERROR = "0";
	public static final String SUCCESS = "1";
	
	private String success;
	
	private String message;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
