package com.purplelight.mcm.api.result;

import com.purplelight.mcm.api.bean.EstimateReport;

public class SingleEstimateReportResult extends Result {
	private EstimateReport report;

	public EstimateReport getReport() {
		return report;
	}

	public void setReport(EstimateReport report) {
		this.report = report;
	}
}
