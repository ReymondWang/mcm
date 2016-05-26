package com.purplelight.mcm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class FileHelper {
	private static final String FILE_STORAGE_PATH;
	private static final String APK_FILE_PATH;
	static{
		FILE_STORAGE_PATH = FileHelper.class.getClassLoader().getResource("").getPath().replace("WEB-INF/classes/", "");
		APK_FILE_PATH = "apk";
	}
	
	public static boolean saveAPK(File file, String fileName){
		boolean success = false;
		if (StringUtil.IsNullOrEmpty(fileName)){
			return success;
		}
		File dictionary = new File(FILE_STORAGE_PATH + APK_FILE_PATH);
		boolean hasDic = true;
		if (!dictionary.exists()){
			hasDic = dictionary.mkdirs();
		}
		if (hasDic) {
			File newFile = new File(dictionary, fileName);
			if (newFile.exists()){
				newFile.delete();
			}
			try {
				FileInputStream inStr = new FileInputStream(file);
				FileOutputStream outStr = new FileOutputStream(newFile);
				byte[] buffer = new byte[1024];
				while((inStr.read(buffer)) != -1){
					outStr.write(buffer);
				}
				outStr.flush();
				outStr.close();
				inStr.close();
				success = true;
			} catch (IOException e) {
				e.printStackTrace();
				success = false;
			}
		} else {
			success = false;
		}
		return success;
	}
	
	/**
	 * 根据应用名称和版本号生成上传后的文件名，生成规则为appname_versioncode_时间戳.扩展名。
	 * @param appName         应用名称
	 * @param versionCode     版本号
	 * @return
	 */
	public static String generateApkFileName(String appName, String versionCode, String orgFileName){
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		String day = String.valueOf(calendar.get(Calendar.DATE));
		String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String minute = String.valueOf(calendar.get(Calendar.MINUTE));
		String second = String.valueOf(calendar.get(Calendar.SECOND));
		String timestampStr = year + "_" + month + "_" + day + "_" + hour + "_" + minute + "_" + second;
		int index = orgFileName.indexOf(".");
		String extName = orgFileName.substring(index);
		
		return appName + "_" + versionCode + "_" + timestampStr + extName;
	}
}
