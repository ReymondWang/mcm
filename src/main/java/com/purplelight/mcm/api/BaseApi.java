package com.purplelight.mcm.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.util.StringUtil;

public class BaseApi extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 5480204491884274866L;
	
	private HttpServletRequest mRequest;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		mRequest = request;
	}
	
	public HttpServletRequest getRequest(){
		return mRequest;
	}
	
	public String getJson(){
		String json = "";
		try{
			json = getFromReader();
			json = URLDecoder.decode(json, "utf-8");
		} catch (Exception e1){
		}
		return json;
	}
	
	private String getFromReader() throws IOException{
		StringBuffer jsonBuffer = new StringBuffer();
		String line = "";
		BufferedReader reader = mRequest.getReader();
		while(!StringUtil.IsNullOrEmpty(line = reader.readLine())){
			jsonBuffer.append(line);
		}
		return jsonBuffer.toString();
	}

	/**
	 * 返回使用API更新数据统一的更新用户
	 * @return
	 */
	public SystemUser getUpdateUser(){
		SystemUser user = new SystemUser();
		user.setId(1);
		return user;
	}
	
}
