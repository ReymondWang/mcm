package com.purplelight.mcm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.bean.Passport;
import com.purplelight.mcm.api.bean.PassportFile;
import com.purplelight.mcm.api.result.PassportFileResult;
import com.purplelight.mcm.api.result.PassportResult;
import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.entity.UserBindSystem;
import com.purplelight.mcm.outtersystem.bean.Licence;
import com.purplelight.mcm.outtersystem.bean.LicenceFile;
import com.purplelight.mcm.outtersystem.result.LicenceFileResult;
import com.purplelight.mcm.outtersystem.result.LicenceResult;
import com.purplelight.mcm.service.IOutterSystemService;
import com.purplelight.mcm.service.IPassportService;
import com.purplelight.mcm.service.IUserBindSystemService;
import com.purplelight.mcm.util.ConvertUtil;
import com.purplelight.mcm.util.HttpUtil;
import com.purplelight.mcm.util.StringUtil;

public class RedStarOmPassportServiceImpl extends BaseServiceImpl implements IPassportService {
	private static final String GET_LICENCES = "app/GetLicences";
	private static final String GET_LICENCE_FILES = "app/GetAttachments";
	private static final String ATTACH_URL = "attachment/download?fileid=";
	private static final String MASTER_TYPE = "证照";
	
	@Resource
	private IOutterSystemService outterSystemService;
	
	@Resource
	private IUserBindSystemService userBindSystemService;
	
	@Override
	public PassportResult getPassportList(int userId, int systemId, String projectId, String category, int pageNo,
			int pageSize) {
		PassportResult result = new PassportResult();
		
		OutterSystem system = outterSystemService.getOutterSystemById(systemId);
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
			if (bindSystem != null){
				result = getLicences(systemId
						, system.getSystemUrl()
						, bindSystem.getToken()
						, bindSystem.getMeachineCode()
						, projectId
						, category
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
	public PassportFileResult getPassportFiles(int userId, int systemId, int itemId) {
		PassportFileResult result = new PassportFileResult();
		
		OutterSystem system = outterSystemService.getOutterSystemById(systemId);
		if (system != null){
			UserBindSystem bindSystem = userBindSystemService.getByUserIdAndSystemId(userId, systemId);
			if (bindSystem != null){
				result = getAttachments(system.getSystemUrl()
						, bindSystem.getToken()
						, bindSystem.getMeachineCode()
						, itemId);
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
	
	private PassportResult getLicences(int systemId
			, String systemUrl
			, String token
			, String meachineCode
			, String projectId
			, String catalog
			, int page
			, int numOfPage){
		PassportResult result = new PassportResult();
		
		String nonce = String.valueOf(System.currentTimeMillis());
		String sign = HttpUtil.generateDynamicToken(token, nonce, meachineCode);
		HashMap<String, String> map = new HashMap<>();
		map.put("token", token);
		map.put("nonce", nonce);
		map.put("sign", sign);
		map.put("projectid", projectId);
		map.put("catalog", catalog);
		map.put("page", String.valueOf(page));
		map.put("numOfPage", String.valueOf(numOfPage));
		
		String responseJson = HttpUtil.GetDataFromNet(systemUrl + GET_LICENCES, map, HttpUtil.POST);
		if (StringUtil.IsNullOrEmpty(responseJson)){
			result.setSuccess(false);
			result.setMessage(getMessage("msg_no_response_data"));
		} else {
			LicenceResult bsResult = new Gson().fromJson(responseJson, LicenceResult.class);
			result.setSuccess(bsResult.isSuccess());
			result.setMessage(bsResult.getMessage());
			if (bsResult.isSuccess() && bsResult.getObj() != null && bsResult.getObj().size() > 0){
				List<Passport> passportList = new ArrayList<>();
				for (Licence lic : bsResult.getObj()){
					Passport passport = new Passport();
					passport.setId(lic.getId());
					passport.setSystemId(systemId);
					passport.setCategory(lic.getCatalog());
					passport.setName(lic.getName());
					passport.setProjectName(lic.getProjectName());
					passport.setResourceName(lic.getResourceNames());
					passport.setLicenseDate(ConvertUtil.dateFormat(lic.getLicenceDate()));
					passport.setExpireDate(ConvertUtil.dateFormat(lic.getLicenceEndDate()));
					passport.setRemark(lic.getRemark());
					
					passportList.add(passport);
				}
				result.setPassports(passportList);
			}
		}
		
		return result;
	}
	
	private PassportFileResult getAttachments(String systemUrl
			, String token
			, String meachineCode
			, int itemId){
		PassportFileResult result = new PassportFileResult();
		
		String nonce = String.valueOf(System.currentTimeMillis());
		String sign = HttpUtil.generateDynamicToken(token, nonce, meachineCode);
		HashMap<String, String> map = new HashMap<>();
		map.put("token", token);
		map.put("nonce", nonce);
		map.put("sign", sign);
		map.put("mastertype", MASTER_TYPE);
		map.put("masterid", String.valueOf(itemId));
		map.put("numOfPage", "0");
		
		String responseJson = HttpUtil.GetDataFromNet(systemUrl + GET_LICENCE_FILES, map, HttpUtil.POST);
		if (StringUtil.IsNullOrEmpty(responseJson)){
			result.setSuccess(false);
			result.setMessage(getMessage("msg_no_response_data"));
		} else {
			LicenceFileResult bsResult = new Gson().fromJson(responseJson, LicenceFileResult.class);
			result.setSuccess(bsResult.isSuccess());
			result.setMessage(bsResult.getMessage());
			if (bsResult.isSuccess() && bsResult.getObj() != null && bsResult.getObj().size() > 0){
				List<PassportFile> fileList = new ArrayList<>();
				for (LicenceFile lic : bsResult.getObj()){
					PassportFile file = new PassportFile();
					file.setFileName(lic.getName());
					file.setFilePath(systemUrl + ATTACH_URL + lic.getId());
					
					fileList.add(file);
				}
				result.setFileList(fileList);
			}
		}
		
		return result;
	}
	
}
