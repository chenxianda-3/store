
$().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
  $("#signupForm").validate({
	    rules: {
	      username: {
	        required: true,
	        minlength: 5
	      },
	      password: {
	        required: true,
	        minlength: 5
	      },
	      checkimg: {
	      	  required: true,
	          rangelength:[4,4]
	      }
	    },
	    messages: {
      username: {
        required: "请输入用户名",
        minlength: "用户名不能少于 5 个字符"
      },
      password: {
        required: "请输入密码",
        minlength: "密码长度不能小于 5 个字符"
      },
      checkimg: {
      	required: "请输入验证码",
        rangelength: "验证码为四个字符"
      }
      
	    }
	});
	
	
});


function change() {
		$("#im").attr("src", "/store/imageCode?time="+ new Date().getTime());
		
	};