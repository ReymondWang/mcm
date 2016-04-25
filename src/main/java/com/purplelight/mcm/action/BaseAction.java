package com.purplelight.mcm.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.purplelight.mcm.api.config.ConfigUtil;

public class BaseAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 8290222100137187228L;

	// 定义错误类型
	public static final int SUCCESS_MSG = 1;
	public static final int ERROR_MSG = 2;
	
	private int messageType = ERROR_MSG;
	
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

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getRootPath(){
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getContextPath();
	}
	
	public String getImageServer(){
		return ConfigUtil.config.getProperty("image_server");
	}
}
