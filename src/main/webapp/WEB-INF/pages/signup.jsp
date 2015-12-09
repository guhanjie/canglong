<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<form action="" method="post" autocomplete="on" class="form-horizontal">
	<div class="form-group <c:if test="not empty ${pageContext.request['user.name.not.exist']}">has-error</c:if>">
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
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">注册</button>
		</div>
	</div>
</form>