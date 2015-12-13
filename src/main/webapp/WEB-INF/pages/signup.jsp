<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="" method="post" autocomplete="on" class="form-horizontal">
	<div class="form-group <c:if test="${not empty request['user.name.not.exist']}">has-error</c:if>">
		<label for="name" class="col-sm-2 control-label">用户名：</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="name" name="name" placeholder="请输入用户名" maxlength="30" autofocus required>
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">密码：</label>
		<div class="col-sm-10">
			<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" maxlength="30" required>
		</div>
	</div>
	<div class="form-group">
		<label for="confirmPassword" class="col-sm-2 control-label">确认密码：</label>
		<div class="col-sm-10">
			<input type="password" class="form-control" id="confirmPassword" placeholder="请再次输入密码" maxlength="30" required>
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">电子邮箱：</label>
		<div class="col-sm-10">
			<input type="email" class="form-control" id="email" name="email" placeholder="请输入联系邮箱" maxlength="50" required>
		</div>
	</div>
	<div class="form-group">
		<label for="bank" class="col-sm-2 control-label">提现银行卡号：</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="bank" name="bank" placeholder="请输入提现银行卡号" maxlength="20" required>
		</div>
	</div>
	<div class="form-group">
		<label for="captcha" class="col-sm-2 control-label">验证码：</label>
		<div class="col-sm-5">
			<input type="text" class="form-control" id="captcha" name="captcha" placeholder="请输入验证码" maxlength="4" required>
		</div>
		<div class="col-sm-5">
			<img border="1" src="${rootPath}/captcha" onclick="this.src='${rootPath}/captcha?rand='+Math.random();" alt="若看不清楚图片，可点击刷新" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">注册</button>
		</div>
	</div>
</form>