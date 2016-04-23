package com.purplelight.mcm.util;

import java.lang.reflect.Method;

public class UpdateUtil {
	
	/**
	 * 比较两个相同的实体对象，如果目标对象的字段值不为null（对象类型）或0（基础类型），则将
	 * 这个目标对象的值拷贝到原始对象中。
	 * @param org           原始对象
	 * @param tar           目标对象
	 * @return              拷贝后的原始对象
	 * @throws Exception    如果原始对象和目标对象的类型不一致，则抛出异常。
	 */
	public static<T> T copyNotNullOrEmptyValue(T org, T tar) throws Exception{
		// 如果有一方为Null，则直接返回。
		if (org == null || tar == null){
			return org;
		}
		
		if (org.getClass().getName().equals(tar.getClass().getName())){
			Method[] orgMethods = org.getClass().getMethods();
			Method[] tarMethods = tar.getClass().getMethods();
			for(Method tarM : tarMethods){
				if (tarM.getName().startsWith("get") || tarM.getName().startsWith("is")){
					Object val = tarM.invoke(tar);
					boolean isNeededToCopy = false;
					if (ConvertUtil.isInteger(val) && (int)val != 0){
						isNeededToCopy = true;
					} else if (ConvertUtil.isFloat(val) && (float)val != 0){
						isNeededToCopy = true;
					} else if (ConvertUtil.isDouble(val) && (double)val != 0){
						isNeededToCopy = true;
					} else if (ConvertUtil.isString(val) && !StringUtil.IsNullOrEmpty(String.valueOf(val))){
						isNeededToCopy = true;
					} else if (ConvertUtil.isTimeStamp(val) && val != null){
						isNeededToCopy = true;
					}
					
					if (isNeededToCopy){
						String cmpName = "";
						if (tarM.getName().startsWith("get")){
							cmpName = tarM.getName().replace("get", "");
						} else if (tarM.getName().startsWith("is")){
							cmpName = tarM.getName().replace("is", "");
						}
						
						for(Method orgM : orgMethods){
							if (orgM.getName().startsWith("set") && cmpName.equals(orgM.getName().replace("set", ""))){
								orgM.invoke(org, val);
								break;
							}
						}
					}
				}
			}
			
			return org;
		}else{
			throw new Exception("copyNotNullOrEmptyValue方法中org和tar的类型必须一致.");
		}
	}
}
