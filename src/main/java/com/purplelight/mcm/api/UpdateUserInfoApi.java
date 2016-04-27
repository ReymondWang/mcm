package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.ISystemUserService;
import com.purplelight.mcm.util.StringUtil;

public class UpdateUserInfoApi extends BaseApi {
	private static final long serialVersionUID = 5911376849085909752L;
	
	private String resultInfo;
	
	@Resource
	private ISystemUserService systemUserService;
	
	public String execute() throws Exception{
		Result result = new Result();
		Gson gson = new Gson();
		SystemUser updateUser = getUpdateUser();
		
		String json = getJson();
		if (!StringUtil.IsNullOrEmpty(json)){
			try{
				SystemUser user = gson.fromJson(json, SystemUser.class);
				systemUserService.updateUser(user, updateUser);
				
				result.setSuccess(Result.SUCCESS);
				result.setMessage(getText("msg_update_user_info_success"));
			} catch (Exception ex){
				result.setSuccess(Result.ERROR);
				result.setMessage(ex.getMessage());
			}
		} else {
			result.setSuccess(Result.ERROR);
			result.setMessage(getText("msg_update_user_info_failed"));
		}
		setResultInfo(gson.toJson(result));
		
		return SUCCESS;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
	
}
