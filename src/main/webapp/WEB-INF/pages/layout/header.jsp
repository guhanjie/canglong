<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
	<span>这里是Header</span>
	<div class="header-right">
		<c:choose>
			<c:when test="${not empty loginUser}">
				<span>${loginUser.name}</span>
				<a href="${rootPath}/logout">[登出]</a>
			</c:when>
			<c:otherwise>
				<a href="${rootPath}/login">[登录]</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>