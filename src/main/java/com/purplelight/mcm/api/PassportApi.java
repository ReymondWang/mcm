package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.PassportFileParameter;
import com.purplelight.mcm.api.parameter.PassportParameter;
import com.purplelight.mcm.api.result.PassportFileResult;
import com.purplelight.mcm.api.result.PassportResult;
import com.purplelight.mcm.service.IPassportService;
import com.purplelight.mcm.util.ConvertUtil;
import com.purplelight.mcm.util.StringUtil;

public class PassportApi extends BaseApi {
	private static final long serialVersionUID = -7270833626389760735L;

	@Resource
	private IPassportService rsOmPassportService;
	
	public String execute() throws Exception{
		PassportResult result = new PassportResult();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			PassportParameter parameter = gson.fromJson(json, PassportParameter.class);
			if (checkToken(parameter.getToken())){
				int userId = ConvertUtil.toInt(parameter.getLoginId());
				int systemId = parameter.getSystemId();
				String projectId = parameter.getProjectId();
				String category = parameter.getCategory();
				int pageNo = parameter.getPageNo();
				int pageSize = parameter.getPageSize();
				
				result = rsOmPassportService.getPassportList(userId, systemId, projectId, category, pageNo, pageSize);
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
	
	public String files() throws Exception{
		PassportFileResult result = new PassportFileResult();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			PassportFileParameter parameter = gson.fromJson(json, PassportFileParameter.class);
			if (checkToken(parameter.getToken())){
				int userId = ConvertUtil.toInt(parameter.getLoginId());
				int systemId = parameter.getSystemId();
				int itemId = parameter.getItemId();
				
				result = rsOmPassportService.getPassportFiles(userId, systemId, itemId);
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
