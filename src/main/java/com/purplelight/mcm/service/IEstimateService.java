package com.purplelight.mcm.service;

import com.purplelight.mcm.api.parameter.EstimateUploadParameter;
import com.purplelight.mcm.api.result.EstimateItemResult;
import com.purplelight.mcm.api.result.EstimateReportResult;
import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.api.result.SingleEstimateItemResult;
import com.purplelight.mcm.api.result.SingleEstimateReportResult;

public interface IEstimateService {
	public EstimateItemResult getEstimateItemsByIncharger(int userId, int systemId, int reportId, int pageNo, int pageSize);
	public EstimateItemResult getEstimateItemsByChecker(int userId, int systemId, int reportId, int pageNo, int pageSize);
	public SingleEstimateItemResult getSingleEstimateItem(int userId, int systemId, int itemId);
	public EstimateReportResult getEstimateReports(int userId, int systemId, int pageNo, int pageSize);
	public SingleEstimateReportResult getSingleEstimateReport(int userId, int systemId, int reportId);
	public Result saveEstimateItem(EstimateUploadParameter parameter);
}
