package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.ModifyPasswordParameter;
import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.service.ISystemUserService;
import com.purplelight.mcm.util.StringUtil;

public class ModifyPasswordApi extends BaseApi {
	private static final long serialVersionUID = -3031710309077849014L;

	private String resultInfo;
	
	@Resource
	private ISystemUserService systemUserService;
	
	@Override
	public String execute() throws Exception {
		Result result = new Result();
		String requestJson = getJson();
		Gson gson = new Gson();
		
		if (!StringUtil.IsNullOrEmpty(requestJson)){
			ModifyPasswordParameter parameter = gson.fromJson(requestJson, ModifyPasswordParameter.class);
			if (checkToken(parameter.getToken())){
				try{
					if (systemUserService.checkPassword(Integer.parseInt(parameter.getLoginId()), parameter.getOriginalPassword())){
						if (parameter.getNewPassword().equals(parameter.getConfirmPassword())){
							systemUserService.modifyPassword(Integer.parseInt(parameter.getLoginId()), parameter.getOriginalPassword());
							result.setSuccess(true);
						} else {
							result.setSuccess(false);
							result.setMessage(getText("msg_new_password_not_equals_confirm"));
						}
					} else {
						result.setSuccess(false);
						result.setMessage(getText("msg_original_password_error"));
					}
				} catch (Exception ex){
					result.setSuccess(false);
					result.setMessage(ex.getMessage());
				}
				
			} else {
				result.setSuccess(false);
				result.setMessage(getText("msg_no_request_json_info"));
			}
		} else {
			result.setSuccess(false);
			result.setMessage(getText("msg_no_request_json_info"));
		}
		
		resultInfo = gson.toJson(result);
		
		return SUCCESS;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
	
}
