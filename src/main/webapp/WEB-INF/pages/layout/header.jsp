<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<span>这里是Header</span>
	<span>${user.name}</span>
	<form action="logout" method="get">
		<input type="submit"  value="Submit" >
	</form>
</div>