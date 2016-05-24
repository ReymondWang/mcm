package com.purplelight.mcm.outtersystem.result;

import java.util.ArrayList;
import java.util.List;

import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.outtersystem.bean.LicenceFile;

public class LicenceFileResult extends Result {
	private List<LicenceFile> obj = new ArrayList<>();

	public List<LicenceFile> getObj() {
		return obj;
	}

	public void setObj(List<LicenceFile> obj) {
		this.obj = obj;
	}
}
