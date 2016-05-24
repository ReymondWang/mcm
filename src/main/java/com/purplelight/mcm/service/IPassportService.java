package com.purplelight.mcm.service;

import com.purplelight.mcm.api.result.PassportFileResult;
import com.purplelight.mcm.api.result.PassportResult;

public interface IPassportService {
	public PassportResult getPassportList(int userId, int systemId, String projectId, String category, int pageNo, int pageSize);
	public PassportFileResult getPassportFiles(int userId, int systemId, int itemId);
}
