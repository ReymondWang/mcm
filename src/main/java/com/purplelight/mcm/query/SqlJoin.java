package com.purplelight.mcm.query;

import com.purplelight.mcm.exception.McmException;

public class SqlJoin {
	public final static int AND = 1;
	public final static int OR = 2;
	
	public static String parse(int join) throws McmException{
		if (join == AND){
			return "and";
		}
		if (join == OR){
			return "or";
		}
		
		throw new McmException("请从SqlJoin中选择查询连接条件。");
	}
}
