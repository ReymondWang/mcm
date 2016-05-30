package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.NotificationCntParameter;
import com.purplelight.mcm.api.result.NotificationCntResult;
import com.purplelight.mcm.service.INotificationService;
import com.purplelight.mcm.util.StringUtil;

public class NotificationApi extends BaseApi {
	private static final long serialVersionUID = -2842173580925126434L;

	@Resource
	private INotificationService rsOmNotificationService;
	
	public String execute() throws Exception{
		NotificationCntResult result = new NotificationCntResult();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			NotificationCntParameter parameter = gson.fromJson(json, NotificationCntParameter.class);
			if (checkToken(parameter.getToken())){
				int userId = Integer.parseInt(parameter.getLoginId());
				try{
					result = rsOmNotificationService.getNotificationCnt(userId, parameter.getAppId());
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
		
		setResultInfo(new Gson().toJson(result));
		
		return SUCCESS;
	}
	
}
