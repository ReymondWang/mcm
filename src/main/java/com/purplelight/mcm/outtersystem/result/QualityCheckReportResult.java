package com.purplelight.mcm.outtersystem.result;

import java.util.ArrayList;
import java.util.List;

import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.outtersystem.bean.QualityCheckReport;

public class QualityCheckReportResult extends Result {
	private List<QualityCheckReport> obj = new ArrayList<>();

	public List<QualityCheckReport> getObj() {
		return obj;
	}

	public void setObj(List<QualityCheckReport> obj) {
		this.obj = obj;
	}
}
