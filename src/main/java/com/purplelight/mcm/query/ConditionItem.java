package com.purplelight.mcm.query;

/**
 * 单个查询条件，定义单个查询项目的详细情况，这些信息包括：
 * 连接方式（and，or）;
 * 查询条件（＝,!=,>,>=,<,<=,like）
 * 字段名称
 * 查询值
 * 是否有开始的括号
 * 是否有结束的括号
 * @author wangyn
 *
 */
public class ConditionItem {
	private String name;
	
	private String value;
	
	private int join = SqlJoin.AND;
	
	private int condition = SqlCondition.EQUALS;

	private boolean beginningBracket = false;
	
	private boolean enddingBracket = false;
	
	public ConditionItem(){}
	
	public ConditionItem(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	public ConditionItem(String name, String value, int join, int condition) {
		this.name = name;
		this.value = value;
		this.join = join;
		this.condition = condition;
	}

	public ConditionItem(String name, String value, int join, int condition, boolean beginningBracket,
			boolean enddingBracket) {
		super();
		this.name = name;
		this.value = value;
		this.join = join;
		this.condition = condition;
		this.beginningBracket = beginningBracket;
		this.enddingBracket = enddingBracket;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getJoin() {
		return join;
	}

	public void setJoin(int join) {
		this.join = join;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public boolean hasBeginningBracket() {
		return beginningBracket;
	}

	public void setBeginningBracket(boolean beginningBracket) {
		this.beginningBracket = beginningBracket;
	}

	public boolean hasEnddingBracket() {
		return enddingBracket;
	}

	public void setEnddingBracket(boolean enddingBracket) {
		this.enddingBracket = enddingBracket;
	}
	
	public String parse() throws Exception{
		StringBuffer strb = new StringBuffer();
		strb.append(" ");
		strb.append(SqlJoin.parse(join));
		strb.append(" ");
		if (hasBeginningBracket()){
			strb.append("(");
		}
		strb.append(name);
		strb.append(" ");
		strb.append(SqlCondition.parse(condition));
		strb.append(" :");
		strb.append(name);
		if (hasEnddingBracket()){
			strb.append(")");
		}
		
		return strb.toString();
	}
}
