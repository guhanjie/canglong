<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="login" action="" method="post" class="form-horizontal">
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">用户名：</label>
		<div class="col-sm-5">
			<input type="text" class="form-control" id="name" name="name" placeholder="请输入用户名" maxlength="30" autofocus required>
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">密码：</label>
		<div class="col-sm-5">
			<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" maxlength="30" required>
		</div>
	</div>
	<div class="form-group">
		<label for="captcha" class="col-sm-2 control-label">验证码：</label>
		<div class="col-sm-2">
			<input type="text" class="form-control" id="captcha" name="captcha" placeholder="请输入验证码" maxlength="4" required>
		</div>
		<div class="col-sm-2 captcha-img">
			<img border="1" src="${rootPath}/captcha" onclick="this.src='${rootPath}/captcha?rand='+Math.random();" alt="若看不清楚图片，可点击刷新" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-1">
			<button type="submit" class="btn btn-default">登录</button>
		</div>
		<div class="col-sm-1">
			<a class="btn btn-default" href="${rootPath}/signup" role="button">注册</a>
		</div>
		<c:if test="${not empty loginError}">
			<div class="col-sm-3 has-error">
				<span class="help-block">登录失败：${loginError}</span>
			</div>
		</c:if>
	</div>
</form>