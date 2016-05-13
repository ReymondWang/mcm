package com.purplelight.mcm.dao;

import java.util.List;

import com.purplelight.mcm.entity.AppFunction;

public interface IAppFunctionDao extends IBaseDao<AppFunction, Integer> {
	public List<AppFunction> getByFragment(int fragment);
	public List<AppFunction> getByFragmentAndPart(int fragment, int part);
	public List<AppFunction> getByFragmentAndPartAndType(int fragment, int part, int functionType);
}
