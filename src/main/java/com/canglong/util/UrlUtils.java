package com.canglong.util;

import org.apache.commons.lang.StringUtils;

public class UrlUtils {

	/**
	 * 将字符串数组用/拼接，\替换为/,并去掉多余的/,使输出符合url格式
	 * 
	 * @param args
	 * @return
	 */
	public static String concat(String... args){
		String str = "";
		for(String arg: args){
			if(StringUtils.isNotBlank(arg)){
				arg = arg.replace("\\", "/");
				str = str +"/"+ arg;
			}			
		}
		str = str.replace("\\", "/");
		while (str.indexOf("//") > 0) {
			str = str.replace("//", "/");
		}
		str = str.substring(1);
		str = str.replace(":/", "://");
		return str;
	}
	
	public static void main(String[] args) {
		System.out.println(concat("a/","b","c/"));
		System.out.println(concat("a/","b","c"));
		System.out.println(concat("a/","b/","/c"));
		System.out.println(concat("http://a//","/b/","\\\\\\\\c\\\\///"));
	}

}
