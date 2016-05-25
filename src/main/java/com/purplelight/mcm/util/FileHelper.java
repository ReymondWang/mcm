package com.purplelight.mcm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
}
