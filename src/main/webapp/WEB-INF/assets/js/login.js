$(function() {
	$('form#login').submit(function() {
		$('.has-error').removeClass('has-error');
		$('.help-block').parent().remove();
		var $captcha = $(':input[name=captcha]');
		if($.cookie('captcha').toLowerCase() != $captcha.val().toLowerCase()) {
			$captcha.closest('.form-group').addClass('has-error');
			$('.captcha-img').after('<div class="col-sm-4 has-error"><span class="help-block">验证码不正确，请重新输入</span></div>');
			return false;
		}
	});
});