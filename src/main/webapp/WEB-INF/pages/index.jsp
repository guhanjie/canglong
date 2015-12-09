<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h2>这里是主页</h2>
<div>
	Request.getRequestURL() = <%=request.getRequestURL() %><br/>
	Request.getRequestURI() = <%=request.getRequestURI() %><br/>
	Request.getContextPath() = <%=request.getContextPath() %><br/>
	Request.getServletPath() = <%=request.getServletPath() %><br/>
	Request.getPathInfo() = <%=request.getPathInfo() %><br/>
	Request.getPathTranslated() = <%=request.getPathTranslated() %><br/>
	Request.getQueryString() = <%=request.getQueryString() %><br/>
	Request.getRemoteAddr() = <%=request.getRemoteAddr() %><br/>
	Request.getRemoteHost() = <%=request.getRemoteHost() %><br/>
	Request.getRemotePort() = <%=request.getRemotePort() %><br/>
	Request.getRemoteUser() = <%=request.getRemoteUser() %><br/>
	Request.getLocalAddr() = <%=request.getLocalAddr() %><br/>
	Request.getLocalName() = <%=request.getLocalName() %><br/>
	Request.getLocalPort() = <%=request.getLocalPort() %><br/>
	Request.getMethod() = <%=request.getMethod() %><br/>
</div>
<img name="captcha" border="1" src="captcha" onclick="this.src='captcha?rand='+Math.random();"
	alt="若看不清楚图片，可点击刷新" />