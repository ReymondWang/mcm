package com.purplelight.mcm.action;

import java.io.File;
import java.io.FileInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.fastdfs.ClientGlobal;
import com.purplelight.mcm.fastdfs.StorageClient;
import com.purplelight.mcm.fastdfs.StorageServer;
import com.purplelight.mcm.fastdfs.TrackerClient;
import com.purplelight.mcm.fastdfs.TrackerServer;
import com.purplelight.mcm.fastdfs.UploadStream;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;
import com.purplelight.mcm.service.ISystemUserService;
import com.purplelight.mcm.util.NameValuePair;
import com.purplelight.mcm.util.StringUtil;

public class UserAction extends BaseAction{
	
	private static final long serialVersionUID = 7050534214674134722L;

	private String title;
	
	private SystemUser user = new SystemUser();
	
	private PageInfo<SystemUser> pageInfo = new PageInfo<>();
	
	private String delIds;
	
	private File image;
	
	private String imageFileName;
	
	@Resource
	private ISystemUserService systemUserService;
	
	@Override
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String action = request.getParameter("action");
		try{
			if ("add".equals(action)){
				title = "用户新增";
			}else if ("modify".equals(action) || "info".equals(action)){
				title = "用户修改";
				
				int id = Integer.valueOf(request.getParameter("id")).intValue();
				user.setId(id);
				user = systemUserService.getUser(user);
			}
			
			SystemUser query = new SystemUser();
			Strategy strategy = new Strategy(query, "u");
			setPageInfo(systemUserService.query(strategy, 1));
			
			return action;
		} catch (Exception ex){
			setMessageType(ERROR_MSG);
			setMessage(ex.getMessage());
			System.out.println(ex.getMessage());
		}
		
		return ERROR;
	}
	
	public String list() throws Exception{
		try{
			Strategy strategy = new Strategy(user, "u");
			setPageInfo(systemUserService.query(strategy, 1));
			
			return SUCCESS;
		} catch (Exception ex){
			setMessageType(ERROR_MSG);
			setMessage(ex.getMessage());
			System.out.println(ex.getMessage());
		}
		
		return ERROR;
	}
	
	public String addUser() throws Exception{
		SystemUser loginedUser = (SystemUser)getSession().get("user");
		if (loginedUser != null){
			try{
				if (image != null && image.length() > 0){
					if (image.length() > 1024 * 1024 * 4){
						setMessageType(BaseAction.ERROR_MSG);
						setMessageFromResource("msg_file_too_big");
						
						return ERROR;
					}
					
					String path = (getClass().getClassLoader().getResource("").toURI()).getPath();
					ClientGlobal.init(path + "fdfs_client.conf");
					
					String fileExtName = imageFileName.substring(imageFileName.lastIndexOf("."));
					long fileLength = image.length();
					
					TrackerClient tracker = new TrackerClient();
					TrackerServer trackerServer = tracker.getConnection();
					StorageServer storageServer = null;
					StorageClient client = new StorageClient(trackerServer, storageServer);
					
					NameValuePair[] metaList = new NameValuePair[3];
					metaList[0] = new NameValuePair("fileName", imageFileName);
					metaList[1] = new NameValuePair("fileExtName", fileExtName);
					metaList[2] = new NameValuePair("fileLength", String.valueOf(fileLength));
					
					FileInputStream fis = new FileInputStream(image);
					UploadStream upStream = new UploadStream(fis, fileLength);
					String[] result = client.upload_file(null, fileLength, upStream, fileExtName, metaList);
					
					user.setHeadImgPath(result[1]);
				}
				
				if (user.getId() != 0){
					systemUserService.updateUser(user, loginedUser);
				} else {
					systemUserService.addUser(user, loginedUser);
				}
				
				SystemUser query = new SystemUser();
				Strategy strategy = new Strategy(query, "u");
				setPageInfo(systemUserService.query(strategy, 1));
				
				setMessageType(SUCCESS_MSG);
				setMessageFromResource("msg_save_success");
				
				return SUCCESS;
			} catch (Exception ex){
				setMessageType(ERROR_MSG);
				setMessage(ex.getMessage());
				System.out.println(ex.getMessage());
				return ERROR;
			}
		} else {
			return "timeout";
		}
	}
	
	public String delUser() throws Exception{
		try{
			if (!StringUtil.IsNullOrEmpty(delIds)){
				systemUserService.deleteUserByIdStr(delIds);
				
				setMessageType(SUCCESS_MSG);
				setMessageFromResource("msg_del_success");
			} else {
				setMessageType(ERROR_MSG);
				setMessageFromResource("msg_del_need_id");
			}
			
			SystemUser query = new SystemUser();
			Strategy strategy = new Strategy(query, "u");
			setPageInfo(systemUserService.query(strategy, 1));
			
		} catch (Exception ex){
			setMessageType(ERROR_MSG);
			setMessage(ex.getMessage());
			System.out.println(ex.getMessage());
		}
		
		return ERROR;
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public PageInfo<SystemUser> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<SystemUser> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDelIds() {
		return delIds;
	}

	public void setDelIds(String delIds) {
		this.delIds = delIds;
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
