package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.FeedbackParameter;
import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.service.IFeedbackService;
import com.purplelight.mcm.util.StringUtil;

public class FeedbackApi extends BaseApi {
	private static final long serialVersionUID = -3807476249981345383L;
	
	@Resource
	private IFeedbackService feedbackService;
	
	public String execute() throws Exception{
		Result result = new Result();
		Gson gson = new Gson();
		
		String json = getJson();
		if (!StringUtil.IsNullOrEmpty(json)){
			FeedbackParameter parameter = gson.fromJson(json, FeedbackParameter.class);
			if (checkToken(parameter.getToken())){
				try{
					feedbackService.addFeedback(parameter.getFeedback());
					
					result.setSuccess(true);
					result.setMessage(getText("msg_feedback_success"));
				} catch (Exception ex){
					result.setSuccess(false);
					result.setMessage(ex.getMessage());
					ex.printStackTrace();
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
