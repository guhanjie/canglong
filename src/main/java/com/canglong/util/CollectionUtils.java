package com.canglong.util;

import java.util.Collection;

public class CollectionUtils {
	
	public boolean isNotEmpty(Collection<Object> collection){
		if(collection != null && collection.size() > 0){
			return true;
		}
		return false;
	}
	
	public boolean isEmpty(Collection<Object> collection){
		if(collection == null || collection.size() == 0){
			return true;
		}
		return false;
	}
	
}
