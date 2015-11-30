package com.canglong.util;

import javax.servlet.http.HttpServletRequest;


public class SchemeUtil {
	private static String HTTPS = "https";
	private static String HTTP = "http";
	 
	/**
	 * 如果url不是以https开头，需要修改成request.getScheme()开头
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	public static String schema(HttpServletRequest request, String url){
		if(url == null){
			return null;
		}
		
		String scheme = request.getScheme();		
		if(url.startsWith(HTTPS)){
			return url;
		}else if(url.startsWith(HTTP)){
			if(HTTPS.equalsIgnoreCase(scheme)){
				return HTTPS + url.substring(4);
			}else{
				return url;
			}
		}else{
			return scheme + "://" + url;
		}
	}
}
