package com.purplelight.mcm.service;

import java.util.List;

import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.entity.SystemUser;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;

public interface IOutterSystemService {
	public void addOutterSystem(OutterSystem outterSystem, SystemUser loginedUser);
	public void updateOutterSystem(OutterSystem outterSystem, SystemUser loginedUser) throws Exception ;
	public void deleteOutterSystem(OutterSystem outterSystem);
	public PageInfo<OutterSystem> query(Strategy strategy, int pageNo) throws Exception;
	public OutterSystem getOutterSystem(OutterSystem outterSystem) throws Exception;
	public OutterSystem getOutterSystemById(int id);
	public List<OutterSystem> getAll();
	public List<OutterSystem> getAllWithBlank();
	public List<OutterSystem> getStartUsingOuterSystem();
}
