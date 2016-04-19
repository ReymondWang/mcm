package com.purplelight.mcm.query;

public class SqlJoin {
	public final static int AND = 1;
	public final static int OR = 2;
	
	public static String parse(int join) throws Exception{
		if (join == AND){
			return "and";
		}
		if (join == OR){
			return "or";
		}
		
		throw new Exception("请从SqlJoin中选择查询连接条件。");
	}
}
