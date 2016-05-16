package com.purplelight.mcm.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.entity.AppFunction;
import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.IAppFunctionService;
import com.purplelight.mcm.service.IOutterSystemService;
import com.purplelight.mcm.util.McmConstant;
import com.purplelight.mcm.util.SpinnerItem;
import com.purplelight.mcm.util.StringUtil;
import com.purplelight.mcm.util.UploadUtil;

public class AppFunctionAction extends BaseAction {
	private static final long serialVersionUID = 3564511008451928822L;

	/**
	 * 当前页面要显示的功能实体
	 */
	private AppFunction appFunc = new AppFunction();
	
	/**
	 * 当前页面快速导航的所有的功能实体
	 */
	private List<AppFunction> appFuncList = new ArrayList<>();
	
	/**
	 * 功能类型
	 */
	private List<SpinnerItem> funcType = McmConstant.FunctionType.getItemList();;
	
	/**
	 * 定义当前功能属于APP的那个Fragment
	 */
	private int fragment;
	
	/**
	 * 定义当前功能数据页面的那一部分
	 */
	private int part;
	
	/**
	 * 定义当前页面的Fragment显示的实体
	 */
	private SpinnerItem fragmentItem;
	
	/**
	 * 定义当前页面的Part显示的实体
	 */
	private SpinnerItem partItem;
	
	/**
	 * 选择的外部系统编号
	 */
	private int outterSystemId;
	
	/**
	 * 可以选择的外部系统的列表
	 */
	private List<OutterSystem> outterSystems = new ArrayList<OutterSystem>();
	
	/**
	 * 上传文件
	 */
	private File image;
	
	/**
	 * 上传文件的文件名，由于Struts2上传的文件会生成临时文件，因此直接从file中获取的文件名跟上传时选择的不一致，因此需要这个参数。
	 */
	private String imageFileName;
	
	@Resource
	private IOutterSystemService outterSystemService;
	
	@Resource
	private IAppFunctionService appFuncService;
	
	@Override
	public String execute() throws Exception{
		// 取得Fragment的显示对象
		if (fragment == 0){
			fragment = Integer.parseInt(getRequest().getParameter("fragment"));
		}
		fragmentItem = McmConstant.Fragment.getItem(fragment);
		
		// 取得Part的显示对象
		if (part == 0){
			part = Integer.parseInt(getRequest().getParameter("part"));
		}
		partItem = McmConstant.FragmentPart.getItem(part);
		
		outterSystems = outterSystemService.getAllWithBlank();
		appFuncList = appFuncService.getAppFuncByFragmentAndPart(fragment, part);
		
		if (!StringUtil.IsNullOrEmpty(getRequest().getParameter("id"))){
			int id = Integer.parseInt(getRequest().getParameter("id"));
			appFunc.setId(id);
			appFunc = appFuncService.getAppFunc(appFunc);
		}
		
		return SUCCESS;
	}
	
	public String save() throws Exception{
		SystemUser loginedUser = (SystemUser)getSession().get(McmConstant.USER_SESSION);
		try{
			outterSystems = outterSystemService.getAllWithBlank();
			
			if (image != null && image.length() > 0){
				if (image.length() > 1024 * 1024 * 5){
					setMessageType(BaseAction.ERROR_MSG);
					setMessageFromResource("msg_file_too_big");
					
					return ERROR;
				}
				
				appFunc.setTitleImgPath(UploadUtil.upload(image, imageFileName));
			}
			appFunc.setFragment(fragment);
			appFunc.setPart(part);
			for (OutterSystem item : outterSystems){
				if (item.getId() == outterSystemId){
					appFunc.setOutterSystem(item);
					break;
				}
			}
			if (appFunc.getId() != 0){
				appFuncService.updateAppFunction(appFunc, loginedUser);
			} else {
				appFuncService.addAppFunction(appFunc, loginedUser);
			}
			
			appFuncList = appFuncService.getAppFuncByFragmentAndPart(fragment, part);
			
			setMessageType(BaseAction.SUCCESS_MSG);
			setMessageFromResource("msg_save_success");
			
			return SUCCESS;
		} catch (Exception ex){
			setMessageType(BaseAction.ERROR_MSG);
			setMessage(ex.getMessage());
			
			return ERROR;
		}
	}
	
	public String delete() throws Exception{
		try{
			appFuncService.deleteAppFunction(appFunc);
			
			outterSystems = outterSystemService.getAllWithBlank();
			appFuncList = appFuncService.getAppFuncByFragmentAndPart(fragment, part);
			
			if (appFuncList != null && appFuncList.size() > 0){
				appFunc = appFuncList.get(0);
			}
			
			setMessageType(BaseAction.SUCCESS_MSG);
			setMessageFromResource("msg_del_success");
			
			return SUCCESS;
		} catch (Exception ex){
			setMessageType(BaseAction.ERROR_MSG);
			setMessage(ex.getMessage());
			return ERROR;
		}
	}

	public AppFunction getAppFunc() {
		return appFunc;
	}

	public void setAppFunc(AppFunction appFunc) {
		this.appFunc = appFunc;
	}

	public List<AppFunction> getAppFuncList() {
		return appFuncList;
	}

	public void setAppFuncList(List<AppFunction> appFuncList) {
		this.appFuncList = appFuncList;
	}

	public List<SpinnerItem> getFuncType() {
		return funcType;
	}

	public void setFuncType(List<SpinnerItem> funcType) {
		this.funcType = funcType;
	}

	public int getFragment() {
		return fragment;
	}

	public void setFragment(int fragment) {
		this.fragment = fragment;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}

	public SpinnerItem getFragmentItem() {
		return fragmentItem;
	}

	public void setFragmentItem(SpinnerItem fragmentItem) {
		this.fragmentItem = fragmentItem;
	}

	public SpinnerItem getPartItem() {
		return partItem;
	}

	public void setPartItem(SpinnerItem partItem) {
		this.partItem = partItem;
	}

	public List<OutterSystem> getOutterSystems() {
		return outterSystems;
	}

	public void setOutterSystems(List<OutterSystem> outterSystems) {
		this.outterSystems = outterSystems;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public int getOutterSystemId() {
		return outterSystemId;
	}

	public void setOutterSystemId(int outterSystemId) {
		this.outterSystemId = outterSystemId;
	}
}
