package com.purplelight.mcm.api;

import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.AppFuncParameter;
import com.purplelight.mcm.api.result.AppFunctionResult;
import com.purplelight.mcm.entity.AppFunction;
import com.purplelight.mcm.service.IAppFunctionService;
import com.purplelight.mcm.util.McmConstant;
import com.purplelight.mcm.util.StringUtil;

public class AppFunctionApi extends BaseApi {

	private static final long serialVersionUID = -6326328313150425347L;

	private String resultInfo;
	
	@Resource
	private IAppFunctionService appFuncService;
	
	public String execute() throws Exception{
		AppFunctionResult result = new AppFunctionResult();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			AppFuncParameter parameter = gson.fromJson(json, AppFuncParameter.class);
			if (checkToken(parameter.getToken())){
				int fragment = Integer.parseInt(parameter.getFragment()) ;
				try{
					List<AppFunction> topAdvs = 
							appFuncService.getAppFuncByFragmentAndPart(fragment, McmConstant.FragmentPart.TOP);
					List<AppFunction> funcs = 
							appFuncService.getAppFuncByFragmentAndPart(fragment, McmConstant.FragmentPart.BODY);
					List<AppFunction> notices = 
							appFuncService.getAppFuncByFragmentAndPart(fragment, McmConstant.FragmentPart.FOOT);
					
					result.setSuccess(true);
					result.setTopList(topAdvs);
					result.setBodyList(funcs);
					result.setFootList(notices);
					
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

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
	
}
