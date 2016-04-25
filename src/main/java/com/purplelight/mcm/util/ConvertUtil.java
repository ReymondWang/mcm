package com.purplelight.mcm.util;

import java.sql.Timestamp;

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
	
	public static byte split_file_id(String file_id, String[] results, String splitor) {
		int pos = file_id.indexOf(splitor);
		if ((pos <= 0) || (pos == file_id.length() - 1)) {
			return ProtoCommon.ERR_NO_EINVAL;
		}

		results[0] = file_id.substring(0, pos); // group name
		results[1] = file_id.substring(pos + 1); // file name
		return 0;
	}
}