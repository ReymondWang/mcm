package com.purplelight.mcm.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 8290222100137187228L;

	private String message;
	
	private Map<String, Object> mSession;
	
	public void setSession(Map<String, Object> session) {
		mSession = session;
	}
	
	public Map<String, Object> getSession(){
		return mSession;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setMessageFromResource(String msgId){
		this.message = getText(msgId);
	}

}
