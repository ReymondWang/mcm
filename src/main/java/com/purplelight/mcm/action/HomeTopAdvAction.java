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

public class HomeTopAdvAction extends BaseAction {
	private static final long serialVersionUID = 3564511008451928822L;

	private AppFunction homeAdv = new AppFunction();
	
	private List<AppFunction> homeAdvList;
	
	private List<SpinnerItem> funcType = McmConstant.FunctionType.getItemList();;
	
	private SpinnerItem fragment = McmConstant.Fragment.getItem(McmConstant.Fragment.HOME);
	
	private SpinnerItem part = McmConstant.FragmentPart.getItem(McmConstant.FragmentPart.TOP);
	
	private List<OutterSystem> outterSystems = new ArrayList<OutterSystem>();
	
	private File image;
	
	private String imageFileName;
	
	@Resource
	private IOutterSystemService outterSystemService;
	
	@Resource
	private IAppFunctionService appFuncService;
	
	@Override
	public String execute() throws Exception{
		outterSystems = outterSystemService.getAllWithBlank();
		homeAdvList = appFuncService.getAppFuncByFragmentAndPart(
				Integer.parseInt(fragment.getCode()), Integer.parseInt(part.getCode()));
		
		if (!StringUtil.IsNullOrEmpty(getRequest().getParameter("id"))){
			int id = Integer.parseInt(getRequest().getParameter("id"));
			homeAdv.setId(id);
			homeAdv = appFuncService.getAppFunc(homeAdv);
		}
		
		return SUCCESS;
	}
	
	public String save() throws Exception{
		SystemUser loginedUser = (SystemUser)getSession().get("user");
		if (loginedUser == null){
			return "timeout";
		}
		
		try{
			if (image != null && image.length() > 0){
				if (image.length() > 1024 * 1024 * 5){
					setMessageType(BaseAction.ERROR_MSG);
					setMessageFromResource("msg_file_too_big");
					
					return ERROR;
				}
				
				homeAdv.setTitleImgPath(UploadUtil.upload(image, imageFileName));
			}
			homeAdv.setFragment(Integer.parseInt(fragment.getCode()));
			homeAdv.setPart(Integer.parseInt(part.getCode()));
			if (homeAdv.getId() != 0){
				appFuncService.updateAppFunction(homeAdv, loginedUser);
			} else {
				appFuncService.addAppFunction(homeAdv, loginedUser);
			}
			
			outterSystems = outterSystemService.getAllWithBlank();
			homeAdvList = appFuncService.getAppFuncByFragmentAndPart(
					Integer.parseInt(fragment.getCode()), Integer.parseInt(part.getCode()));
			
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
			appFuncService.deleteAppFunction(homeAdv);
			
			outterSystems = outterSystemService.getAllWithBlank();
			homeAdvList = appFuncService.getAppFuncByFragmentAndPart(
					Integer.parseInt(fragment.getCode()), Integer.parseInt(part.getCode()));
			
			if (homeAdvList != null && homeAdvList.size() > 0){
				homeAdv = homeAdvList.get(0);
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

	public AppFunction getHomeAdv() {
		return homeAdv;
	}

	public void setHomeAdv(AppFunction homeAdv) {
		this.homeAdv = homeAdv;
	}

	public List<AppFunction> getHomeAdvList() {
		return homeAdvList;
	}

	public void setHomeAdvList(List<AppFunction> homeAdvList) {
		this.homeAdvList = homeAdvList;
	}

	public List<SpinnerItem> getFuncType() {
		return funcType;
	}

	public void setFuncType(List<SpinnerItem> funcType) {
		this.funcType = funcType;
	}

	public SpinnerItem getFragment() {
		return fragment;
	}

	public void setFragment(SpinnerItem fragment) {
		this.fragment = fragment;
	}

	public SpinnerItem getPart() {
		return part;
	}

	public void setPart(SpinnerItem part) {
		this.part = part;
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
	
}
