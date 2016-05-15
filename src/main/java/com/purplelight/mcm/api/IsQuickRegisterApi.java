package com.purplelight.mcm.api;

import com.google.gson.Gson;
import com.purplelight.mcm.api.config.ConfigUtil;
import com.purplelight.mcm.api.result.QuickRegisterResult;

public class IsQuickRegisterApi extends BaseApi {
	private static final long serialVersionUID = 1632802775156034080L;

	@Override
	public String execute() throws Exception{
		QuickRegisterResult result = new QuickRegisterResult();
		String prop = ConfigUtil.config.getProperty("quick_register");
		result.setQuickRegister("true".equals(prop));
		result.setSuccess(true);
		
		Gson gson = new Gson();
		setResultInfo(gson.toJson(result));
		
		return SUCCESS;
	}
	
}
