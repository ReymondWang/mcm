package com.purplelight.mcm.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.purplelight.mcm.api.result.WebBanner;
import com.purplelight.mcm.entity.AppFunction;
import com.purplelight.mcm.fastdfs.ProtoCommon;

public class ConvertUtil {
	public static boolean isInteger(Object obj){
		if (obj == null){
			return false;
		}
		return obj.getClass().getName().equals(Integer.class.getName()) || obj.getClass().getName().equals("int");
	}
	
	public static boolean isFloat(Object obj){
		if (obj == null){
			return false;
		}
		return obj.getClass().getName().equals(Float.class.getName()) || obj.getClass().getName().equals("float");
	}
	
	public static boolean isDouble(Object obj){
		if (obj == null){
			return false;
		}
		return obj.getClass().getName().equals(Double.class.getName()) || obj.getClass().getName().equals("double");
	}
	
	public static boolean isString(Object obj){
		if (obj == null){
			return false;
		}
		return obj.getClass().getName().equals(String.class.getName());
	}
	
	public static boolean isTimeStamp(Object obj){
		if (obj == null){
			return false;
		}
		return obj.getClass().getName().equals(Timestamp.class.getName());
	}
	
	public static String ConvertToDateStr(Timestamp time){
		String dateStr = "";
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		String day = String.valueOf(calendar.get(Calendar.DATE));
		
		dateStr = year + "-" + month + "-" + day;
		
		return dateStr;
	}
	
	public static String ConvertToDateTimeStr(Timestamp time){
		String dateTimeStr = "";
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		String day = String.valueOf(calendar.get(Calendar.DATE));
		String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String minute = String.valueOf(calendar.get(Calendar.MINUTE));
		String second = String.valueOf(calendar.get(Calendar.SECOND));
		
		dateTimeStr = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
		
		return dateTimeStr;
	}
	
	public static Timestamp ConvertToTimestamp(String str){
		String[] arr = str.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]) - 1, Integer.parseInt(arr[2]));
		
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	public static byte split_file_id(String file_id, String[] results, String splitor) {
		int pos = file_id.indexOf(splitor);
		if ((pos <= 0) || (pos == file_id.length() - 1)) {
			return ProtoCommon.ERR_NO_EINVAL;
		}

		results[0] = file_id.substring(0, pos); // group name
		results[1] = file_id.substring(pos + 1); // file name
		return 0;
	}
	
	public static WebBanner toWebBanner(AppFunction function){
		WebBanner banner = new WebBanner();
		if (function != null){
			banner.setId(String.valueOf(function.getId()));
			banner.setImage(function.getTitleImgPath());
			banner.setType(String.valueOf(function.getFunctionType()));
			banner.setLabel(function.getTitle());
			banner.setOutterSystem(function.getOutterSystem());
			banner.setUrl(function.getContentUrl());
			banner.setCallMethod(function.getCallMethod());
		}
		
		return banner;
	}
	
	public static List<WebBanner> toWebBannerList(List<AppFunction> functions){
		List<WebBanner> retList = new ArrayList<>();
		if (functions != null && functions.size() > 0){
			for (AppFunction func : functions){
				retList.add(toWebBanner(func));
			}
		}
		
		return retList;
	}
}
