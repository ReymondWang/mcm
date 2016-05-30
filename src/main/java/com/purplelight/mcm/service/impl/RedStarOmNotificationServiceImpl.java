package com.purplelight.mcm.service.impl;

import java.util.HashMap;
import java.util.Locale;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.purplelight.mcm.api.result.NotificationCntResult;
import com.purplelight.mcm.dao.IAppFunctionDao;
import com.purplelight.mcm.entity.AppFunction;
import com.purplelight.mcm.entity.UserBindSystem;
import com.purplelight.mcm.outtersystem.result.NoticeCntResult;
import com.purplelight.mcm.service.INotificationService;
import com.purplelight.mcm.service.IUserBindSystemService;
import com.purplelight.mcm.util.HttpUtil;
import com.purplelight.mcm.util.StringUtil;

public class RedStarOmNotificationServiceImpl implements INotificationService {

	@Resource
	private IAppFunctionDao appFuncDao;
	
	@Resource
	private IUserBindSystemService userBindSystemService;
	
	@Override
	public NotificationCntResult getNotificationCnt(int userId, int appId) {
		NotificationCntResult result = new NotificationCntResult();
		Gson gson = new Gson();
		
		LocalizedTextUtil.findDefaultText("", Locale.getDefault());
		
		AppFunction appFunc = appFuncDao.getById(appId);
		if (appFunc == null){
			result.setSuccess(false);
			result.setMessage("没有找到对应的注册功能");
			
			return result;
		}
		int systemId = appFunc.getOutterSystem().getId();
		String notificationUrl = appFunc.getStatUrl();
		if (StringUtil.IsNullOrEmpty(notificationUrl)){
			result.setSuccess(false);
			result.setMessage("该功能没有定义提醒接口");
			
			return result;
		}
		
		UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
		if (bindSystem == null){
			result.setSuccess(false);
			result.setMessage("用户没有绑定外部系统");
			
			return result;
		}
		String token = bindSystem.getToken();
		
		HashMap<String, String> map = new HashMap<>();
		map.put("token", token);
		String responseJson = HttpUtil.GetDataFromNet(notificationUrl, map, HttpUtil.POST);
		NoticeCntResult bsResult = gson.fromJson(responseJson, NoticeCntResult.class);
		result.setSuccess(bsResult.isSuccess());
		result.setMessage(bsResult.getMessage());
		result.setCount(bsResult.getObj());
		
		return result;
	}

}
