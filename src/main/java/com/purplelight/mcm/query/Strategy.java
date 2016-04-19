package com.purplelight.mcm.query;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.purplelight.mcm.util.StringUtil;

public class Strategy {
	private Object entity;
	
	private String table;
	
	private String alias;
	
	private String hql;
	
	private String hqlForCount;
	
	private List<ConditionItem> queryItems = new ArrayList<>();
	
	private Map<String, Object> queryProperties = new HashMap<String, Object>();
	
	public Strategy(Object entity, String alias) {
		this.entity = entity;
		this.alias = alias;
		
		setTableFromEntity();
	}
	
	public Strategy(String table, String alias){
		this.table = table;
		this.alias = alias;
	}
	
	public String generateHql() throws Exception{
		if (table == null){
			throw new Exception("Strategy中必须设置table");
		}
		if (StringUtil.IsNullOrEmpty(alias)){
			throw new Exception("Strategy中必须设置alias");
		}
		
		StringBuffer strb = new StringBuffer();
		StringBuffer strbForCount = new StringBuffer();
		strb.append(String.format("select %s from %s %s where 1 = 1 ", alias, table, alias));
		strbForCount.append(String.format("select count(*) from %s %s where 1 = 1 ", alias, table, alias));
		
		// 取得目标类中的所有的getter方法，如果（String，date，timestamp）的不为空或（int，float，double）不为0，则作为查询条件。
		Method[] methods = entity.getClass().getMethods();
		for (Method m : methods){
			if (m.getName().startsWith("get")){
				String column = m.getName().replace("get", "");
				column = column.substring(0, 1).toLowerCase() + column.substring(1);
				if (!isInTheQueryItem(column) && isCondition(m.invoke(entity))){
					strb.append(String.format(" and %s.%s = :%s", alias, column, column));
					strbForCount.append(String.format(" and %s.%s = :%s", alias, column, column));
					queryProperties.put(column, m.invoke(entity));
				}
			}
		}
		
		// 取得queryItems中的查询条件
		for(ConditionItem item : queryItems){
			strb.append(item.parse());
			strbForCount.append(item.parse());
			queryProperties.put(item.getName(), item.getValue());
		}
		
		hql = strb.toString();
		hqlForCount = strbForCount.toString();
		
		return hql;
	}

	public void add(ConditionItem item){
		queryItems.add(item);
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
		
		setTableFromEntity();
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public List<ConditionItem> getQueryItems(){
		return queryItems;
	}
	
	private void setTableFromEntity(){
		if (entity != null){
			table = entity.getClass().getSimpleName();
		}
	}
	
	private boolean isInTheQueryItem(String column){
		for(ConditionItem item : queryItems){
			if (column.equals(item.getName())){
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isInteger(Object obj){
		if (obj == null){
			return false;
		}
		return obj.getClass().getName().equals(Integer.class.getName()) || obj.getClass().getName().equals("int");
	}
	
	private boolean isFloat(Object obj){
		if (obj == null){
			return false;
		}
		return obj.getClass().getName().equals(Float.class.getName()) || obj.getClass().getName().equals("float");
	}
	
	private boolean isDouble(Object obj){
		if (obj == null){
			return false;
		}
		return obj.getClass().getName().equals(Double.class.getName()) || obj.getClass().getName().equals("double");
	}
	
	private boolean isString(Object obj){
		if (obj == null){
			return false;
		}
		return obj.getClass().getName().equals(String.class.getName());
	}
	
	private boolean isTimeStamp(Object obj){
		if (obj == null){
			return false;
		}
		return obj.getClass().getName().equals(Timestamp.class.getName());
	}
	
	private boolean isCondition(Object obj){
		if (isInteger(obj)){
			return (int)obj != 0;
		} else if (isFloat(obj)){
			return (float)obj != 0;
		} else if (isDouble(obj)){
			return (double)obj != 0;
		} else if (isString(obj)){
			return !StringUtil.IsNullOrEmpty((String)obj);
		} else if (isTimeStamp(obj)){
			return obj != null;
		}
		
		return false;
	}
	
	public String getHql() {
		return hql;
	}

	public String getHqlForCount() {
		return hqlForCount;
	}

	public Map<String, Object> getQueryProperties() {
		return queryProperties;
	}
}
