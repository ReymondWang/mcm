package com.purplelight.mcm.outtersystem.result;

import java.util.List;

import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.outtersystem.bean.SpecialCheckItem;

public class SpecialCheckItemResult extends Result {
	private List<SpecialCheckItem> obj;

	public List<SpecialCheckItem> getObj() {
		return obj;
	}

	public void setObj(List<SpecialCheckItem> obj) {
		this.obj = obj;
	}
}
