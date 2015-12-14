$(function() {
	function jump(count) {
        window.setTimeout(function(){
            count--;
            if(count > 0) {
            	$('.signup-result .jump').remove();
                $('.signup-result').append('<span class="col-sm-4 help-block jump">'+count+' 秒后跳转至首页</span>');
                jump(count);
            } else {
                location.href = App.rootPath+"/index";
            }
        }, 1000);
    }
	$('form#signup').submit(function() {
		$('.has-error').removeClass('has-error');
		$('.help-block').parent().remove();
		var $captcha = $(':input[name=captcha]');
		if($.cookie('captcha').toLowerCase() != $captcha.val().toLowerCase()) {
			$captcha.closest('.form-group').addClass('has-error');
			$('.captcha-img').after('<div class="col-sm-4 has-error"><span class="help-block">验证码不正确，请重新输入</span></div>');
			return false;
		}
		if($('#password').val().toLowerCase() != $('#confirmPassword').val().toLowerCase()) {
			$('#password').closest('.form-group').addClass('has-error');
			$('#confirmPassword').closest('.form-group').addClass('has-error');
			$('#password').parent().after('<div class="col-sm-4 has-error"><span class="help-block">两次输入密码不一致，请重新输入</span></div>');
			return false;
		}
		$.ajax({
			  type: "POST",
			  url: $(this).attr('action'),
			  data: $(this).serialize(),
			  success: function(data) {
				  if(data.success == true) {
					  $('.signup-btn').after('<div class="signup-result col-sm-8 has-success"><span class="col-sm-4 help-block">'+data.content.name+' 恭喜您，注册成功！</span></div>');
					  jump(65);
				  }
				  else {
					  var errMsg = data.content;
					  if(!errMsg) {
						  errMsg = (data.description==undefined)? "" : data.description;
					  }
					  $('.signup-btn').after('<div class="col-sm-4 has-error"><span class="help-block">'+errMsg+'</span></div>');
				  }
			  },
			  dataType: 'json'
			});
		return false;
	});
});