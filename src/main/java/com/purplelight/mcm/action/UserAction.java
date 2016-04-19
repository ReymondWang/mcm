package com.purplelight.mcm.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.ISystemUserService;

public class UserAction extends BaseAction{
	
	private static final long serialVersionUID = 7050534214674134722L;

	private SystemUser user = new SystemUser();
	
	@Resource
	private ISystemUserService systemUserService;
	
	@Override
	public String execute() throws Exception{
//		Thread.sleep(1000);
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getParameter("action");
	}
	
	public String addUser() throws Exception{
		SystemUser loginedUser = (SystemUser)getSession().get("user");
		if (loginedUser != null){
			try{
				systemUserService.addUser(user, loginedUser);
				return SUCCESS;
			} catch (Exception ex){
				return ERROR;
			}
		} else {
			return "timeout";
		}
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}
	
}
