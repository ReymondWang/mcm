package com.purplelight.mcm.query;

import com.purplelight.mcm.exception.McmException;

public class SqlCondition {
	public final static int EQUALS = 1;
	public final static int NOT_QUUALS = 2;
	public final static int BIGGER = 3;
	public final static int BIGGER_OR_EQUALS = 4;
	public final static int LESS = 5;
	public final static int LESS_OR_EQUALS = 6;
	public final static int LIKE = 7;
	
	public static String parse(int condition) throws McmException{
		switch(condition){
			case EQUALS:
				return "=";
			case NOT_QUUALS:
				return "!=";
			case BIGGER:
				return ">";
			case BIGGER_OR_EQUALS:
				return ">=";
			case LESS:
				return "<";
			case LESS_OR_EQUALS:
				return "<=";
			case LIKE:
				return "like";
		}
		throw new McmException("请从SqlCondition选择查询条件。");
	}
}
