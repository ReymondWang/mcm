package com.purplelight.mcm.query;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.purplelight.mcm.exception.McmException;
import com.purplelight.mcm.util.ConvertUtil;
import com.purplelight.mcm.util.StringUtil;

public class Strategy {
	private Object entity;
	
	private String table;
	
	/**
	 * 多表关联查询时使用的自定义sql
	 */
	private JoinedTable joinedTable;
	
	private String alias;
	
	private String hql;
	
	private String hqlForCount;
	
	private List<ConditionItem> queryItems = new ArrayList<>();
	
	private Map<String, Object> queryProperties = new HashMap<String, Object>();
	
	public Strategy(){}
	
	public Strategy(Object entity, String alias) {
		this.entity = entity;
		this.alias = alias;
		
		setTableFromEntity();
	}
	
	public Strategy(String table, String alias){
		this.table = table;
		this.alias = alias;
	}
	
	public String generateHql() throws McmException, IllegalAccessException, IllegalArgumentException,
    InvocationTargetException{
		if (table == null && joinedTable == null){
			throw new McmException("Strategy中必须设置table");
		}
		
		StringBuffer strb = new StringBuffer();
		StringBuffer strbForCount = new StringBuffer();
		if (joinedTable != null){
			strb.append(String.format("select %s from %s where 1 = 1 ", joinedTable.getColumns(), joinedTable.getTables()));
			strbForCount.append(String.format("select count(*) from %s where 1 = 1 ", joinedTable.getTables()));
		} else {
			strb.append(String.format("select %s from %s %s where 1 = 1 ", alias, table, alias));
			strbForCount.append(String.format("select count(*) from %s %s where 1 = 1 ", table, alias));
		}
		
		if(entity != null){
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
	
	private boolean isCondition(Object obj){
		if (ConvertUtil.isInteger(obj)){
			return (int)obj != 0;
		} else if (ConvertUtil.isFloat(obj)){
			return (float)obj != 0;
		} else if (ConvertUtil.isDouble(obj)){
			return (double)obj != 0;
		} else if (ConvertUtil.isString(obj)){
			return !StringUtil.IsNullOrEmpty((String)obj);
		} else if (ConvertUtil.isTimeStamp(obj)){
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

	public JoinedTable getJoinedTable() {
		return joinedTable;
	}

	public void setJoinedTable(JoinedTable joinedTable) {
		this.joinedTable = joinedTable;
	}

}
