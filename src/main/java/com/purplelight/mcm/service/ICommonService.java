package com.purplelight.mcm.service;

import com.purplelight.mcm.api.result.ProjectResult;

public interface ICommonService {
	public ProjectResult getProjectList(int userId, int systemId);
}
