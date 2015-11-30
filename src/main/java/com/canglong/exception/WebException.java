package com.canglong.exception;

import java.util.ArrayList;
import java.util.List;

public class WebException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    private int httpStatus = 299;   
    private Integer code;							//异常代码
    private String message;    					//异常英文KEY
    private String screenMessage;   			//用于界面显示的消息
    private String errorCause; 					//异常原因，便于开发人员调试的消息    
    private List<String> params;				//错误消息中的参数，如：用户名长度要大于{0}小于{1}
          
    protected WebException(Integer code, String message) {
		super(message,null);
		this.code = code;
		this.message = message;
	}
        
    protected WebException(Integer code, String message, String errorCause) {
		super(message,null);
		this.code = code;
		this.message = message;
		this.errorCause = xssClean(errorCause);
	}
    
    protected WebException(Integer code, String message, String screenMessage, String errorCause) {
		super(message,null);
		this.code = code;
		this.message = message;
		this.screenMessage = screenMessage;
		this.errorCause = xssClean(errorCause);
	}
    
    protected WebException(Integer code, String message, String errorCause, Throwable e) {
		super(message,e);
		this.code = code;
		this.message = message;
		this.errorCause = xssClean(errorCause);
	}
	
    protected WebException(Integer code, String message, String screenMessage, String errorCause, Throwable e) {
		super(message,e);
		this.code = code;
		this.message = message;
		this.screenMessage = screenMessage;
		this.errorCause = xssClean(errorCause);
	}
	
	public int getHttpStatus() {
		return httpStatus;
	}


	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}


	public Integer getCode() {
		return code;
	}


	public WebException setCode(Integer code) {
		this.code = code;
		return this;
	}


	public String getMessage() {
		return message;
	}


	public WebException setMessage(String message) {
		this.message = message;
		return this;
	}


	public String getErrorCause() {
		return errorCause;
	}


	public WebException setErrorCause(String errorCause) {
		this.errorCause = errorCause;
		return this;
	}


	public WebException setParams(List<String> params) {
		this.params = params;
		return this;
	}
	
	public WebException addParam(String param) {
		if (this.params == null) {
			this.params = new ArrayList<String>();
		}
		this.params.add(param);
		return this;
	}

	public String getScreenMessage() {
		return screenMessage;
	}

	public WebException setScreenMessage(String screenMessage) {
		this.screenMessage = screenMessage;
		return this;
	}

	public List<String> getParams() {
		return params;
	}
	
	private static String xssClean(String str){
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
