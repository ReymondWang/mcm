package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.UserInfoParameter;
import com.purplelight.mcm.api.result.BindUserResult;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.ISystemUserService;
import com.purplelight.mcm.util.StringUtil;

public class BindUserApi extends BaseApi {
	private static final long serialVersionUID = 6675574176633007748L;
	
	@Resource
	private ISystemUserService systemUserService;
	
	@Override
	public String execute(){
		BindUserResult result = new BindUserResult();
		Gson gson = new Gson();
		SystemUser updateUser = getUpdateUser();
		
		String json = getJson();
		if (!StringUtil.IsNullOrEmpty(json)){
			UserInfoParameter parameter = gson.fromJson(json, UserInfoParameter.class);
			if (checkToken(parameter.getToken())){
				try{
					if (systemUserService.hasThisUser(parameter.getUser())){
						systemUserService.updateUser(parameter.getUser(), updateUser);
					} else {
						systemUserService.addUser(parameter.getUser(), updateUser);
					}
					
					SystemUser user = systemUserService.getUser(parameter.getUser());
					result.setUser(user);
					result.setSuccess(true);
					result.setMessage(getText("msg_bind_user_success"));
				} catch (Exception ex){
					result.setSuccess(false);
					result.setMessage(ex.getMessage());
				}
			} else {
				result.setSuccess(false);
				result.setMessage(getText("msg_illegal_request_info"));
			}
		} else {
			result.setSuccess(false);
			result.setMessage(getText("msg_no_request_json_info"));
		}
		setResultInfo(gson.toJson(result));
		
		return SUCCESS;
	}
	
}
