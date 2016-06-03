package com.purplelight.mcm.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.purplelight.mcm.dao.IFunctionStructureDao;
import com.purplelight.mcm.entity.FunctionStructure;
import com.purplelight.mcm.exception.McmException;
import com.purplelight.mcm.query.Strategy;

public class FunctionStructureDaoImpl extends BaseDaoImpl<FunctionStructure, Integer> implements IFunctionStructureDao {

	@Override
	public FunctionStructure getByCode(String code) {
		FunctionStructure entity = new FunctionStructure();
		entity.setFunctionCode(code);
		
		Strategy strategy = new Strategy(entity, "fs");
		try {
			List<FunctionStructure> list = find(strategy);
			if (list != null && list.size() > 0){
				return list.get(0);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | McmException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
