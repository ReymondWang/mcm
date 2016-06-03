package com.purplelight.mcm.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.result.NotificationCntResult;
import com.purplelight.mcm.dao.IAppFunctionDao;
import com.purplelight.mcm.entity.AppFunction;
import com.purplelight.mcm.entity.UserBindSystem;
import com.purplelight.mcm.outtersystem.result.NoticeCntResult;
import com.purplelight.mcm.service.INotificationService;
import com.purplelight.mcm.service.IUserBindSystemService;
import com.purplelight.mcm.util.ConvertUtil;
import com.purplelight.mcm.util.HttpUtil;
import com.purplelight.mcm.util.StringUtil;

public class RedStarOmNotificationServiceImpl extends BaseServiceImpl implements INotificationService {

	@Resource
	private IAppFunctionDao appFuncDao;
	
	@Resource
	private IUserBindSystemService userBindSystemService;
	
	@Override
	public NotificationCntResult getNotificationCnt(int userId, int appId) {
		NotificationCntResult result = new NotificationCntResult();
		Gson gson = new Gson();
		
		AppFunction appFunc = appFuncDao.getById(appId);
		if (appFunc == null){
			result.setSuccess(false);
			result.setMessage(getMessage("msg_no_binded_function"));
			
			return result;
		}
		int systemId = appFunc.getOutterSystem().getId();
		String notificationUrl = appFunc.getStatUrl();
		if (StringUtil.IsNullOrEmpty(notificationUrl)){
			result.setSuccess(false);
			result.setMessage(getMessage("msg_no_notification_interface"));
			
			return result;
		}
		
		UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
		if (bindSystem == null){
			result.setSuccess(false);
			result.setMessage(getMessage("msg_no_system_binded"));
			
			return result;
		}
		
		long milli = System.currentTimeMillis();
		String nonce = String.valueOf(milli);
		
		String str = ConvertUtil.ConvertToDateTimeStr(new Timestamp(milli));
		System.out.println(str);
		
		String token = bindSystem.getToken();
		String sign = HttpUtil.generateDynamicToken(token, nonce, bindSystem.getMeachineCode());
		
		HashMap<String, String> map = new HashMap<>();
		map.put("token", token);
		map.put("nonce", nonce);
		map.put("sign", sign);
		
		String responseJson = HttpUtil.GetDataFromNet(notificationUrl, map, HttpUtil.GET);
		NoticeCntResult bsResult = gson.fromJson(responseJson, NoticeCntResult.class);
		result.setSuccess(bsResult.isSuccess());
		result.setMessage(bsResult.getMessage());
		result.setCount(bsResult.getObj());
		
		return result;
	}
	
	

}
