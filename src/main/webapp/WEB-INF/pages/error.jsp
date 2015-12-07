<%@ page  contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div data-page="error.jsp" id="page-body"  class="container" style="min-height:700px;">
    <div id="page-body-content" style="width:315px;margin:0 auto;">
        <img src="<%=request.getContextPath() %>/resources/img/error.jpg"/>
        <div style="color:#666; font-size:30px;text-align: center;" ondblclick='$("#detailMsg").slideToggle(1000);'>
        	<c:if test="${not empty exception.screenMessage}">
        		${exception.screenMessage}
        	</c:if>
        	<c:if test="${empty exception.screenMessage}">
        		${exception.code}:${exception.message}
        	</c:if>
        </div>
    </div>
    <div id="detailMsg" style="display: none;">
            <br>错误代码：${exception.code}
			<br>错误简述：${exception.message} 
        	<c:if test="${not empty exception.causeMessage}">
        		<br>错误详情：${exception.causeMessage}
        	</c:if>
        	<c:if test="${empty exception.causeMessage}">
        		<br>错误详情：${exception.cause}
        	</c:if>
    </div>
</div>