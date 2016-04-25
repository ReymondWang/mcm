package com.purplelight.mcm.util;

import java.io.File;
import java.io.FileInputStream;

import com.purplelight.mcm.fastdfs.ClientGlobal;
import com.purplelight.mcm.fastdfs.StorageClient;
import com.purplelight.mcm.fastdfs.StorageServer;
import com.purplelight.mcm.fastdfs.TrackerClient;
import com.purplelight.mcm.fastdfs.TrackerServer;
import com.purplelight.mcm.fastdfs.UploadStream;

public class UploadUtil {
	/**
	 * 上传图片到服务器，并返回文件名。
	 * @param image        图片
	 * @param fileName     文件名
	 * @return             fdfs的文件名
	 * @throws Exception   异常信息
	 */
	public static String upload(File image, String fileName) throws Exception{
		String path = (UploadUtil.class.getClassLoader().getResource("").toURI()).getPath();
		ClientGlobal.init(path + "fdfs_client.conf");
		
		String fileExtName = fileName.substring(fileName.lastIndexOf("."));
		long fileLength = image.length();
		
		TrackerClient tracker = new TrackerClient();
		TrackerServer trackerServer = tracker.getConnection();
		StorageServer storageServer = null;
		StorageClient client = new StorageClient(trackerServer, storageServer);
		
		NameValuePair[] metaList = new NameValuePair[3];
		metaList[0] = new NameValuePair("fileName", fileName);
		metaList[1] = new NameValuePair("fileExtName", fileExtName);
		metaList[2] = new NameValuePair("fileLength", String.valueOf(fileLength));
		
		FileInputStream fis = new FileInputStream(image);
		UploadStream upStream = new UploadStream(fis, fileLength);
		String[] result = client.upload_file(null, fileLength, upStream, fileExtName, metaList);
		
		return result[1];
	}
}
