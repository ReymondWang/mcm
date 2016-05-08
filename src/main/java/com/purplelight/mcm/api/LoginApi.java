package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.LoginParameter;
import com.purplelight.mcm.api.result.LoginResult;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.ISystemUserService;
import com.purplelight.mcm.util.StringUtil;

public class LoginApi extends BaseApi {
	private static final long serialVersionUID = -6032398435463595761L;

	private String resultInfo;
	
	@Resource
	private ISystemUserService systemUserService;
	
	public String execute() throws Exception{
		String loginInfo = getJson();
		LoginResult result = new LoginResult();
		Gson gson = new Gson();
		
		if (!StringUtil.IsNullOrEmpty(loginInfo)){
			LoginParameter lp = new Gson().fromJson(loginInfo, LoginParameter.class);
			if (checkToken(lp.getToken())){
				SystemUser user = new SystemUser();
				// 尝试使用用户编号登录
				user.setUserCode(lp.getLoginId());
				user = systemUserService.login(user, lp.getPassword());
				
				// 如果失败，尝试使用邮箱登录
				if (user == null){
					user = new SystemUser();
					user.setEmail(lp.getLoginId());
					user = systemUserService.login(user, lp.getPassword());
				}
				
				// 如果失败，尝试使用手机号登录
				if (user == null){
					user = new SystemUser();
					user.setPhone(lp.getLoginId());
					user = systemUserService.login(user, lp.getPassword());
				}
				
				if (user != null){
					result.setSuccess(true);
					result.setUser(user);
				} else {
					result.setSuccess(false);
					result.setMessage(getText("msg_login_failed"));
				}
			} else {
				result.setSuccess(false);
				result.setMessage(getText("msg_illegal_request_info"));
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
