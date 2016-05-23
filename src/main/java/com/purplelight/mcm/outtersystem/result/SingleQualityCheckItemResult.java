package com.purplelight.mcm.outtersystem.result;

import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.outtersystem.bean.QualityCheckItem;

public class SingleQualityCheckItemResult extends Result {
	private QualityCheckItem obj = new QualityCheckItem();

	public QualityCheckItem getObj() {
		return obj;
	}

	public void setObj(QualityCheckItem obj) {
		this.obj = obj;
	}
}
