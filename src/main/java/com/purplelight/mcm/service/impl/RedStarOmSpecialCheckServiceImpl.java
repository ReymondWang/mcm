package com.purplelight.mcm.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.bean.SpecialItem;
import com.purplelight.mcm.api.bean.SpecialItemCheckResult;
import com.purplelight.mcm.api.parameter.SpecialItemParameter;
import com.purplelight.mcm.api.parameter.SpecialItemSubmitParameter;
import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.api.result.SpecialItemResult;
import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.entity.UserBindSystem;
import com.purplelight.mcm.outtersystem.bean.ResultItem;
import com.purplelight.mcm.outtersystem.bean.SpecialCheckItem;
import com.purplelight.mcm.outtersystem.parameter.SpecialCheckItemDoneParameter;
import com.purplelight.mcm.outtersystem.parameter.SpecialCheckItemParameter;
import com.purplelight.mcm.outtersystem.result.SpecialCheckItemResult;
import com.purplelight.mcm.service.IOutterSystemService;
import com.purplelight.mcm.service.ISpecialCheckService;
import com.purplelight.mcm.service.IUserBindSystemService;
import com.purplelight.mcm.util.ConvertUtil;
import com.purplelight.mcm.util.HttpUtil;
import com.purplelight.mcm.util.StringUtil;

public class RedStarOmSpecialCheckServiceImpl extends BaseServiceImpl implements ISpecialCheckService {
	private static final String GET_SPECIAL_CHECK_ITEMS = "app/GetSpecialCheckItems";
	private static final String SPECIAL_CHECK_ITEM_DONE = "app/SpecialCheckItemDone";
	private static final String GET_ROOM_CHECK_ITEMS = "app/GetRoomCheckItems";
	private static final String ROOM_CHECK_ITEM_DONE = "app/RoomCheckItemDone";
	
	@Resource
	private IOutterSystemService outterSystemService;
	
	@Resource
	private IUserBindSystemService userBindSystemService;
	
	@Override
	public SpecialItemResult getSpecialCheckItems(SpecialItemParameter parameter) {
		int checkType = parameter.getCheckType();
		int userId = ConvertUtil.toInt(parameter.getLoginId());
		int systemId = parameter.getSystemId();
		int reportId = parameter.getReportId();
		boolean onlyUnChecked = parameter.isOnlyUnChecked();
		int pageNo = parameter.getPageNo();
		int pageSize = parameter.getPageSize();
		
		SpecialItemResult result = new SpecialItemResult();
		
		OutterSystem system = outterSystemService.getOutterSystemById(systemId);
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
			if (bindSystem != null){
				
				result = GetSpecialCheckItems(checkType
						, systemId
						, system.getSystemUrl()
						, bindSystem.getToken()
						, bindSystem.getMeachineCode()
						, reportId
						, onlyUnChecked
						, pageNo
						, pageSize);
			} else {
				result.setSuccess(false);
				result.setMessage(getMessage("msg_no_system_binded"));
			}
		} else {
			result.setSuccess(false);
			result.setMessage(getMessage("msg_no_system"));
		}
		return result;
	}

	@Override
	public Result submitSpecialCheckItem(SpecialItemSubmitParameter parameter) {
		Result result = new Result();
		
		int checkType = parameter.getCheckType();
		int userId = ConvertUtil.toInt(parameter.getLoginId());
		int systemId = parameter.getSystemId();
		int itemId = parameter.getItemId();
		List<SpecialItemCheckResult> results = parameter.getResults();
		List<String> images = parameter.getImages();
		
		OutterSystem system = outterSystemService.getOutterSystemById(systemId);
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
			if (bindSystem != null){
				result = SpecialCheckItemDone(checkType
						, system.getSystemUrl()
						, bindSystem.getToken()
						, bindSystem.getMeachineCode()
						, itemId
						, results
						, images);
			} else {
				result.setSuccess(false);
				result.setMessage(getMessage("msg_no_system_binded"));
			}
		} else {
			result.setSuccess(false);
			result.setMessage(getMessage("msg_no_system"));
		}
		
		return result;
	}
	
	private SpecialItemResult GetSpecialCheckItems(int checkType
			, int systemId
			, String systemUrl
			, String token
			, String meachineCode
			, int checkId
			, boolean onlyUnchecked
			, int page
			, int numOfPage){
		SpecialItemResult result = new SpecialItemResult();
		
		if (checkType == 1){
			systemUrl += GET_SPECIAL_CHECK_ITEMS;
		} else {
			systemUrl += GET_ROOM_CHECK_ITEMS;
		}
		
		String nonce = String.valueOf(System.currentTimeMillis());
		String sign = HttpUtil.generateDynamicToken(token, nonce, meachineCode);
		SpecialCheckItemParameter parameter = new SpecialCheckItemParameter();
		parameter.setCheckId(checkId);
		parameter.setToken(token);
		parameter.setNonce(nonce);
		parameter.setSign(sign);
		parameter.setOnlyUnchecked(onlyUnchecked);
		parameter.setPage(page);
		parameter.setNumOfPage(numOfPage);
		
		Gson gson = new Gson();
		
		String requestJson = gson.toJson(parameter);
		try{
			String responseJson = HttpUtil.PostJosn(systemUrl, requestJson);
			if (!StringUtil.IsNullOrEmpty(responseJson)){
				SpecialCheckItemResult bsResult = gson.fromJson(responseJson, SpecialCheckItemResult.class);
				result.setSuccess(bsResult.isSuccess());
				result.setMessage(bsResult.getMessage());
				if (bsResult.isSuccess()){
					List<SpecialItem> items = new ArrayList<>();
					
					if (bsResult.getObj() != null && bsResult.getObj().size() > 0){
						for (SpecialCheckItem bsItem : bsResult.getObj()){
							SpecialItem item = new SpecialItem();
							item.setId(bsItem.getId());
							item.setCheckType(checkType);
							item.setSystemId(systemId);
							item.setCategory(bsItem.getCatalog());
							item.setProjectName(bsItem.getProjectName());
							item.setAreaName(bsItem.getPeriodName() + " " + bsItem.getPartitionName());
							item.setCreateDate(ConvertUtil.dateFormat(bsItem.getCreateDate()));
							
							List<String> places = new ArrayList<>();
							places.add(bsItem.getPlace1());
							places.add(bsItem.getPlace2());
							places.add(bsItem.getPlace3());
							item.setPlaces(places);
							
							item.setBuildingId(bsItem.getBuildingId());
							item.setBuilding(bsItem.getBuilding());
							
							item.setCode(bsItem.getCode());
							item.setNames(Arrays.asList(bsItem.getName().split("\\\n")));
							item.setPersonName(bsItem.getPersonName());
							item.setRemark(bsItem.getRemark());
							item.setCheckDate(ConvertUtil.dateFormat(bsItem.getCheckDate()));
							item.setPassPercent(bsItem.getPassPercent());
							
							List<SpecialItemCheckResult> resultItems = new ArrayList<>();
							if (bsItem.getResultItems() != null && bsItem.getResultItems().size() > 0){
								for (ResultItem ri : bsItem.getResultItems()){
									SpecialItemCheckResult checkItem = new SpecialItemCheckResult();
									checkItem.setName(ri.getName());
									if ("√".equals(ri.getCheckresult())){
										checkItem.setResult(1);
									} else {
										checkItem.setResult(0);
									}
									resultItems.add(checkItem);
								}
							}
							item.setResultItems(resultItems);
							
							item.setThumbnail(bsItem.getThumbnail());
							item.setImages(bsItem.getImages());
							
							items.add(item);
						}
					}
					
					result.setItems(items);
				}
			} else {
				result.setSuccess(false);
				result.setMessage(getMessage("msg_no_response_data"));
			}
		} catch (IOException ex){
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}
		
		return result;
	}
	
	private Result SpecialCheckItemDone(int checkType
			, String systemUrl
			, String token
			, String meachineCode
			, int id
			, List<SpecialItemCheckResult> results
			, List<String> images){
		Result result = new Result();
		
		if (checkType == 1){
			systemUrl += SPECIAL_CHECK_ITEM_DONE;
		} else {
			systemUrl += ROOM_CHECK_ITEM_DONE;
		}
		
		String nonce = String.valueOf(System.currentTimeMillis());
		String sign = HttpUtil.generateDynamicToken(token, nonce, meachineCode);
		SpecialCheckItemDoneParameter parameter = new SpecialCheckItemDoneParameter();
		parameter.setId(id);
		parameter.setToken(token);
		parameter.setNonce(nonce);
		parameter.setSign(sign);
		
		List<ResultItem> items = new ArrayList<>();
		if (results != null && results.size() > 0){
			try{
				for (SpecialItemCheckResult cr : results){
					ResultItem item = new ResultItem();
					item.setName(URLEncoder.encode(cr.getName(), "utf-8"));
					if (cr.getResult() == 1){
						item.setCheckresult(URLEncoder.encode("√", "utf-8"));
					} else {
						item.setCheckresult(URLEncoder.encode("×", "utf-8"));
					}
					items.add(item);
				}
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		parameter.setItems(items);
		parameter.setImages(images);
		
		Gson gson = new Gson();
		String requestJson = gson.toJson(parameter);
		
		try{
			
			String responseJson = HttpUtil.PostJosn(systemUrl, requestJson);
			if (!StringUtil.IsNullOrEmpty(responseJson)){
				result = gson.fromJson(responseJson, Result.class);
			} else {
				result.setSuccess(false);
				result.setMessage(getMessage("msg_no_response_data"));
			}
		} catch (IOException ex){
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}
		
		return result;
	}

}
