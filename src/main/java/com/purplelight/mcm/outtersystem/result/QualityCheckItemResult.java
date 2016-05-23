package com.purplelight.mcm.outtersystem.result;

import java.util.ArrayList;
import java.util.List;

import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.outtersystem.bean.QualityCheckItem;

public class QualityCheckItemResult extends Result {
	private List<QualityCheckItem> obj = new ArrayList<>();

	public List<QualityCheckItem> getObj() {
		return obj;
	}

	public void setObj(List<QualityCheckItem> obj) {
		this.obj = obj;
	}
 }
