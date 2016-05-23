package com.purplelight.mcm.outtersystem.result;

import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.outtersystem.bean.QualityCheckReport;

public class SingleQualityCheckReportResult extends Result {
	private QualityCheckReport obj = new QualityCheckReport();

	public QualityCheckReport getObj() {
		return obj;
	}

	public void setObj(QualityCheckReport obj) {
		this.obj = obj;
	}
}
