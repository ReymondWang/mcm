package com.purplelight.mcm.service.impl;

import java.util.Locale;

import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class BaseServiceImpl {
	
	/**
	 * 根据消息编号取得国际化文件中的消息
	 * @param msgCode
	 * @return
	 */
	public String getMessage(String msgCode){
		return LocalizedTextUtil.findDefaultText(msgCode, Locale.getDefault());
	}
}
