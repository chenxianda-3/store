<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品详情信息</title>
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

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
</style>
</head>

<body>

	<div class="container-fluid">

		<%@ include file="/jsp/header.jsp"%>

		<div class="container">
			<div class="row">
				<div
					style="border: 1px solid #e4e4e4; width: 930px; margin-bottom: 10px; margin: 0 auto; padding: 10px; margin-bottom: 10px;">
					<a href="${pageContext.request.contextPath}/">首页&nbsp;&nbsp;&gt;</a>

				</div>

				<div style="margin: 0 auto; width: 950px;">

					<form id="myForm">
						<div class="col-md-6">
							<img style="opacity: 1; width: 400px; height: 350px;" title=""
								class="medium"
								src="${pageContext.request.contextPath}/${product.pimage}">
						</div>

						<div class="col-md-6">
							<!-- 
					    	${product}  :底层依次调用4个域对象上的*.getAttribute("keyName");
					    	寻找到request可以获取到一个对象 product
					    	${product.pname} :通过获取到的product对象调用对象上的getPname()方法.
					     -->
							<div>
								<strong>${product.pname}</strong>
							</div>
							<div
								style="border-bottom: 1px dotted #dddddd; width: 350px; margin: 10px 0 10px 0;">
								<div>编号:${product.pid}</div>
							</div>

							<div style="margin: 10px 0 10px 0;">
								商城价: <strong style="color: #ef0101;">￥：${product.shop_price}元/份</strong>
								市场价：
								<del>￥${product.market_price}元/份</del>
							</div>

							<div style="margin: 10px 0 10px 0;">
								促销: <a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)"
									style="background-color: #f07373;">限时抢购</a>
							</div>

							<div
								style="padding: 10px; border: 1px solid #e7dbb1; width: 330px; margin: 15px 0 10px 0;; background-color: #fffee6;">

								<div
									style="border-bottom: 1px solid #faeac7; margin-top: 20px; padding-left: 10px;">
									购买数量:

									<!-- 向服务端发送 购买数量-->
									<input id="quantity" name="quantity" value="1" maxlength="4"
										size="10" type="text">
								</div>
								<!-- 向服务端发送 商品pid-->
								<input type="hidden" name="pid"  id ="pid" value="${product.pid}" />


								<div style="margin: 20px 0 10px 0;; text-align: center;">
									<%--加入到购物车 --%>
									<input id="btnId"
										style="background: url('${pageContext.request.contextPath}/img/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;"
										value="加入购物车" type="button">
								</div>
							</div>
					</form>
				</div>
				<div class="clear"></div>
				<div style="width: 950px; margin: 0 auto;">
					<div
						style="background-color: #d3d3d3; width: 930px; padding: 10px 10px; margin: 10px 0 10px 0;">
						<strong>商品介绍</strong>
						<h3>${product.pdesc}</h3>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/jsp/footer.jsp"%>

</body>
<script>
$(function () {
	var productQuantity =$("#quantity").val();
	$("input[name='quantity']").change(function(){
		//判断输入的是一个数字
		var productQuantity2 =$("#quantity").val();
		var reg =/^[1-9][0-9]{0,1}$/;
		if(!reg.test(productQuantity2)){
			//不是一个有效数字
			alert("请输入符合规定的数字,最多支持两位数");
			$("#quantity").val(productQuantity)
			return ;
		}
		
	});
	$("#btnId").click(function () {
		 $.ajax({
             type: "GET",
             url: "/store/CartServlet",
            data: {method:"addCartItemToCart",quantity:$("#quantity").val(), pid:$("#pid").val() },
			/* data:{method:"findByName",username:"chenxianda"}, */
             dataType: "json",
             error : function() {
					alert("加入购物车失败");
				}, 
             success: function(data){
                      if(data.flag){
                    	  alert("添加购物车成功！")
                      }else{
                    	  alert("请确认是否登录");
                      }
                      }
         });
	});
	
});
</script>
</html>