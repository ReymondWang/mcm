package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.parameter.EstimateItemParameter;
import com.purplelight.mcm.api.parameter.EstimateReportParameter;
import com.purplelight.mcm.api.parameter.EstimateUploadParameter;
import com.purplelight.mcm.api.result.EstimateItemResult;
import com.purplelight.mcm.api.result.EstimateReportResult;
import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.api.result.SingleEstimateItemResult;
import com.purplelight.mcm.api.result.SingleEstimateReportResult;
import com.purplelight.mcm.service.IEstimateService;
import com.purplelight.mcm.util.ConvertUtil;
import com.purplelight.mcm.util.StringUtil;

public class EstimateApi extends BaseApi {
	private static final long serialVersionUID = 428971347233956837L;

	private static final int INCHARGER = 1;
	private static final int CHECHER = 2;
	
	@Resource
	private IEstimateService rsOmEstimateService;
	
	public String items() throws Exception{
		EstimateItemResult result = new EstimateItemResult();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			EstimateItemParameter parameter = gson.fromJson(json, EstimateItemParameter.class);
			if (checkToken(parameter.getToken())){
				int userId = ConvertUtil.toInt(parameter.getLoginId());
				int systemId = parameter.getSystemId();
				int reportId = parameter.getReportId();
				int pageNo = parameter.getPageNo();
				int pageSize = parameter.getPageSize();
				if (INCHARGER == parameter.getType()){
					result = rsOmEstimateService.getEstimateItemsByIncharger(userId, systemId, reportId, pageNo, pageSize);
				} else if (CHECHER == parameter.getType()){
					result = rsOmEstimateService.getEstimateItemsByChecker(userId, systemId, reportId, pageNo, pageSize);
				} else {
					result.setSuccess(false);
					result.setMessage(getText("msg_illegal_request_info"));
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
	
	public String singleItem() throws Exception{
		SingleEstimateItemResult result = new SingleEstimateItemResult();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			EstimateItemParameter parameter = gson.fromJson(json, EstimateItemParameter.class);
			if (checkToken(parameter.getToken())){
				int userId = ConvertUtil.toInt(parameter.getLoginId());
				int systemId = parameter.getSystemId();
				int itemId = parameter.getReportId();
				
				result = rsOmEstimateService.getSingleEstimateItem(userId, systemId, itemId);
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
	
	public String reports() throws Exception{
		EstimateReportResult result = new EstimateReportResult();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			EstimateReportParameter parameter = gson.fromJson(json, EstimateReportParameter.class);
			if (checkToken(parameter.getToken())){
				int userId = ConvertUtil.toInt(parameter.getLoginId());
				int systemId = parameter.getSystemId();
				int pageNo = parameter.getPageNo();
				int pageSize = parameter.getPageSize();
				
				result = rsOmEstimateService.getEstimateReports(userId, systemId, pageNo, pageSize);
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
	
	public String singleReport() throws Exception{
		SingleEstimateReportResult result = new SingleEstimateReportResult();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			EstimateReportParameter parameter = gson.fromJson(json, EstimateReportParameter.class);
			if (checkToken(parameter.getToken())){
				int userId = ConvertUtil.toInt(parameter.getLoginId());
				int systemId = parameter.getSystemId();
				int reportId = parameter.getReportId();
				
				result = rsOmEstimateService.getSingleEstimateReport(userId, systemId, reportId);
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
			EstimateUploadParameter parameter = gson.fromJson(json, EstimateUploadParameter.class);
			if (checkToken(parameter.getToken())){
				result = rsOmEstimateService.saveEstimateItem(parameter);
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
