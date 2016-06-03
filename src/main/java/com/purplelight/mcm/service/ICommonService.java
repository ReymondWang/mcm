package com.purplelight.mcm.service;

import com.purplelight.mcm.api.result.ProjectResult;
import com.purplelight.mcm.api.result.TokenResult;

public interface ICommonService {
	public ProjectResult getProjectList(int userId, int systemId);
	public TokenResult getToken(int userId, int systemId);
}
