package com.purplelight.mcm.service;

import java.util.List;

import com.purplelight.mcm.entity.AppFunction;
import com.purplelight.mcm.entity.SystemUser;

public interface IAppFunctionService {
	public void addAppFunction(AppFunction appFunction, SystemUser loginedUser);
	public void updateAppFunction(AppFunction appFunction, SystemUser loginedUser) throws Exception;
	public void deleteAppFunction(AppFunction appFunction);
	public AppFunction getAppFunc(AppFunction appFunc) throws Exception;
	public List<AppFunction> getAppFuncByFragment(int fragment);
	public List<AppFunction> getAppFuncByFragmentAndPart(int fragment, int part);
	public List<AppFunction> getAppFuncByFragmentAndPart(int fragment, int part, int userId);
	public List<AppFunction> getAppFuncByFragmentAndPartAndType(int fragment, int part, int type);
}
