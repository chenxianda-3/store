$().ready(function() {

			jQuery.validator.addMethod("usernamecheck",
					function(value, element) {
						var flag = 1;
						$.ajax({
									type : "post",
									url : "/store/UserServlet",
									async : false,
									data : {
										username : function() {
											return $("#username").val();
										},
										method : function() {
											return "findByName";
										}
									},
									dataType : "json",
									error : function() {
										alert("aaaa");
									},
									success : function(data) {
										// 成功便執行下面的操作
									/*	alert(data.flag);*/
										/*
										 * $("#showUsernameSpan").addClass("label");
										 * $("#showUsernameSpan").text(data.message);
										 */
										if (data.flag) {
											// 用户名可用
											flag = 0;
											/*
											 * $("#showUsernameSpan").addClass("label-success");
											 * $("#showUsernameSpan").removeClass("label-danger");
											 * $("#registButton").removeAttr("disabled");
											 */
											return true;
										} else {
											// 用户名不可用
											/*
											 * $("#showUsernameSpan").addClass("label-danger");
											 * $("#showUsernameSpan").removeClass("label-success");
											 * $("#registButton").attr("disabled",
											 * "disabled");
											 */
											return false;
										}

									}
								});
						if (flag == 0) {
							return true;
						} else {
							return false;
						}
					}, "用户名已存在");

			// 在键盘按下并释放及提交后验证提交表单
			$("#signupForm").validate({
						/*
						 * onfocusout : function(element) { $(element).valid();
						 * var isValidate = $("#signupForm").validate.form(); if
						 * (!isValidate) { $("#registButton").attr("disabled",
						 * "disabled"); } else {
						 * $("#registButton").removeAttr("disabled"); } },
						 */
			        	onfocusout:function(element) { $(element).valid();},
			        	onclick:false,
						onkeyup:false,
						onclick:false,
						rules : {
							username : {
								required : true,
								minlength : 5,
								usernamecheck : true
						
							},
							password : {
								required : true,
								minlength : 6
							},
							confirmpwd : {
								required : true,
								minlength : 5,
								equalTo : "#password"
							},
							email : {
								required : true,
								email : true
							},
							sex : "required",
							birthday : "required",
							checkimg : {
								required : true,
								rangelength : [4, 4]
							}
						},
						messages : {
							username : {
								required : "请输入用户名",
								minlength : "用户名不能少于 5 个字符"
							},
							password : {
								required : "请输入密码",
								minlength : "密码长度不能小于 6 个字符"
							},
							confirmpwd : {
								required : "请输入密码",
								minlength : "密码长度不能小于 5 个字母",
								equalTo : "两次密码输入不一致"
							},
							email : {
								required : "请输入用户名",
								email : "请输入一个正确的邮箱"
							},
							sex : "请选择你的性别",
							birthday : "请选择你的出生日期",
							checkimg : {
								required : "请输入验证码",
								rangelength : "验证码为四个字符"
							}

						}
					});

		});

function change() {
	$("#im").attr("src", "/store/imageCode?time=" + new Date().getTime());

};
