package com.purplelight.mcm.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.bean.EstimateItem;
import com.purplelight.mcm.api.bean.EstimateReport;
import com.purplelight.mcm.api.parameter.EstimateUploadParameter;
import com.purplelight.mcm.api.result.EstimateItemResult;
import com.purplelight.mcm.api.result.EstimateReportResult;
import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.api.result.SingleEstimateItemResult;
import com.purplelight.mcm.api.result.SingleEstimateReportResult;
import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.entity.UserBindSystem;
import com.purplelight.mcm.outtersystem.bean.QualityCheckItem;
import com.purplelight.mcm.outtersystem.bean.QualityCheckReport;
import com.purplelight.mcm.outtersystem.parameter.FixQualityItemParameter;
import com.purplelight.mcm.outtersystem.result.QualityCheckItemResult;
import com.purplelight.mcm.outtersystem.result.QualityCheckReportResult;
import com.purplelight.mcm.outtersystem.result.SingleQualityCheckItemResult;
import com.purplelight.mcm.outtersystem.result.SingleQualityCheckReportResult;
import com.purplelight.mcm.service.IEstimateService;
import com.purplelight.mcm.service.IOutterSystemService;
import com.purplelight.mcm.service.IUserBindSystemService;
import com.purplelight.mcm.util.ConvertUtil;
import com.purplelight.mcm.util.HttpUtil;
import com.purplelight.mcm.util.StringUtil;

public class RedStarOmEstimateServiceImpl implements IEstimateService {
	private static final String QUALITY_CHECK_ITEM = "app/getqualitycheckitems";
	private static final String QUALITY_SINGLE_CHECK_ITEM = "app/GetQualityCheckItem";
	private static final String QUALITY_CHECK_REPORT = "app/getqualitycheckreports";
	private static final String QUALITY_SINGLE_CHECK_REPORT = "app/getqualitycheckreport";
	private static final String FIX_QUALITY_FIX_ITEM = "app/FixQualityFixItem";
	
	@Resource
	private IOutterSystemService outterSystemService;
	
	@Resource
	private IUserBindSystemService userBindSystemService;
	
	@Override
	public EstimateItemResult getEstimateItemsByIncharger(int userId, int systemId, int reportId, int pageNo, int pageSize) {
		EstimateItemResult result = new EstimateItemResult();
		
		OutterSystem system = outterSystemService.getOutterSystemById(systemId);
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
			if (bindSystem != null){
				result = getQualityCheckItems(systemId, system.getSystemUrl(), bindSystem.getToken(), userId, 0, reportId, pageNo, pageSize);
			} else {
				result.setSuccess(false);
				result.setMessage("用户没有绑定该系统");
			}
		} else {
			result.setSuccess(false);
			result.setMessage("指定的外部系统不存在");
		}
		
		return result;
	}

	@Override
	public EstimateItemResult getEstimateItemsByChecker(int userId, int systemId, int reportId, int pageNo, int pageSize) {
		EstimateItemResult result = new EstimateItemResult();
		
		OutterSystem system = outterSystemService.getOutterSystemById(systemId);
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
			if (bindSystem != null){
				result = getQualityCheckItems(systemId, system.getSystemUrl(), bindSystem.getToken(), 0, userId, reportId, pageNo, pageSize);
			} else {
				result.setSuccess(false);
				result.setMessage("用户没有绑定该系统");
			}
		} else {
			result.setSuccess(false);
			result.setMessage("指定的外部系统不存在");
		}
		
		return result;
	}
	
	@Override
	public SingleEstimateItemResult getSingleEstimateItem(int userId, int systemId, int itemId) {
		SingleEstimateItemResult result = new SingleEstimateItemResult();
		
		OutterSystem system = outterSystemService.getOutterSystemById(systemId);
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
			if (bindSystem != null){
				result = getQualityCheckItem(system.getSystemUrl(), bindSystem.getToken(), itemId);
			} else {
				result.setSuccess(false);
				result.setMessage("用户没有绑定该系统");
			}
		} else {
			result.setSuccess(false);
			result.setMessage("指定的外部系统不存在");
		}
		
		return result;
	}
	
	@Override
	public EstimateReportResult getEstimateReports(int userId, int systemId, int pageNo, int pageSize) {
		EstimateReportResult result = new EstimateReportResult();
		
		OutterSystem system = outterSystemService.getOutterSystemById(systemId);
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
			if (bindSystem != null){
				result = getQualityCheckReports(system.getSystemUrl(), bindSystem.getToken(), pageNo, pageSize);
			} else {
				result.setSuccess(false);
				result.setMessage("用户没有绑定该系统");
			}
		} else {
			result.setSuccess(false);
			result.setMessage("指定的外部系统不存在");
		}
		
		return result;
	}

	@Override
	public SingleEstimateReportResult getSingleEstimateReport(int userId, int systemId, int reportId) {
		SingleEstimateReportResult result = new SingleEstimateReportResult();
		
		OutterSystem system = outterSystemService.getOutterSystemById(systemId);
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
			if (bindSystem != null){
				result = getQualityCheckReport(system.getSystemUrl(), bindSystem.getToken(), reportId);
			} else {
				result.setSuccess(false);
				result.setMessage("用户没有绑定该系统");
			}
		} else {
			result.setSuccess(false);
			result.setMessage("指定的外部系统不存在");
		}
		
		return result;
	}
	
	@Override
	public Result saveEstimateItem(EstimateUploadParameter parameter) {
		Result result = new Result();
		
		int userId = Integer.parseInt(parameter.getLoginId());
		OutterSystem system = outterSystemService.getOutterSystemById(parameter.getSystemId());
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, parameter.getSystemId());
			if (bindSystem != null){
				result = fixQualityFixItem(system.getSystemUrl(), bindSystem.getToken(), parameter);
			} else {
				result.setSuccess(false);
				result.setMessage("用户没有绑定该系统");
			}
		} else {
			result.setSuccess(false);
			result.setMessage("指定的外部系统不存在");
		}
		
		return result;
	}
	
	/**
	 * 调用红星OM系统获取质量检查的信息列表
	 * @param token
	 * @param inChargeId
	 * @param checkPersonId
	 * @param checkId
	 * @param page
	 * @param pageNo
	 * @return
	 */
	private EstimateItemResult getQualityCheckItems(int systemId, String systemUrl, String token, int inChargeId, int checkPersonId, int checkId, int page, int pageNo){
		EstimateItemResult result = new EstimateItemResult();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
//		map.put("inchargeid", ConvertUtil.toString(inChargeId));
		map.put("inchargeid", "0");
		map.put("checkpersonid", ConvertUtil.toString(checkPersonId));
		map.put("checkid", ConvertUtil.toString(checkId));
		map.put("page", ConvertUtil.toString(page));
		map.put("numofpage", ConvertUtil.toString(pageNo));
		
		String responseJson = HttpUtil.GetDataFromNet(systemUrl + QUALITY_CHECK_ITEM, map, HttpUtil.POST);
		if (!StringUtil.IsNullOrEmpty(responseJson)){
			QualityCheckItemResult qcResult = new Gson().fromJson(responseJson, QualityCheckItemResult.class);
			result.setSuccess(qcResult.isSuccess());
			result.setMessage(qcResult.getMessage());
			if (result.isSuccess()){
				List<EstimateItem> esItems = new ArrayList<>();
				for (QualityCheckItem item : qcResult.getObj()){
					EstimateItem esItem = new EstimateItem();
					esItem.setId(item.getId());
					esItem.setReportId(item.getCheckId());
					esItem.setProjectId(item.getProjectId());
					esItem.setProjectName(item.getProjectName());
					esItem.setAreaName(item.getPeriodName());
					esItem.setCategory(item.getCatalog());
					esItem.setCharacter(item.getCharacter());
					esItem.setDescription(item.getDescript());
					esItem.setThumbs(ConvertUtil.batchAddSystemUrl(systemUrl, item.getThumbnail()));
					esItem.setImages(ConvertUtil.batchAddSystemUrl(systemUrl, item.getImages()));
					esItem.setInChargePersonId(item.getInChargePerson());
					esItem.setInChargePersonName(item.getInChargePersonName());
					esItem.setCheckPersonId(item.getCheckPerson());
					esItem.setCheckPersonName(item.getInChargePersonName());
					esItem.setPlanDate(ConvertUtil.dateFormat(item.getPlanDate()));
					esItem.setBeginDate(ConvertUtil.dateFormat(item.getBeginDate()));
					esItem.setEndDate(ConvertUtil.dateFormat(item.getEndDate()));
					esItem.setImprovmentAction(item.getImprovementAction());
					esItem.setFixedThumbs(ConvertUtil.batchAddSystemUrl(systemUrl, item.getFixedThumbnail()));
					esItem.setFixedImages(ConvertUtil.batchAddSystemUrl(systemUrl, item.getFixedImages()));
					esItem.setOutterSystemId(systemId);
					
					esItems.add(esItem);
				}
				result.setItems(esItems);
			}
		} else {
			result.setSuccess(false);
			result.setMessage("业务系统没有返回数据");
		}
		
		return result;
	}
	
	private SingleEstimateItemResult getQualityCheckItem(String systemUrl, String token, int itemId){
		SingleEstimateItemResult result = new SingleEstimateItemResult();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("id", String.valueOf(itemId));
		
		String responseJson = HttpUtil.GetDataFromNet(systemUrl + QUALITY_SINGLE_CHECK_ITEM, map, HttpUtil.POST);
		if (!StringUtil.IsNullOrEmpty(responseJson)){
			SingleQualityCheckItemResult qcResult = new Gson().fromJson(responseJson, SingleQualityCheckItemResult.class);
			result.setSuccess(qcResult.isSuccess());
			result.setMessage(qcResult.getMessage());
			if (result.isSuccess() && qcResult.getObj() != null){
				QualityCheckItem obj = qcResult.getObj();
				
				EstimateItem item = new EstimateItem();
				item.setId(obj.getId());
				item.setReportId(obj.getCheckId());
				item.setProjectId(obj.getProjectId());
				item.setProjectName(obj.getProjectName());
				item.setAreaName(obj.getPeriodName());
				item.setCategory(obj.getCatalog());
				item.setCharacter(obj.getCharacter());
				item.setDescription(obj.getDescript());
				item.setThumbs(ConvertUtil.batchAddSystemUrl(systemUrl, obj.getThumbnail()));
				item.setImages(ConvertUtil.batchAddSystemUrl(systemUrl, obj.getImages()));
				item.setInChargePersonId(obj.getInChargePerson());
				item.setInChargePersonName(obj.getInChargePersonName());
				item.setCheckPersonId(obj.getCheckPerson());
				item.setCheckPersonName(obj.getInChargePersonName());
				item.setPlanDate(ConvertUtil.dateFormat(obj.getPlanDate()));
				item.setBeginDate(ConvertUtil.dateFormat(obj.getBeginDate()));
				item.setEndDate(ConvertUtil.dateFormat(obj.getEndDate()));
				item.setImprovmentAction(obj.getImprovementAction());
				item.setFixedThumbs(ConvertUtil.batchAddSystemUrl(systemUrl, obj.getFixedThumbnail()));
				item.setFixedImages(ConvertUtil.batchAddSystemUrl(systemUrl, obj.getFixedImages()));
				
				result.setItem(item);
			}
		} else {
			result.setSuccess(false);
			result.setMessage("业务系统没有返回数据");
		}
		
		return result;
	}
	
	/**
	 * 从业务系统获取第三方评估报告
	 * @param systemUrl
	 * @param token
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	private EstimateReportResult getQualityCheckReports(String systemUrl, String token, int pageNo, int pageSize){
		EstimateReportResult result = new EstimateReportResult();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("page", ConvertUtil.toString(pageNo));
		map.put("numofpage", ConvertUtil.toString(pageNo));
		
		String responseJson = HttpUtil.GetDataFromNet(systemUrl + QUALITY_CHECK_REPORT, map, HttpUtil.POST);
		if (!StringUtil.IsNullOrEmpty(responseJson)){
			QualityCheckReportResult qcResult = new Gson().fromJson(responseJson, QualityCheckReportResult.class);
			result.setSuccess(qcResult.isSuccess());
			result.setMessage(qcResult.getMessage());
			if (result.isSuccess()){
				List<EstimateReport> esItems = new ArrayList<>();
				for (QualityCheckReport item : qcResult.getObj()){
					EstimateReport esItem = new EstimateReport();
					esItem.setId(item.getId());
					esItem.setProjectId(item.getProjectId());
					esItem.setProjectName(item.getProjectName());
					esItem.setAreaId(item.getPeriodId());
					esItem.setAreaName(item.getPeriodName());
					esItem.setCategory(item.getCatalog());
					esItem.setCheckType(item.getCheckType());
					esItem.setReportDate(ConvertUtil.dateFormat(item.getReportDate()));
					esItem.setInChargePerson(item.getInChargePerson());
					esItem.setReporter(item.getReporter());
					esItem.setSupervisionId(item.getSupervisionId());
					esItem.setSupervisionName(item.getSupervision());
					esItem.setConstractionId(item.getConstructionId());
					esItem.setConstractionName(item.getConstruction());
					esItem.setRemark(item.getRemark());
					esItem.setGradeSCSL(item.getGradeSCSL());
					esItem.setGradeMPFH(item.getGradeMPFH());
					esItem.setGradeGGBW(item.getGradeGGBW());
					esItem.setGradeWLMGG(item.getGradeWLMGG());
					esItem.setGradeYLGG(item.getGradeYLGG());
					esItem.setGradeXMZH(item.getGradeXMZH());
					esItem.setGradeSCDF(item.getGradeSCDF());
					esItem.setGradeZLKF(item.getGradeZLKF());
					esItem.setGradeGLXW(item.getGradeGLXW());
					esItem.setGradeAQWM(item.getGradeAQWM());
					esItem.setGradeZHDF(item.getGradeZHDF());
					
					esItems.add(esItem);
				}
				result.setReports(esItems);
			}
		} else {
			result.setSuccess(false);
			result.setMessage("业务系统没有返回数据");
		}
		
		return result;
	}
	
	private SingleEstimateReportResult getQualityCheckReport(String systemUrl, String token, int reportId){
		SingleEstimateReportResult result = new SingleEstimateReportResult();
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("id", ConvertUtil.toString(reportId));
		
		String responseJson = HttpUtil.GetDataFromNet(systemUrl + QUALITY_SINGLE_CHECK_REPORT, map, HttpUtil.POST);
		if (!StringUtil.IsNullOrEmpty(responseJson)){
			SingleQualityCheckReportResult qcResult = new Gson().fromJson(responseJson, SingleQualityCheckReportResult.class);
			result.setSuccess(qcResult.isSuccess());
			result.setMessage(qcResult.getMessage());
			if (result.isSuccess() && qcResult.getObj() != null){
				QualityCheckReport obj = qcResult.getObj();
				
				EstimateReport esItem = new EstimateReport();
				esItem.setId(obj.getId());
				esItem.setProjectId(obj.getProjectId());
				esItem.setProjectName(obj.getProjectName());
				esItem.setAreaId(obj.getPeriodId());
				esItem.setAreaName(obj.getPeriodName());
				esItem.setCategory(obj.getCatalog());
				esItem.setCheckType(obj.getCheckType());
				esItem.setReportDate(ConvertUtil.dateFormat(obj.getReportDate()));
				esItem.setInChargePerson(obj.getInChargePerson());
				esItem.setReporter(obj.getReporter());
				esItem.setSupervisionId(obj.getSupervisionId());
				esItem.setSupervisionName(obj.getSupervision());
				esItem.setConstractionId(obj.getConstructionId());
				esItem.setConstractionName(obj.getConstruction());
				esItem.setRemark(obj.getRemark());
				esItem.setGradeSCSL(obj.getGradeSCSL());
				esItem.setGradeMPFH(obj.getGradeMPFH());
				esItem.setGradeGGBW(obj.getGradeGGBW());
				esItem.setGradeWLMGG(obj.getGradeWLMGG());
				esItem.setGradeYLGG(obj.getGradeYLGG());
				esItem.setGradeXMZH(obj.getGradeXMZH());
				esItem.setGradeSCDF(obj.getGradeSCDF());
				esItem.setGradeZLKF(obj.getGradeZLKF());
				esItem.setGradeGLXW(obj.getGradeGLXW());
				esItem.setGradeAQWM(obj.getGradeAQWM());
				esItem.setGradeZHDF(obj.getGradeZHDF());
				
				result.setReport(esItem);
			}
		} else {
			result.setSuccess(false);
			result.setMessage("业务系统没有返回数据");
		}
		
		return result;
	}
	
	private Result fixQualityFixItem(String systemUrl, String token, EstimateUploadParameter parameter){
		Result result = new Result();
		Gson gson = new Gson();
		try {
			FixQualityItemParameter submitParams = new FixQualityItemParameter();
			submitParams.setToken(token);
			submitParams.setId(parameter.getItemId());
			submitParams.setAction(URLEncoder.encode(parameter.getImprovementAction(), "utf-8"));
			submitParams.setDonedate(parameter.getDate());
			submitParams.setImages(parameter.getImageFileNames());
			String json = gson.toJson(submitParams);
//			String fileName = System.currentTimeMillis() + ".json";
//			File file = new File("/Users/wangyn/" + fileName);
//			if (!file.exists()){
//				file.createNewFile();
//			}
//			FileOutputStream outStream = new FileOutputStream(file);
//			byte[] buffer = json.getBytes();
//			outStream.write(buffer, 0, buffer.length);
//			outStream.close();
			String responseJson = HttpUtil.PostJosn(systemUrl + FIX_QUALITY_FIX_ITEM, json);
			result = gson.fromJson(responseJson, Result.class);
		} catch (IOException e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}

}
