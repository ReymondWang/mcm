package com.purplelight.mcm.dao;

import java.util.List;

import com.purplelight.mcm.entity.OutterSystem;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;

public interface IOutterSystemDao {
	public List<OutterSystem> getAll();
	public OutterSystem getById(int id);
	public OutterSystem getByCode(String code);
	public List<OutterSystem> getBySystemTypeCode(String typeCode);
	public void addOutterSystem(OutterSystem outterSystem);
	public void updateOutterSystem(OutterSystem outterSystem);
	public void deleteOutterSystem(OutterSystem outterSystem);
	public List<OutterSystem> find(Strategy strategy) throws Exception;
	public PageInfo<OutterSystem> findByPageInfo(Strategy queryStrategy, int startPos, int pageNo, int pageSize) throws Exception;
}
