<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 引入EasyUI的CSS和JS -->
<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<style>
	.menuA{
		text-decoration:none;
	}
</style>
<script type="text/javascript">
	$(function(){
		$(".menuA").click(function(){
			// 获得超链接中的文本内容
			var textContent = this.innerHTML;
			// 获得请求路径
			var url = this.href;
			// alert(url);
			// 创建选项卡
			createTabs(textContent,url);
			// 让超链接不跳转:
			return false;
		});
	});
	
	// 创建选项卡的函数:
	function createTabs(textContent,url){
		// 判断选项卡是否存在
		// 判断选项卡是否存在:
		var flag = $("#tt").tabs("exists",textContent);
		if(flag){
			// 已经存在该选项卡
			$('#tt').tabs("select",textContent);
		}else{
			// 不存在该选项卡
			$('#tt').tabs('add',{    
			    title:textContent,    
			    content:createUrl(url),    
			    closable:true    
			}); 
		}
	}
	
	function createUrl(url){
		return "<iframe src='"+url+"' style='border:0px;width:100%;height:95%;'></iframe>";
	}
</script>
</head>


<body>
<!--  ,selected:true -->
<div id="cc" class="easyui-layout" data-options="fit:true">   
    <div data-options="region:'north',title:'商城管理系统',split:true" style="height:100px;"></div>   
    <div data-options="region:'west',title:'系统菜单',split:true" style="width:200px;">
    	<div id="aa" class="easyui-accordion" data-options="fit:true">   
		    <div title="分类管理" data-options="iconCls:'icon-save' " style="overflow:auto;padding:10px;">   
		    	<a href="${pageContext.request.contextPath}/admin/category/CategoryManager.jsp" class="menuA">分类管理</a>
		    </div>   
		    <div title="商品管理"  data-options="iconCls:'icon-reload' " style="padding:10px;"> 
		    	<a href="${pageContext.request.contextPath}/admin/product/ProductManager.jsp" class="menuA">商品管理</a>  
		    </div>   
		    <div title="订单管理" data-options="iconCls:'icon-reload'  " >   
		    	<a href="${pageContext.request.contextPath}/admin/order/OrderManager.jsp" class="menuA">订单管理</a>  
		    </div>   
		    <div title="用户管理" data-options="iconCls:'icon-reload' " style="padding:10px;"> 
		    	<a href="${pageContext.request.contextPath}/admin/user/UserManager.jsp" class="menuA">用户管理</a>  
		    </div>   
		    <div title="系统管理" data-options="iconCls:'icon-reload'" >   
		    	<a href="#" class="menuA">系统管理</a>  
		    </div>   
		</div> 
    </div>   
    <div data-options="region:'center',title:''" style="padding:5px;background:#eee;">
    	<div id="tt" class="easyui-tabs" data-options="fit:true">   
		    <div title="数据区域" data-options="closable:true" style="padding:20px;display:none;">   
		        欢迎来到商城管理系统  
		    </div>   
		</div>  
    </div>   
</div>  
</body>
</html>