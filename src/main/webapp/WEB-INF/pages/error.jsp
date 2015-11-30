<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div data-page="error.jsp" id="page-body"  class="container" style="min-height:700px;">
    <div id="page-body-content" style="width:315px;margin:0 auto;">
        <img src="//static.glodon.com/static${staticVersion}/common/images/500.png"/>
        <div style="color:#666;font-size:30px;" ondblclick='$("#detailMsg").slideToggle(1000);'>
        	<c:if test="${not empty exception.screenMessage}">
        		${exception.screenMessage}
        	</c:if>
        	<c:if test="${empty exception.screenMessage}">
        		${exception.code}:${exception.errorCause}
        	</c:if>
        </div>
    </div>
    <div id="detailMsg" style="display: none;">
            <br>错误代码：${exception.code}            
        	<c:if test="${not empty exception.errorCause}">
        		<br>错误详情：${exception.errorCause}
        	</c:if>
        	<c:if test="${empty exception.errorCause}">
        		<br>错误详情：${exception.cause}
        	</c:if>
    </div>
</div>