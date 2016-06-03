package com.purplelight.mcm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.bean.ProjectInfo;
import com.purplelight.mcm.api.result.ProjectResult;
import com.purplelight.mcm.api.result.TokenResult;
import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.entity.UserBindSystem;
import com.purplelight.mcm.outtersystem.bean.Project;
import com.purplelight.mcm.outtersystem.result.RsProjectResult;
import com.purplelight.mcm.service.ICommonService;
import com.purplelight.mcm.service.IOutterSystemService;
import com.purplelight.mcm.service.IUserBindSystemService;
import com.purplelight.mcm.util.HttpUtil;
import com.purplelight.mcm.util.StringUtil;

public class RedStarOmCommonServiceImpl extends BaseServiceImpl implements ICommonService {

	private static final String GET_PROJECTS = "app/getprojects";
	
	@Resource
	private IOutterSystemService outterSystemService;
	
	@Resource
	private IUserBindSystemService userBindSystemService;
	
	@Override
	public ProjectResult getProjectList(int userId, int systemId) {
		ProjectResult result = new ProjectResult();
		
		OutterSystem system = outterSystemService.getOutterSystemById(systemId);
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
			if (bindSystem != null){
				result = getProjects(system.getSystemUrl(), bindSystem.getToken(), bindSystem.getMeachineCode());
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
	public TokenResult getToken(int userId, int systemId) {
		TokenResult result = new TokenResult();
		
		UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
		if (bindSystem != null){
			result.setSuccess(true);
			result.setToken(bindSystem.getToken());
		} else {
			result.setSuccess(false);
			result.setMessage(getMessage("msg_no_system_binded"));
		}
		
		return result;
	}
	
	private ProjectResult getProjects(String systemUrl, String token, String meachineCode){
		ProjectResult result = new ProjectResult();
		
		String nonce = String.valueOf(System.currentTimeMillis());
		String sign = HttpUtil.generateDynamicToken(token, nonce, meachineCode);
		HashMap<String, String> map = new HashMap<>();
		map.put("token", token);
		map.put("nonce", nonce);
		map.put("sign", sign);
		
		String responseJson = HttpUtil.GetDataFromNet(systemUrl + GET_PROJECTS, map, HttpUtil.POST);
		if (!StringUtil.IsNullOrEmpty(responseJson)){
			RsProjectResult rsResult = new Gson().fromJson(responseJson, RsProjectResult.class);
			result.setSuccess(rsResult.isSuccess());
			result.setMessage(rsResult.getMessage());
			if (result.isSuccess()){
				List<ProjectInfo> list = new ArrayList<>();
				for (Project p : rsResult.getObj()){
					ProjectInfo item = new ProjectInfo();
					item.setProjectId(p.getId());
					item.setProjectName(p.getName());
					list.add(item);
				}
				result.setProjects(list);
			}
			
		} else {
			result.setSuccess(false);
			result.setMessage(getMessage("msg_no_response_data"));
		}
		
		return result;
	}

}
