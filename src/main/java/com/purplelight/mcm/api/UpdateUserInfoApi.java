package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.UserInfoParameter;
import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.ISystemUserService;
import com.purplelight.mcm.util.StringUtil;

public class UpdateUserInfoApi extends BaseApi {
	private static final long serialVersionUID = 5911376849085909752L;
	
	@Resource
	private ISystemUserService systemUserService;
	
	public String execute() throws Exception{
		Result result = new Result();
		Gson gson = new Gson();
		SystemUser updateUser = getUpdateUser();
		
		String json = getJson();
		if (!StringUtil.IsNullOrEmpty(json)){
			UserInfoParameter parameter = gson.fromJson(json, UserInfoParameter.class);
			if (checkToken(parameter.getToken())){
				try{
					systemUserService.updateUser(parameter.getUser(), updateUser);
					
					result.setSuccess(true);
					result.setMessage(getText("msg_update_user_info_success"));
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
