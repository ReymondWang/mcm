package com.purplelight.mcm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionStructureUtil {
	private static final Map<String, String> COMPARE_RIGHT = new HashMap<>();
	static {
		COMPARE_RIGHT.put("进度计划管理-项目计划查看", "100100002");
		COMPARE_RIGHT.put("质量安全管理-集团第三方评估查看", "100100003");
		COMPARE_RIGHT.put("质量安全管理-区域联合检查查看", "100100004");
		COMPARE_RIGHT.put("质量安全管理-项目安全文明查看", "100100005");
		COMPARE_RIGHT.put("质量安全管理-专项检查查看", "100100006");
		COMPARE_RIGHT.put("质量安全管理-一户一验查看", "100100007");
		COMPARE_RIGHT.put("证照管理-证照查询", "100100008");
	}
	
	public static List<String> getRights(List<String> businessRights){
		List<String> rights = new ArrayList<>();
		
		// 添加通用权限
		rights.add("100100001");
		
		for(String busiRight : businessRights){
			if (COMPARE_RIGHT.containsKey(busiRight)){
				rights.add(COMPARE_RIGHT.get(busiRight));
			}
		}
		
		return rights;
	}
}
