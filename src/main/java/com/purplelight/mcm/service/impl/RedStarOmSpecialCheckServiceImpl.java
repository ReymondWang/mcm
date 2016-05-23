package com.purplelight.mcm.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.bean.SpecialItem;
import com.purplelight.mcm.api.bean.SpecialItemCheckResult;
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

public class RedStarOmSpecialCheckServiceImpl implements ISpecialCheckService {
	private static final String GET_SPECIAL_CHECK_ITEMS = "app/GetSpecialCheckItems";
	private static final String SPECIAL_CHECK_ITEM_DONE = "app/SpecialCheckItemDone";
	
	@Resource
	private IOutterSystemService outterSystemService;
	
	@Resource
	private IUserBindSystemService userBindSystemService;
	
	@Override
	public SpecialItemResult getSpecialCheckItems(int userId, int systemId, int reportId, boolean onlyUnChecked,
			int pageNo, int pageSize) {
		SpecialItemResult result = new SpecialItemResult();
		
		OutterSystem system = outterSystemService.getOutterSystemById(systemId);
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
			if (bindSystem != null){
				result = GetSpecialCheckItems(systemId, system.getSystemUrl(), bindSystem.getToken(), reportId,
						onlyUnChecked, pageNo, pageSize);
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
	public Result submitSpecialCheckItem(int userId, int systemId, int itemId, List<SpecialItemCheckResult> results,
			List<String> images) {
		Result result = new Result();
		
		OutterSystem system = outterSystemService.getOutterSystemById(systemId);
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
			if (bindSystem != null){
				result = SpecialCheckItemDone(system.getSystemUrl(), bindSystem.getToken(), itemId, results, images);
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
	
	private SpecialItemResult GetSpecialCheckItems(int systemId, String systemUrl, 
			String token, int checkId, boolean onlyUnchecked, int page, int numOfPage){
		SpecialItemResult result = new SpecialItemResult();
		
		SpecialCheckItemParameter parameter = new SpecialCheckItemParameter();
		parameter.setCheckId(checkId);
		parameter.setToken(token);
		parameter.setOnlyUnchecked(onlyUnchecked);
		parameter.setPage(page);
		parameter.setNumOfPage(numOfPage);
		
		Gson gson = new Gson();
		
		String requestJson = gson.toJson(parameter);
		try{
			String responseJson = HttpUtil.PostJosn(systemUrl + GET_SPECIAL_CHECK_ITEMS, requestJson);
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
				result.setMessage("业务系统没有返回数据");
			}
		} catch (IOException ex){
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}
		
		return result;
	}
	
	private Result SpecialCheckItemDone(String systemUrl, String token, 
			int id, List<SpecialItemCheckResult> results, List<String> images){
		Result result = new Result();
		
		SpecialCheckItemDoneParameter parameter = new SpecialCheckItemDoneParameter();
		parameter.setId(id);
		parameter.setToken(token);
		
		List<ResultItem> items = new ArrayList<>();
		if (results != null && results.size() > 0){
			for (SpecialItemCheckResult cr : results){
				ResultItem item = new ResultItem();
				item.setName(cr.getName());
				if (cr.getResult() == 1){
					item.setCheckresult("√");
				} else {
					item.setCheckresult("×");
				}
				items.add(item);
			}
		}
		parameter.setItems(items);
		
		parameter.setImages(images);
		
		Gson gson = new Gson();
		String requestJson = gson.toJson(parameter);
		
		try{
			String responseJson = HttpUtil.PostJosn(systemUrl + SPECIAL_CHECK_ITEM_DONE, requestJson);
			if (!StringUtil.IsNullOrEmpty(responseJson)){
				result = gson.fromJson(responseJson, Result.class);
			} else {
				result.setSuccess(false);
				result.setMessage("业务系统没有返回数据");
			}
		} catch (IOException ex){
			result.setSuccess(false);
			result.setMessage(ex.getMessage());
		}
		
		return result;
	}

}
