package com.purplelight.mcm.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.purplelight.mcm.dao.IOutterSystemDao;
import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;
import com.purplelight.mcm.service.IOutterSystemService;
import com.purplelight.mcm.util.McmConstant;
import com.purplelight.mcm.util.UpdateUtil;

public class OutterSystemServiceImpl extends BaseServiceImpl implements IOutterSystemService {
	private static Logger logger = LoggerFactory.getLogger(OutterSystemServiceImpl.class);
	
	@Resource
	private IOutterSystemDao outterSystemDao;
	
	@Override
	public void addOutterSystem(OutterSystem outterSystem, SystemUser loginedUser) {
		outterSystem.setInputUser(loginedUser);
		outterSystem.setInputTime(new Timestamp(System.currentTimeMillis()));
		outterSystem.setUpdateUser(loginedUser);
		outterSystem.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		outterSystemDao.save(outterSystem);
	}

	@Override
	public void updateOutterSystem(OutterSystem outterSystem, SystemUser loginedUser) throws Exception  {
		OutterSystem orgSys = outterSystemDao.getById(outterSystem.getId());
		List<String> expField = new ArrayList<String>();
		expField.add("StartUsing");
		orgSys = UpdateUtil.copyNotNullOrEmptyValue(orgSys, outterSystem, expField);
		
		orgSys.setUpdateUser(loginedUser);
		orgSys.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		outterSystemDao.update(orgSys);
	}

	@Override
	public void deleteOutterSystem(OutterSystem outterSystem) {
		outterSystemDao.delete(outterSystem);
	}

	@Override
	public List<OutterSystem> getStartUsingOuterSystem() {
		OutterSystem entity = new OutterSystem();
		entity.setStartUsing(1);
		Strategy strategy = new Strategy(entity, "os");
		
		List<OutterSystem> list = new ArrayList<>();
		try{
			list = outterSystemDao.find(strategy);
		} catch (Exception ex){
			logger.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
		
		return list;
	}

	@Override
	public PageInfo<OutterSystem> query(Strategy strategy, int pageNo) throws Exception {
		int pageSize = McmConstant.PAGE_SIZE;
		int startPos = (pageNo - 1) * pageSize;
		
		return outterSystemDao.find(strategy, startPos, pageNo, pageSize);
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

	@Override
	public OutterSystem getOutterSystemById(int id) {
		return outterSystemDao.getById(id);
	}

	@Override
	public List<OutterSystem> getAll() {
		return outterSystemDao.getAll();
	}

	@Override
	public List<OutterSystem> getAllWithBlank() {
		List<OutterSystem> list = getAll();
		
		OutterSystem blank = new OutterSystem();
		blank.setSystemName("外部系统");
		list.add(0, blank);
		
		return list;
	}

}
