package com.purplelight.mcm.service;

import java.util.List;

import com.purplelight.mcm.api.bean.SpecialItemCheckResult;
import com.purplelight.mcm.api.result.Result;
import com.purplelight.mcm.api.result.SpecialItemResult;

public interface ISpecialCheckService {
	public SpecialItemResult getSpecialCheckItems(int userId, int systemId, int reportId, boolean onlyUnChecked, int pageNo, int pageSize);
	public Result submitSpecialCheckItem(int userId, int systemId, int itemId, List<SpecialItemCheckResult> results, List<String> images);
}
