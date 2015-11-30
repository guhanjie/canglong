package com.canglong.exception;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.canglong.util.HttpUtils;

public class WebExceptionHandler implements HandlerExceptionResolver {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object handler,
			Exception exception) {		
		LOGGER.error(exception.getMessage(), exception);
		
		if (!(exception instanceof WebException)) {
			String debugMessage = exception.getMessage();
			if(debugMessage == null){
				debugMessage="no message";
			}
			debugMessage =debugMessage.trim();
			exception = WebExceptionFactory.exception(WebExceptionEnum.SYSTEM_ERROR, debugMessage, exception);
		}		
		WebException ee = (WebException)exception;
		String errorCause = ee.getErrorCause();
		//最多显示200个字符，以免将底层堆栈返回给客户端
		if(errorCause != null && errorCause.length()>200){
			errorCause = errorCause.substring(0, 200);
		}		
		//status
		httpservletresponse.setStatus(ee.getHttpStatus());
		//ajax
		if(HttpUtils.isAjaxRequest(httpservletrequest) || HttpUtils.isMultiPartRequest(httpservletrequest)){
			PrintWriter os = null;
			try {
				httpservletresponse.setStatus(ee.getHttpStatus());
				os = httpservletresponse.getWriter();
				os.print(errorCause);
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			} finally {
				close(os);
			}
			return null;
		}else{
			httpservletrequest.setAttribute("errorCause", errorCause);
			httpservletrequest.setAttribute("exception", exception);
			return new ModelAndView("error");
		}
	}
	
	protected void close(Closeable obj){
		try {
			if(obj != null){
				obj.close();
			}			
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage(),e);
		}
	}
}
