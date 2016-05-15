package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.RegisterParameter;
import com.purplelight.mcm.api.result.RegisterResult;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.ISystemUserService;
import com.purplelight.mcm.util.StringUtil;

public class RegisterApi extends BaseApi {
	private static final long serialVersionUID = -5088942414971553442L;

	@Resource
	private ISystemUserService systemUserService;
	
	@Override
	public String execute() throws Exception{
		SystemUser loginedUser = getUpdateUser();
		String registerInfo = getJson();
		RegisterResult result = new RegisterResult();
		Gson gson = new Gson();
		
		if (!StringUtil.IsNullOrEmpty(registerInfo)){
			RegisterParameter parameter = gson.fromJson(registerInfo, RegisterParameter.class);
			if (checkToken(parameter.getToken())){
				result = checkParameter(parameter);
				if (result.isSuccess()){
					SystemUser user = new SystemUser();
					user.setUserCode(parameter.getLoginId());
					user.setUserName(parameter.getUserName());
					user.setEmail(parameter.getEmail());
					user.setPhone(parameter.getPhone());
					user.setPassword(parameter.getPassword());
					
					if (systemUserService.hasThisUser(user)){
						result.setSuccess(false);
						result.setMessage(getText("user_can_not_equals"));
					} else {
						systemUserService.addUser(user, loginedUser);
						
						result.setSuccess(true);
						result.setUser(systemUserService.getUser(user));
					}
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
	
	private RegisterResult checkParameter(RegisterParameter parameter){
		RegisterResult result = new RegisterResult();
		result.setSuccess(true);
		
		if (StringUtil.IsNullOrEmpty(parameter.getLoginId())){
			result.setSuccess(false);
			result.setMessage(getText("msg_usercode_cannot_empty"));
			
			return result;
		}
		if (StringUtil.IsNullOrEmpty(parameter.getUserName())){
			result.setSuccess(false);
			result.setMessage(getText("msg_username_cannnot_empty"));
			
			return result;
		}
		
		return result;
	}
}
