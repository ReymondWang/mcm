package com.purplelight.mcm.api;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.purplelight.mcm.api.bean.UpgradeInfo;
import com.purplelight.mcm.api.parameter.UpgradeParameter;
import com.purplelight.mcm.api.result.UpgradeResult;
import com.purplelight.mcm.entity.AppFileManage;
import com.purplelight.mcm.service.IAppFileManageService;
import com.purplelight.mcm.util.FileHelper;
import com.purplelight.mcm.util.McmConstant.APPLICATION_VERSION;
import com.purplelight.mcm.util.StringUtil;

public class UpgradeApi extends BaseApi {
	private static final long serialVersionUID = 2533917307703501960L;

	@Resource
	private IAppFileManageService appFileManageService;
	
	@Override
	public String execute() throws Exception{
		UpgradeResult result = new UpgradeResult();
		Gson gson = new Gson();
		String json = getJson();
		
		if (!StringUtil.IsNullOrEmpty(json)){
			UpgradeParameter parameter = gson.fromJson(json, UpgradeParameter.class);
			if (checkToken(parameter.getToken())){
				String appName = parameter.getAppName();
				String osType = parameter.getOsType();
				AppFileManage file = appFileManageService.getUpgrateInfo(appName, osType);
				
				UpgradeInfo info = new UpgradeInfo();
				info.setAppName(file.getAppName());
				info.setVersionCode(file.getVersionCode());
				info.setVersionName(file.getVersionName());
				info.setDescription(file.getVersionDescription());
				if (APPLICATION_VERSION.ANDROID.equals(osType)){
					info.setDownLoadUrl(FileHelper.APK_FILE_PATH + "/" + file.getFileName());
				} else {
					info.setDownLoadUrl(file.getFileName());
				}
				result.setSuccess(true);
				result.setUpgradeInfo(info);
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
