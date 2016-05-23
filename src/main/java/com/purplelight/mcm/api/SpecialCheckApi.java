package com.purplelight.mcm.api;

import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.bean.SpecialItemCheckResult;
import com.purplelight.mcm.api.parameter.SpecialItemParameter;
import com.purplelight.mcm.api.parameter.SpecialItemSubmitParameter;
import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.api.result.SpecialItemResult;
import com.purplelight.mcm.service.ISpecialCheckService;
import com.purplelight.mcm.util.ConvertUtil;
import com.purplelight.mcm.util.StringUtil;

public class SpecialCheckApi extends BaseApi {
	private static final long serialVersionUID = 2801846344181764869L;

	@Resource
	private ISpecialCheckService rsOmSpecialCheckervice;
	
	public String execute() throws Exception{
		SpecialItemResult result = new SpecialItemResult();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			SpecialItemParameter parameter = gson.fromJson(json, SpecialItemParameter.class);
			if (checkToken(parameter.getToken())){
				int userId = ConvertUtil.toInt(parameter.getLoginId());
				int systemId = parameter.getSystemId();
				int reportId = parameter.getReportId();
				boolean onlyUnChecked = parameter.isOnlyUnChecked();
				int pageNo = parameter.getPageNo();
				int pageSize = parameter.getPageSize();
				
				result = rsOmSpecialCheckervice.getSpecialCheckItems(userId
						, systemId, reportId, onlyUnChecked, pageNo, pageSize);
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
	
	public String submit() throws Exception{
		Result result = new Result();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			SpecialItemSubmitParameter parameter = gson.fromJson(json, SpecialItemSubmitParameter.class);
			if (checkToken(parameter.getToken())){
				int userId = ConvertUtil.toInt(parameter.getLoginId());
				int systemId = parameter.getSystemId();
				int itemId = parameter.getItemId();
				List<SpecialItemCheckResult> results = parameter.getResults();
				List<String> images = parameter.getImages();
				
				result = rsOmSpecialCheckervice.submitSpecialCheckItem(userId, systemId, itemId, results, images);
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
