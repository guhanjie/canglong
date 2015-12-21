/** 
 * Project Name:		canglong 
 * Package Name:	com.canglong.util 
 * File Name:			XssSecurity.java 
 * Create Date:		2015年12月21日 下午3:31:25 
 * Copyright (c) 2008-2015, Canglong All Rights Reserved.
 */  
package com.canglong.util;

/**
 * Class Name:		XssSecurity<br/>
 * Description:		[description]
 * @time				2015年12月21日 下午3:31:25
 * @author			canglong
 * @version			1.0.0 
 * @since 			JDK 1.6 
 */
public class XssSecurity {

	public static String xssClean(String str){
		if(str == null || str.length() == 0){
			return str;
		}
		str = str.replace("&", "&amp;");
		str = str.replace("<", "&lt;");
		str = str.replace(">", "&gt;");
		str = str.replace("'", "&apos;");
		str = str.replace("\"","&quot;");
		return str;
	}
}
