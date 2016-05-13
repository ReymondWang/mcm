package com.purplelight.mcm.dao;

import java.util.List;

import com.purplelight.mcm.entity.OutterSystem;

public interface IOutterSystemDao extends IBaseDao<OutterSystem, Integer> {
	public OutterSystem getByCode(String code);
	public List<OutterSystem> getBySystemTypeCode(String typeCode);
}
