package com.purplelight.mcm.api;

import java.util.HashMap;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.BindUserParameter;
import com.purplelight.mcm.api.result.BindUser;
import com.purplelight.mcm.api.result.BindUserResult;
import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.entity.UserBindSystem;
import com.purplelight.mcm.service.IOutterSystemService;
import com.purplelight.mcm.service.ISystemUserService;
import com.purplelight.mcm.service.IUserBindSystemService;
import com.purplelight.mcm.util.HttpUtil;
import com.purplelight.mcm.util.StringUtil;

public class BindUserApi extends BaseApi {
	private static final long serialVersionUID = 6675574176633007748L;
	
	@Resource
	private IUserBindSystemService userBindSystemService;
	
	@Resource
	private IOutterSystemService outterSystemService;
	
	@Resource
	private ISystemUserService systemUserService;
	
	@Override
	public String execute(){
		Result result = new Result();
		Gson gson = new Gson();
		SystemUser updateUser = getUpdateUser();
		
		String json = getJson();
		if (!StringUtil.IsNullOrEmpty(json)){
			BindUserParameter parameter = gson.fromJson(json, BindUserParameter.class);
			if (checkToken(parameter.getToken())){
				try{
					OutterSystem system = outterSystemService.getOutterSystemById(parameter.getSystemId());
					if (system != null){
						HashMap<String, String> map = new HashMap<>();
			            map.put("name", parameter.getUserCode());
			            map.put("password", parameter.getPassword());
			            String bindJson = HttpUtil.GetDataFromNet(system.getSystemUrl() + system.getValidationUrl(), map, HttpUtil.POST);
						if (!StringUtil.IsNullOrEmpty(bindJson)){
							BindUserResult bindResult = gson.fromJson(bindJson, BindUserResult.class);
							if (bindResult.isSuccess()){
								BindUser user = bindResult.getObj();
								UserBindSystem bindSystem = new UserBindSystem();
								bindSystem.setUser(systemUserService.getUserbyId(Integer.parseInt(parameter.getLoginId())));
								bindSystem.setOutterSystem(system);
								bindSystem.setToken(user.getToken());
								userBindSystemService.addBindInfo(bindSystem, updateUser);
								
								result.setSuccess(true);
								result.setMessage(getText("msg_bind_user_success"));
							} else {
								result.setSuccess(false);
								result.setMessage(bindResult.getMessage());
							}
						} else {
							result.setSuccess(false);
							result.setMessage(String.format(getText("msg_outter_system_bind_failure")));
						}
					} else {
						result.setSuccess(false);
						result.setMessage(getText("msg_not_found_system"));
					}
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
	
	public String unBind() throws Exception{
		Result result = new Result();
		Gson gson = new Gson();
		
		String json = getJson();
		if (!StringUtil.IsNullOrEmpty(json)){
			BindUserParameter parameter = gson.fromJson(json, BindUserParameter.class);
			if (checkToken(parameter.getToken())){
				try{
					userBindSystemService.deleteBindInfo(Integer.parseInt(parameter.getLoginId()), parameter.getSystemId());
					
					result.setSuccess(true);
					result.setMessage(getText("msg_unbind_user_success"));
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
