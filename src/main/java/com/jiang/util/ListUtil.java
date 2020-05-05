package com.jiang.util;

import java.util.List;

public class ListUtil {
	public static boolean isEmpty(List list){
		if(list==null)
			return true;
		else if(list.size()==0)
			return true;
		return false;
	}
}
