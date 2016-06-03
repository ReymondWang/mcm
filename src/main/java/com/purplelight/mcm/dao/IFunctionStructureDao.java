package com.purplelight.mcm.dao;

import com.purplelight.mcm.entity.FunctionStructure;

public interface IFunctionStructureDao extends IBaseDao<FunctionStructure, Integer> {
	public FunctionStructure getByCode(String code);
}
