package com.purplelight.mcm.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.entity.AppFileManage;
import com.purplelight.mcm.entity.DictionaryItem;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.service.IAppFileManageService;
import com.purplelight.mcm.service.IDictionaryService;
import com.purplelight.mcm.util.FileHelper;
import com.purplelight.mcm.util.McmConstant;
import com.purplelight.mcm.util.StringUtil;

public class AppFileManageAction extends BaseAction {
	private static final long serialVersionUID = 1714539843146847509L;

	private static final String OS_TYPE = "D000003";
	
	private List<DictionaryItem> osTypes = new ArrayList<>();
	
	private PageInfo<AppFileManage> pageInfo = new PageInfo<>();
	
	private String curOsType;
	
	private AppFileManage entity;
	
	private String curOsTypeName;
	
	private File appFile;
	
	private String selFileName;
	
	private int currentPageNo;
	
	@Resource
	private IDictionaryService dictService;
	
	@Resource
	private IAppFileManageService appFileManageService;
	
	@Override
	public String execute() throws Exception{
		curOsType = getRequest().getParameter("osType");
		setOsTypeInfo();
		
		int pageNo = 1;
		if (currentPageNo != 0){
			pageNo = currentPageNo;
		}
		pageInfo = appFileManageService.getByOsType(curOsType, pageNo);
		
		return SUCCESS;
	}

	public String info() throws Exception{
		curOsType = getRequest().getParameter("osType");
		String id = getRequest().getParameter("id");
		setOsTypeInfo();
		if (!StringUtil.IsNullOrEmpty(id)){
			entity = appFileManageService.getById(Integer.parseInt(id));
		}
		
		return SUCCESS;
	}
	
	public String save() throws Exception{
		setOsTypeInfo();
		
		if (!StringUtil.IsNullOrEmpty(selFileName)){
			entity.setFileName(FileHelper.generateApkFileName(
					entity.getAppName(), entity.getVersionName(), selFileName));
			boolean success = FileHelper.saveAPK(appFile, entity.getFileName());
			if (!success){
				setMessageType(BaseAction.ERROR_MSG);
				setMessageFromResource("msg_file_upload_failed");
				return ERROR;
			}
		}
		entity.setAppType(curOsType);
		SystemUser loginedUser = (SystemUser)getSession().get(McmConstant.USER_SESSION);
		appFileManageService.saveOrUpdate(entity, loginedUser);
		
		setMessageType(BaseAction.SUCCESS_MSG);
		setMessageFromResource("msg_save_success");
		
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		setOsTypeInfo();
		
		appFileManageService.delete(entity);
		
		// 取得当前分类下的下一条数据
		int pageNo = 1;
		if (currentPageNo != 0){
			pageNo = currentPageNo;
		}
		pageInfo = appFileManageService.getByOsType(curOsType, pageNo);
		if (pageInfo != null && pageInfo.getResult().size() > 0){
			entity = pageInfo.getResult().get(0);
		}
		
		setMessageType(BaseAction.SUCCESS_MSG);
		setMessageFromResource("msg_del_success");
		return SUCCESS;
	}
	
	private void setOsTypeInfo(){
		osTypes = dictService.getDictItemsByDictNameCode(OS_TYPE);
		if (StringUtil.IsNullOrEmpty(curOsType) && osTypes != null && osTypes.size() > 0){
			curOsType = osTypes.get(0).getDictItemCode();
			curOsTypeName = osTypes.get(0).getDictItemValue();
		} else {
			DictionaryItem dictItem = dictService.getDictItemByCode(curOsType);
			if (dictItem != null){
				curOsTypeName = dictItem.getDictItemValue();
			}
		}
	}
	
	public List<DictionaryItem> getOsTypes() {
		return osTypes;
	}

	public void setOsTypes(List<DictionaryItem> osTypes) {
		this.osTypes = osTypes;
	}

	public String getCurOsType() {
		return curOsType;
	}

	public void setCurOsType(String curOsType) {
		this.curOsType = curOsType;
	}

	public PageInfo<AppFileManage> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<AppFileManage> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public AppFileManage getEntity() {
		return entity;
	}

	public void setEntity(AppFileManage entity) {
		this.entity = entity;
	}

	public String getCurOsTypeName() {
		return curOsTypeName;
	}

	public void setCurOsTypeName(String curOsTypeName) {
		this.curOsTypeName = curOsTypeName;
	}

	public File getAppFile() {
		return appFile;
	}

	public void setAppFile(File appFile) {
		this.appFile = appFile;
	}

	public String getSelFileName() {
		return selFileName;
	}

	public void setSelFileName(String selFileName) {
		this.selFileName = selFileName;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	
}
