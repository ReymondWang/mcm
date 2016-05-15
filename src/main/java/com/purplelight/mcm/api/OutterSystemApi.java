package com.purplelight.mcm.api;

import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.Parameter;
import com.purplelight.mcm.api.result.OutterSystemResult;
import com.purplelight.mcm.bean.OutterSystemBindInfo;
import com.purplelight.mcm.service.IUserBindSystemService;
import com.purplelight.mcm.util.StringUtil;

public class OutterSystemApi extends BaseApi {
	private static final long serialVersionUID = -7280002303773439512L;
	
	@Resource
	private IUserBindSystemService userBindSystemService;
	
	@Override
	public String execute() throws Exception{
		
		OutterSystemResult result = new OutterSystemResult();
		Gson gson = new Gson();
		String requestJson = getJson();
		
		if (!StringUtil.IsNullOrEmpty(requestJson)){
			Parameter parameter = gson.fromJson(requestJson, Parameter.class);
			
			if (checkToken(parameter.getToken())){
				List<OutterSystemBindInfo> list = userBindSystemService.getUserBindInfo(Integer.parseInt(parameter.getLoginId()));
				result.setSuccess(true);
				result.setSystemList(list);
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
