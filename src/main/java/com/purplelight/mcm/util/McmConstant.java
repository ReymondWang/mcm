package com.purplelight.mcm.util;

import java.util.ArrayList;
import java.util.List;

public final class McmConstant {
	/**
	 * 通用分页大小
	 */
	public final static int PAGE_SIZE = 10;
	
	public final static class Fragment{
		public static final int HOME = 1;
		public static final int WORK = 2;
		public static final int PROFILE = 3;
		
		public static List<SpinnerItem> getItemList(){
			List<SpinnerItem> retList = new ArrayList<>();
			retList.add(new SpinnerItem("1", "首页"));
			retList.add(new SpinnerItem("2", "办公"));
			retList.add(new SpinnerItem("3", "个人"));
			
			return retList;
		}
		
		public static SpinnerItem getItem(int code){
			SpinnerItem item = new SpinnerItem("1", "首页");
			switch(code){
			case HOME:
				item = new SpinnerItem("1", "首页");
				break;
			case WORK:
				item = new SpinnerItem("2", "办公");
				break;
			case PROFILE:
				item = new SpinnerItem("3", "个人");
				break;
			}
			
			return item;
		}
	}
	
	/**
	 * 功能在APP上显示的区域
	 * @author wangyn
	 *
	 */
	public final static class FragmentPart{
		public static final int COMMON = 0;
		public static final int TOP = 1;
		public static final int BODY = 2;
		public static final int FOOT = 3;
		
		public static List<SpinnerItem> getItemList(){
			List<SpinnerItem> retList = new ArrayList<>();
			retList.add(new SpinnerItem("0", "通用"));
			retList.add(new SpinnerItem("1", "头部"));
			retList.add(new SpinnerItem("2", "中部"));
			retList.add(new SpinnerItem("3", "尾部"));
			
			return retList;
		}
		
		public static SpinnerItem getItem(int code){
			SpinnerItem item = new SpinnerItem("0", "通用");
			switch(code){
			case COMMON:
				item = new SpinnerItem("0", "通用");
				break;
			case TOP:
				item = new SpinnerItem("1", "头部");
				break;
			case BODY:
				item = new SpinnerItem("2", "中部");
				break;
			case FOOT:
				item = new SpinnerItem("3", "尾部");
				break;
			}
			
			return item;
		}
	}
	
	/**
	 * 功能的类型
	 * @author wangyn
	 *
	 */
	public final static class FunctionType{
		// 内部文章，只提供展示。
		public static final int INNER_ARTICAL = 1;
		// 内部网络轻应用。
		public static final int INNER_WAP_FUNCTION = 2;
		// 内部原生应用。
		public static final int INNER_NATIVE_FUNCTION = 3;
		// 外部文章，只提供展示。
		public static final int OUTTER_ARTICAL = 4;
		// 外部网络轻应用。
		public static final int OUTTER_WAP_FUNCTION = 5;
		// 外部原生应用。
		public static final int OUTTER_NATIVE_FUNCTION = 6;
		
		public static List<SpinnerItem> getItemList(){
			List<SpinnerItem> retList = new ArrayList<>();
			retList.add(new SpinnerItem("1", "内部文章"));
			retList.add(new SpinnerItem("2", "内部轻应用"));
			retList.add(new SpinnerItem("3", "内部原生应用"));
			retList.add(new SpinnerItem("4", "外部文章"));
			retList.add(new SpinnerItem("5", "外部轻应用"));
			retList.add(new SpinnerItem("6", "外部原生应用"));
			
			return retList;
		}
		
		public static SpinnerItem getItem(int code){
			SpinnerItem item = new SpinnerItem("1", "内部文章");
			switch(code){
			case INNER_ARTICAL:
				item = new SpinnerItem("1", "内部文章");
				break;
			case INNER_WAP_FUNCTION:
				item = new SpinnerItem("2", "内部轻应用");
				break;
			case INNER_NATIVE_FUNCTION:
				item = new SpinnerItem("3", "内部原生应用");
				break;
			case OUTTER_ARTICAL:
				item = new SpinnerItem("4", "外部文章");
				break;
			case OUTTER_WAP_FUNCTION:
				item = new SpinnerItem("5", "外部轻应用");
				break;
			case OUTTER_NATIVE_FUNCTION:
				item = new SpinnerItem("6", "外部原生应用");
				break;
			}
			
			return item;
		}
	}
	
}
