<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<form action="" method="post" class="form-horizontal">
	<div class="form-group">
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
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">登录</button>
		</div>
	</div>
</form>