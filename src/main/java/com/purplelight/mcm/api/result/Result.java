package com.purplelight.mcm.api.result;

public class Result{
	public static final int ERROR = 0;
	public static final int SUCCESS = 1;
	
	private int success;
	
	private String message;

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
