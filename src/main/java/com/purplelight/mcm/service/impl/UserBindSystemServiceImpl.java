package com.purplelight.mcm.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;

import com.purplelight.mcm.bean.OutterSystemBindInfo;
import com.purplelight.mcm.dao.IUserBindSystemDao;
import com.purplelight.mcm.entity.UserBindSystem;
import com.purplelight.mcm.service.IUserBindSystemService;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.util.UpdateUtil;

public class UserBindSystemServiceImpl implements IUserBindSystemService {

	@Resource
	private IUserBindSystemDao userBindSystemDao;
	
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
	public List<OutterSystemBindInfo> getUserBindInfo(int userId) {
		Session session = userBindSystemDao.getSession();
		String hql = "select os.id"
				+ ", os.systemCode"
				+ ", os.systemType"
				+ ", os.systemUrl"
				+ ", os.startUsing"
				+ ", os.validationUrl"
				+ ", os.systemDescription"
				+ ", coalesce(us.user.id, 0, 1)"
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
			bindInfo.setSystemCode((String.valueOf(valArr[1])));
			bindInfo.setSystemType((String.valueOf(valArr[2])));
			bindInfo.setSystemUrl((String.valueOf(valArr[3])));
			bindInfo.setStartUsing((int)valArr[4]);
			bindInfo.setValidationUrl((String.valueOf(valArr[5])));
			bindInfo.setSystemDescription((String.valueOf(valArr[6])));
			if ((int)valArr[7] == 0){
				bindInfo.setBinded(false);
			} else {
				bindInfo.setBinded(true);
			}
			
			retList.add(bindInfo);
		}
		
		return retList;
	}

}
