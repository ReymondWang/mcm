package com.purplelight.mcm.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.entity.DictionaryItem;
import com.purplelight.mcm.entity.DictionaryName;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.service.IDictionaryService;
import com.purplelight.mcm.util.McmConstant;
import com.purplelight.mcm.util.StringUtil;

public class DictionaryAction extends BaseAction {
	private static final long serialVersionUID = -1643668534554429090L;

	private List<DictionaryName> dictNames = new ArrayList<>();
	
	private List<DictionaryItem> dictItems = new ArrayList<>();
	
	private String dictNameCode;
	
	private String dictItemCode;
	
	private DictionaryItem dictItem;
	
	@Resource
	private IDictionaryService dictService;
	
	@Override
	public String execute() throws Exception{
		String code = getRequest().getParameter("dictNameCode");
		dictNames = dictService.getAllDictNames();
		if (StringUtil.IsNullOrEmpty(code) && dictNames.size() > 0){
			dictNameCode = dictNames.get(0).getDictNameCode();
		}
		if (!StringUtil.IsNullOrEmpty(dictNameCode)){
			dictItems = dictService.getDictItemsByDictNameCode(dictNameCode);
		}
		
		return SUCCESS;
	}
	
	public String info() throws Exception{
		dictNameCode = getRequest().getParameter("dictNameCode");
		dictItemCode = getRequest().getParameter("dictItemCode");
		dictNames = dictService.getAllDictNames();
		if (!StringUtil.IsNullOrEmpty(dictItemCode)){
			dictItem = dictService.getDictItemByCode(dictItemCode);
		}
		
		return SUCCESS;
	}
	
	public String save() throws Exception{
		dictNames = dictService.getAllDictNames();
		SystemUser loginedUser = (SystemUser)getSession().get(McmConstant.USER_SESSION);
		try{
			if (dictService.hasDictItemCode(dictItem.getDictItemCode())){
				setMessageType(BaseAction.ERROR_MSG);
				setMessageFromResource("msg_dict_item_code_unique");
				return ERROR;
			}
			
			dictItem.setDictNameCode(dictNameCode);
			if (dictItem.getId() != 0){
				dictService.updateDictItem(dictItem, loginedUser);
			} else {
				dictService.addDictItem(dictItem, loginedUser);
			}
			
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
		dictNames = dictService.getAllDictNames();
		try{
			dictService.deleteDictItem(dictItem);
			dictItems = dictService.getDictItemsByDictNameCode(dictNameCode);
			if (dictItems != null && dictItems.size() > 0){
				dictItem = dictItems.get(0);
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

	public List<DictionaryName> getDictNames() {
		return dictNames;
	}

	public void setDictNames(List<DictionaryName> dictNames) {
		this.dictNames = dictNames;
	}

	public List<DictionaryItem> getDictItems() {
		return dictItems;
	}

	public void setDictItems(List<DictionaryItem> dictItems) {
		this.dictItems = dictItems;
	}

	public String getDictNameCode() {
		return dictNameCode;
	}

	public void setDictNameCode(String dictNameCode) {
		this.dictNameCode = dictNameCode;
	}

	public String getDictItemCode() {
		return dictItemCode;
	}

	public void setDictItemCode(String dictItemCode) {
		this.dictItemCode = dictItemCode;
	}

	public DictionaryItem getDictItem() {
		return dictItem;
	}

	public void setDictItem(DictionaryItem dictItem) {
		this.dictItem = dictItem;
	}

}
