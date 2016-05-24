package com.purplelight.mcm.outtersystem.result;

import java.util.ArrayList;
import java.util.List;

import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.outtersystem.bean.Licence;

public class LicenceResult extends Result {
	private List<Licence> obj = new ArrayList<>();

	public List<Licence> getObj() {
		return obj;
	}

	public void setObj(List<Licence> obj) {
		this.obj = obj;
	}
}
