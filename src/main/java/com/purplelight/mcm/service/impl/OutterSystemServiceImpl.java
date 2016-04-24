package com.purplelight.mcm.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.purplelight.mcm.dao.IOutterSystemDao;
import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;
import com.purplelight.mcm.service.IOutterSystemService;
import com.purplelight.mcm.util.MCMContext;
import com.purplelight.mcm.util.UpdateUtil;

public class OutterSystemServiceImpl implements IOutterSystemService {

	@Resource
	private IOutterSystemDao outterSystemDao;
	
	@Override
	public void addOutterSystem(OutterSystem outterSystem, SystemUser loginedUser) {
		outterSystem.setInputUser(loginedUser.getId());
		outterSystem.setInputTime(new Timestamp(System.currentTimeMillis()));
		outterSystem.setUpdateUser(loginedUser.getId());
		outterSystem.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		outterSystemDao.addOutterSystem(outterSystem);
	}

	@Override
	public void updateOutterSystem(OutterSystem outterSystem, SystemUser loginedUser) throws Exception  {
		OutterSystem orgSys = outterSystemDao.getById(outterSystem.getId());
		orgSys = UpdateUtil.copyNotNullOrEmptyValue(orgSys, outterSystem);
		
		orgSys.setUpdateUser(loginedUser.getId());
		orgSys.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		outterSystemDao.updateOutterSystem(orgSys);
	}

	@Override
	public void deleteOutterSystem(OutterSystem outterSystem) {
		outterSystemDao.deleteOutterSystem(outterSystem);
	}

	@Override
	public PageInfo<OutterSystem> query(Strategy strategy, int pageNo) throws Exception {
		int pageSize = MCMContext.PAGE_SIZE;
		int startPos = (pageNo - 1) * pageSize;
		
		return outterSystemDao.findByPageInfo(strategy, startPos, pageNo, pageSize);
	}

	@Override
	public OutterSystem getOutterSystem(OutterSystem outterSystem) throws Exception {
		Strategy strategy = new Strategy(outterSystem, "os");
		List<OutterSystem> list = outterSystemDao.find(strategy);
		if (list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}

}
