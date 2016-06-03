package com.purplelight.mcm.service;

import com.purplelight.mcm.api.parameter.SpecialItemParameter;
import com.purplelight.mcm.api.parameter.SpecialItemSubmitParameter;
import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.api.result.SpecialItemResult;

public interface ISpecialCheckService {
	public SpecialItemResult getSpecialCheckItems(SpecialItemParameter parameter);
	public Result submitSpecialCheckItem(SpecialItemSubmitParameter parameter);
}
