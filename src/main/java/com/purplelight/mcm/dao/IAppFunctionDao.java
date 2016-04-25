package com.purplelight.mcm.dao;

import java.util.List;

import com.purplelight.mcm.entity.AppFunction;
import com.purplelight.mcm.query.Strategy;

public interface IAppFunctionDao {
	public List<AppFunction> getAll();
	public AppFunction getById(int id);
	public List<AppFunction> getByFragment(int fragment);
	public List<AppFunction> getByFragmentAndPart(int fragment, int part);
	public List<AppFunction> getByFragmentAndPartAndType(int fragment, int part, int functionType);
	public List<AppFunction> find(Strategy strategy) throws Exception;
	public void add(AppFunction appFunction);
	public void update(AppFunction appFunction);
	public void delete(AppFunction appFunction);
}
