<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.validate.js"></script>

<style>
#header {
    padding: 22px 0;
    padding-top: 22px;
    padding-right: 0px;
    padding-bottom: 22px;
    padding-left: 0px;
    height: 70px;
}
.logo {
    width: 1300px;
    margin: 0 auto;
    overflow: hidden;
}


.login-msg.error {
    border-color: #ffb4a8;
    background-color: #fef2f2;
    color: #6C6C6C;
}

.error {
	color: red;
}

body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

.container .row div {
	/* position:relative;
	 float:left; */
	
}

font {
	color: #666;
	font-size: 22px;
	font-weight: normal;
	padding-right: 17px;
}
</style>
<script type="text/javascript">

</script>
</head>
<body>

<!-- 页头部分 -->
<div id="header" >
			
<!-- 提示部分 -->
<div class="logo" >
    
    <div class="login-msg error" style="text-align: center;width: 980px; margin: 0 auto;">
        <p class="error" style="float: none;width: auto;" >
            为确保您账户的安全及正常使用，依《网络安全法》相关要求，6月1日起会员账户需绑定手机。如您还未绑定，请尽快完成，感谢您的理解及支持！
        </p>
    </div>
    
</div>
			
		</div>

		<!--  中间登录部分！-->
	<div class="container"
		style="width: 100%; height: 460px; background: #e93854 url('${pageContext.request.contextPath}/img/images/loginbg.png') no-repeat;">
		<div class="row">
			<div class="col-md-7">
				<!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
			</div>

			<div class="col-md-5">
				<div
					style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
					<font>用户登录</font>USER LOGIN
					<div class="error">${msg}</div>
					<div>&nbsp;</div>
					<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/UserServlet?method=userLogin"
						id="signupForm" name="signupForm">

						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-6" style>
								<input type="text" class="form-control " id="username"
									name="username" placeholder="请输入用户名" value="${cookie.remember.value}" >
							</div>
						
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="请输入密码">
							</div>
						</div>
							<div class="form-group">
							<label for="password" class="col-sm-2 control-label">验证码</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="checkimg" name="checkimg"
									placeholder="请输入验证码" size="4">
							</div>
							<div class="col-sm-2">
								<img onclick="change();" id="im"
									src="${pageContext.request.contextPath}/imageCode" />
							</div>

						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label> <input type="checkbox" name="autologin" value="1"  ${not empty cookie.autologin ? "checked='checked'":"" }>  自动登录</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<label> <input type="checkbox" name="remember" value="1" ${not empty cookie.remember? "checked='checked'":"" }> 记住用户名</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" width="100" value="登录" name="submit" 
									border="0"
									style="background: url('${pageContext.request.contextPath}/img/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
							</div>
						</div>
					</form>
		
					
				</div>
			</div>
		</div>
	</div>
	<!--
            	描述：页脚部分
            -->
			<div class="container-fluid">
				<div style="margin-top:50px;">
					<img src="${pageContext.request.contextPath}/img/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
				</div>
		
				<div style="text-align: center;margin-top: 5px;">
					<ul class="list-inline">
						<li><a>关于我们</a></li>
						<li><a>联系我们</a></li>
						<li><a>招贤纳士</a></li>
						<li><a>法律声明</a></li>
						<li><a>友情链接</a></li>
						<li><a>支付方式</a></li>
						<li><a>配送方式</a></li>
						<li><a>服务声明</a></li>
						<li><a>广告声明</a></li>
					</ul>
				</div>
			</div>

</body>
</html>