package com.purplelight.mcm.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gson.Gson;
import com.purplelight.mcm.api.bean.BindUser;
import com.purplelight.mcm.api.bean.OutterSystemBindInfo;
import com.purplelight.mcm.api.bean.SystemUserInfo;
import com.purplelight.mcm.api.result.BindUserResult;
import com.purplelight.mcm.api.result.LoginResult;
import com.purplelight.mcm.dao.IOutterSystemDao;
import com.purplelight.mcm.dao.ISystemUserDao;
import com.purplelight.mcm.dao.IUserBindSystemDao;
import com.purplelight.mcm.entity.UserBindSystem;
import com.purplelight.mcm.query.Strategy;
import com.purplelight.mcm.service.IUserBindSystemService;
import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.util.ConvertUtil;
import com.purplelight.mcm.util.HttpUtil;
import com.purplelight.mcm.util.StringUtil;
import com.purplelight.mcm.util.UpdateUtil;

public class UserBindSystemServiceImpl implements IUserBindSystemService {

	@Resource
	private IUserBindSystemDao userBindSystemDao;
	
	@Resource
	private IOutterSystemDao outterSystemDao;
	
	@Resource
	private ISystemUserDao systemUserDao;
	
	@Override
	public void addBindInfo(UserBindSystem entity, SystemUser loginedUser) {
		entity.setInputUser(loginedUser);
		entity.setInputTime(new Timestamp(System.currentTimeMillis()));
		entity.setUpdateUser(loginedUser);
		entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		userBindSystemDao.save(entity);
	}

	@Override
	public void updateBindInfo(UserBindSystem entity, SystemUser loginedUser) throws Exception {
		UserBindSystem orgEntity = userBindSystemDao.getById(entity.getId());
		orgEntity = UpdateUtil.copyNotNullOrEmptyValue(orgEntity, entity);
		orgEntity.setUpdateUser(loginedUser);
		orgEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		userBindSystemDao.update(orgEntity);
	}

	@Override
	public void deleteBindInfo(UserBindSystem entity) {
		userBindSystemDao.delete(entity);
	}

	@Override
	public void deleteBindInfo(int userId, int systemId) {
		String hql = "from UserBindSystem us where us.user.id = :userId and us.outterSystem.id = :systemId";
		Query query = userBindSystemDao.getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("systemId", systemId);
		
		@SuppressWarnings("unchecked")
		List<UserBindSystem> list = (List<UserBindSystem>)query.list();
		if (list != null && list.size() > 0){
			userBindSystemDao.deleteAll(list);
		}
	}

	/**
	 * 检查系统中启用的外部系统，用用户当前的登录名和密码去检验是否成功，如果成功则将本用户的信息复制过来。
	 * 创建一个APP的用户，并与原系统账户进行绑定。
	 * 处于效率的考虑，只尝试一次，建议用在只有一个启用的外部系统的时候，快速建立用户。
	 * Config.properties中quick_register要设置为true
	 */
	@Override
	public LoginResult bindWithCreate(String userCode, String password, SystemUser loginedUser) throws Exception {
		LoginResult loginResult = new LoginResult();
		
		OutterSystem queryEntity = new OutterSystem();
		queryEntity.setStartUsing(1);
		Strategy strategy = new Strategy(queryEntity, "os");
		List<OutterSystem> systemList = outterSystemDao.find(strategy);
		if (systemList != null && systemList.size() > 0){
			Gson gson = new Gson();
			
			OutterSystem trySystem = systemList.get(0);
			HashMap<String, String> tryParams = new HashMap<>();
			tryParams.put("name", userCode);
			tryParams.put("password", password);
			String responseJson = HttpUtil.GetDataFromNet(
					trySystem.getSystemUrl() + trySystem.getValidationUrl()
					, tryParams
					, HttpUtil.POST);
			if (!StringUtil.IsNullOrEmpty(responseJson)){
				BindUserResult result = gson.fromJson(responseJson, BindUserResult.class);
				if (result.isSuccess()){
					BindUser bindUser = result.getObj();
					SystemUser user = new SystemUser();
					user.setUserCode(bindUser.getEmail());
					user.setUserName(bindUser.getName());
					user.setSex("男".equals(bindUser.getGender()) ? 1 : 2);
					user.setEmail(bindUser.getEmail());
					user.setInputUser(loginedUser.getId());
					user.setInputTime(new Timestamp(System.currentTimeMillis()));
					user.setUpdateUser(loginedUser.getId());
					user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
					systemUserDao.save(user);
					systemUserDao.updatePassword(user.getId(), password);
					
					UserBindSystem bindSystem = new UserBindSystem();
					bindSystem.setOutterSystem(trySystem);
					bindSystem.setUser(user);
					bindSystem.setToken(bindUser.getToken());
					bindSystem.setOutterUserId(bindUser.getUserId());
					bindSystem.setInputUser(loginedUser);
					bindSystem.setInputTime(new Timestamp(System.currentTimeMillis()));
					bindSystem.setUpdateUser(loginedUser);
					bindSystem.setUpdateTime(new Timestamp(System.currentTimeMillis()));
					userBindSystemDao.save(bindSystem);
					
					loginResult.setSuccess(true);
					
					SystemUserInfo userInfo = new SystemUserInfo();
					userInfo.setId(ConvertUtil.toString(user.getId()));
					userInfo.setUserCode(user.getUserCode());
					userInfo.setUserName(user.getUserName());
					userInfo.setSex(ConvertUtil.toString(user.getSex()));
					userInfo.setEmail(user.getEmail());
					userInfo.setPhone(user.getPhone());
					userInfo.setAddress(user.getAddress());
					userInfo.setHeadImgPath(user.getHeadImgPath());
					userInfo.setToken(user.getToken());
					loginResult.setUser(userInfo);
				} else {
					loginResult.setSuccess(false);
					loginResult.setMessage(result.getMessage());
				}
			} else {
				loginResult.setSuccess(false);
				loginResult.setMessage("外部系统返回信息为空");
			}
		} else {
			loginResult.setSuccess(false);
			loginResult.setMessage("没有启用的外部系统");
		}
		
		return loginResult;
	}

	@Override
	public List<OutterSystemBindInfo> getUserBindInfo(int userId) {
		Session session = userBindSystemDao.getSession();
		String hql = "select os.id"
				+ ", os.systemType"
				+ ", os.systemUrl"
				+ ", os.startUsing"
				+ ", os.validationUrl"
				+ ", os.systemDescription"
				+ ", coalesce(us.user.id, 0, 1)"
				+ ", os.systemName"
				+ " from OutterSystem as os left join os.bindUsers us with us.user.id = :id"
				+ " where os.startUsing = 1";
		Query query = session.createQuery(hql);
		query.setInteger("id", userId);
		
		@SuppressWarnings("rawtypes")
		List list = query.list();
		List<OutterSystemBindInfo> retList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++){
			Object[] valArr = (Object[])query.list().get(i);
			OutterSystemBindInfo bindInfo = new OutterSystemBindInfo();
			bindInfo.setSystemId((int)valArr[0]);
			bindInfo.setSystemType((String.valueOf(valArr[1])));
			bindInfo.setSystemUrl((String.valueOf(valArr[2])));
			bindInfo.setStartUsing((int)valArr[3]);
			bindInfo.setValidationUrl((String.valueOf(valArr[4])));
			bindInfo.setSystemDescription((String.valueOf(valArr[5])));
			if ((int)valArr[6] == 0){
				bindInfo.setBinded(false);
			} else {
				bindInfo.setBinded(true);
			}
			bindInfo.setSystemName(String.valueOf(valArr[7]));
			
			retList.add(bindInfo);
		}
		
		return retList;
	}

	@Override
	public UserBindSystem getByUserIdAndSystemId(int userId, int systemId) {
		String hql = "from UserBindSystem us where us.user.id = :userId and us.outterSystem.id = :systemId";
		Query query = userBindSystemDao.getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("systemId", systemId);
		
		@SuppressWarnings("rawtypes")
		List list = query.list();
		
		if (list != null && list.size() > 0){
			return (UserBindSystem)list.get(0);
		}
		
		return null;
	}

}
