package com.purplelight.mcm.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.purplelight.mcm.entity.DictionaryItem;
import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;
import com.purplelight.mcm.service.IDictionaryService;
import com.purplelight.mcm.service.IOutterSystemService;
import com.purplelight.mcm.util.StringUtil;

public class OutterSystemAction extends BaseAction {
	private static final long serialVersionUID = 5335748209427804776L;
	
	private static final String OUTTER_SYSTEM_TYPE = "D000001";
	
	@Resource
	private IDictionaryService dictService;
	
	@Resource
	private IOutterSystemService outterSystemService;
	
	private OutterSystem outterSystem = new OutterSystem();
	
	private PageInfo<OutterSystem> pageInfo;
	
	private List<DictionaryItem> outterSystemTypes;
	
	@Override
	public String execute() throws Exception{
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			if (!StringUtil.IsNullOrEmpty(request.getParameter("id"))){
				int id = Integer.valueOf(request.getParameter("id")).intValue();
				outterSystem.setId(id);
				outterSystem = outterSystemService.getOutterSystem(outterSystem);
			}
			
			// 取得外部系统类型的数据
			setOutterSystemTypes(dictService.getDictItemsByDictNameCodeWithBlank(OUTTER_SYSTEM_TYPE));
			
			// 去的已保存的外部系统的数据
			OutterSystem os = new OutterSystem();
			setPageInfo(outterSystemService.query(new Strategy(os, "os"), 1));
			
			return SUCCESS;
		} catch (Exception ex){
			setMessageType(ERROR_MSG);
			setMessage(ex.getMessage());
			return ERROR;
		}
	}
	
	public String save() throws Exception{
		SystemUser loginedUser = (SystemUser)getSession().get("user");
		if (loginedUser != null){
			try{
				if (outterSystem.getId() != 0){
					outterSystemService.updateOutterSystem(outterSystem, loginedUser);
				} else {
					outterSystemService.addOutterSystem(outterSystem, loginedUser);
				}
				
				// 取得外部系统类型的数据
				setOutterSystemTypes(dictService.getDictItemsByDictNameCodeWithBlank(OUTTER_SYSTEM_TYPE));
				
				// 去的已保存的外部系统的数据
				OutterSystem os = new OutterSystem();
				setPageInfo(outterSystemService.query(new Strategy(os, "os"), 1));
				
				setMessageType(SUCCESS_MSG);
				setMessageFromResource("msg_save_success");
				return SUCCESS;
			} catch (Exception ex){
				setMessageType(ERROR_MSG);
				setMessage(ex.getMessage());
				return ERROR;
			}
		} else {
			return "timeout";
		}
	}
	
	public String delete() throws Exception{
		try{
			outterSystemService.deleteOutterSystem(outterSystem);
			
			// 取得外部系统类型的数据
			setOutterSystemTypes(dictService.getDictItemsByDictNameCodeWithBlank(OUTTER_SYSTEM_TYPE));
			
			// 去的已保存的外部系统的数据
			OutterSystem os = new OutterSystem();
			setPageInfo(outterSystemService.query(new Strategy(os, "os"), 1));
			
			if (pageInfo.getResult().size() > 0){
				outterSystem = pageInfo.getResult().get(0);
			} else {
				outterSystem = new OutterSystem();
			}
			
			setMessageType(SUCCESS_MSG);
			setMessageFromResource("msg_del_success");
			return SUCCESS;
		} catch (Exception ex){
			setMessageType(ERROR_MSG);
			setMessage(ex.getMessage());
			return ERROR;
		}
	}

	public OutterSystem getOutterSystem() {
		return outterSystem;
	}

	public void setOutterSystem(OutterSystem outterSystem) {
		this.outterSystem = outterSystem;
	}

	public PageInfo<OutterSystem> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<OutterSystem> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<DictionaryItem> getOutterSystemTypes() {
		return outterSystemTypes;
	}

	public void setOutterSystemTypes(List<DictionaryItem> outterSystemTypes) {
		this.outterSystemTypes = outterSystemTypes;
	}
	
}
