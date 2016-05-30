package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.bean.SystemUserInfo;
import com.purplelight.mcm.api.config.ConfigUtil;
import com.purplelight.mcm.api.parameter.LoginParameter;
import com.purplelight.mcm.api.result.LoginResult;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.ISystemUserService;
import com.purplelight.mcm.service.IUserBindSystemService;
import com.purplelight.mcm.util.ConvertUtil;
import com.purplelight.mcm.util.StringUtil;

public class LoginApi extends BaseApi {
	private static final long serialVersionUID = -6032398435463595761L;

	@Resource
	private ISystemUserService systemUserService;
	
	@Resource
	private IUserBindSystemService userBindSystemService;
	
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
					
					SystemUserInfo userInfo = new SystemUserInfo();
					userInfo.setId(ConvertUtil.toString(user.getId()));
					userInfo.setUserCode(user.getUserCode());
					userInfo.setUserName(user.getUserName());
					userInfo.setSex(ConvertUtil.toString(user.getSex()));
					userInfo.setEmail(user.getEmail());
					userInfo.setPhone(user.getPhone());
					userInfo.setAddress(user.getAddress());
					userInfo.setHeadImgPath(user.getHeadImgPath());
					userInfo.setToken(user.getToken());
					result.setUser(userInfo);
				} else {
					if ("true".equals(ConfigUtil.config.getProperty("quick_register"))){
						result = userBindSystemService.bindWithCreate(lp.getLoginId(), lp.getPassword(), getUpdateUser());
					} else {
						result.setSuccess(false);
						result.setMessage(getText("msg_login_failed"));
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
	
}
