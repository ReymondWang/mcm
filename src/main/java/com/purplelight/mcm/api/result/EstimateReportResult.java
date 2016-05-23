package com.purplelight.mcm.api.result;

import java.util.ArrayList;
import java.util.List;

import com.purplelight.mcm.api.bean.EstimateReport;

public class EstimateReportResult extends Result {
	private List<EstimateReport> reports = new ArrayList<>();

	public List<EstimateReport> getReports() {
		return reports;
	}

	public void setReports(List<EstimateReport> reports) {
		this.reports = reports;
	}
}
