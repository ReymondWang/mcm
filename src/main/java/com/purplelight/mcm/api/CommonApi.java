package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.ProjectParameter;
import com.purplelight.mcm.api.parameter.TokenParameter;
import com.purplelight.mcm.api.result.ProjectResult;
import com.purplelight.mcm.api.result.TokenResult;
import com.purplelight.mcm.service.ICommonService;
import com.purplelight.mcm.util.ConvertUtil;
import com.purplelight.mcm.util.StringUtil;

public class CommonApi extends BaseApi {
	private static final long serialVersionUID = 5737627482998191979L;

	@Resource
	private ICommonService rsOmCommonService;
	
	public String projects() throws Exception{
		ProjectResult result = new ProjectResult();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			ProjectParameter parameter = gson.fromJson(json, ProjectParameter.class);
			if (checkToken(parameter.getToken())){
				int userId = ConvertUtil.toInt(parameter.getLoginId());
				int systemId = parameter.getSystemId();
				
				result = rsOmCommonService.getProjectList(userId, systemId);
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
	
	public String token() throws Exception{
		TokenResult result = new TokenResult();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			TokenParameter parameter = gson.fromJson(json, TokenParameter.class);
			int userId = Integer.parseInt(parameter.getLoginId());
			int systemId = parameter.getSystemId();
			result = rsOmCommonService.getToken(userId, systemId);
		} else {
			result.setSuccess(false);
			result.setMessage(getText("msg_no_request_json_info"));
		}
		
		setResultInfo(gson.toJson(result));
		
		return SUCCESS;
	}
	
}
