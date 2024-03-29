<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://cn.edu.lingnan/jsp/mytaglib" prefix="my"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

</head>
<body>
			<!--
            	描述：菜单栏
            -->

<div class="container-fluid">
				<div class="col-md-4">
					
				</div>
				<div class="col-md-5">
				
				</div>
					<div class="col-md-3" style="padding-top:20px">
					<ol class="list-inline">
					
					  <my:if test="${empty loginUser}">					
						<li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUI">登录</a></li>
						<li><a href="${pageContext.request.contextPath}/UserServlet?method=registUI">注册</a></li>
						<li><a href="${pageContext.request.contextPath}/CartServlet?method=getALLCartItemByUid">购物车</a></li>
					  </my:if>
						
					  <my:if test="${not empty loginUser}">
						<li>欢迎${loginUser.username}</li>
						<li><a href="${pageContext.request.contextPath}/UserServlet?method=logOut">退出</a></li>
						<li><a href="${pageContext.request.contextPath}/CartServlet?method=getALLCartItemByUid">购物车</a></li>
						<li><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrdersWithPage&num=1">我的订单</a></li>
					  </my:if>	
						
					</ol>
				</div>
			</div>
			
				<!--
            	描述：导航条
            -->
			<div class="container-fluid">
				<nav class="navbar navbar-inverse" >
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="${pageContext.request.contextPath}/IndexServlet">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav" id="myUL">
								
							</ul>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div>

</body>
<script>
$(function(){
	//向服务端CategoryServlet__>gteAllCats发起ajax请求,服务端经过处理,
	//将所有分类信息以JSON格式的数据返回,获取到返回的所有分类绑定在页面的显示分类区域
	var url="/store/CategoryServlet";
	var obj={"method":"findAllCats"};
	$.post(url,obj,function(data){
		//alert(data);
	
		//获取到服务端响应会的数据,经过观察data中存放的是一个JSON格式数组,遍历数组,动态的显示分类区域代码	
		$.each(data,function(i,obj){
			var li="<li><a href='/store/ProductServlet?method=findProductsByCidWithPage&num=1&cid="+obj.cid+"'>"+obj.cname+"</a></li>";
			$("#myUL").append(li);
		});
		
	},"json");
	
	
});
</script>
</html>