package com.purplelight.mcm.api;

import com.google.gson.Gson;
import com.purplelight.mcm.api.result.NotificationCntResult;

import java.util.Random;

public class StatTestApi extends BaseApi {
	private static final long serialVersionUID = -114910798300076266L;
	
	public String execute() throws Exception{
		Random random = new Random();
		int cnt = random.nextInt(100) + 1;
		NotificationCntResult result = new NotificationCntResult();
		result.setCount(cnt);
		result.setSuccess(true);
		
		setResultInfo(new Gson().toJson(result));
		
		return SUCCESS;
	}
	
}
