<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>关于我们</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"
	type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
</head>

<body>
	<div class="container">


		<h1>${msg}</h1>
		<ol class="list-inline">

			<c:if test="${empty loginUser}">
				<li><a
					href="${pageContext.request.contextPath}/UserServlet?method=loginUI">登录</a></li>
				<li><a
					href="${pageContext.request.contextPath}/UserServlet?method=registUI">注册</a></li>
			</c:if>
			<li><a href="${pageContext.request.contextPath}/IndexServlet">首页</a></li>
		</ol>





	</div>



</body>

</html>