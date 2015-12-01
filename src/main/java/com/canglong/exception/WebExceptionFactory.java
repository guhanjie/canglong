package com.canglong.exception;

public class WebExceptionFactory {
	
	public static WebException exception(Integer code, String message, String screenMessage, String errorCause, Throwable cause){
        return new WebException(code, message, errorCause, screenMessage, cause);
    }
		
    public static WebException exception(WebExceptionEnum exceptionEnum, String errorCause){
    	if(exceptionEnum == null) {
    		exceptionEnum = WebExceptionEnum.SYSTEM_ERROR;
    	}
		return new WebException(exceptionEnum.getCode(), exceptionEnum.getMessage(), exceptionEnum.getScreenMessage(), errorCause);
    }
	
    public static WebException exception(WebExceptionEnum exceptionEnum, Throwable cause){
    	if(exceptionEnum == null) {
    		exceptionEnum = WebExceptionEnum.SYSTEM_ERROR;
    	}
		return new WebException(exceptionEnum.getCode(), exceptionEnum.getMessage(), exceptionEnum.getScreenMessage(), cause);
    }
    
    public static WebException exception(WebExceptionEnum exceptionEnum,  String errorCause, Throwable cause){
    	if(exceptionEnum == null) {
    		exceptionEnum = WebExceptionEnum.SYSTEM_ERROR;
    	}
		return new WebException(exceptionEnum.getCode(), exceptionEnum.getMessage(), exceptionEnum.getScreenMessage(), errorCause, cause);
    }

	public static WebException exception(WebExceptionEnum exceptionEnum){
		return exception(exceptionEnum, null, null);
    }
}
