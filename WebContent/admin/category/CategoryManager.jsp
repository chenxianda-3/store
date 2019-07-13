<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"  src="${pageContext.request.contextPath}/js/public.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
		<script type="text/javascript">
		
			$(function(){
				$('#dg').datagrid({    
				    url:'/store/AdminCategoryServlet?method=findAllCats',    
				    columns:[[    
				        {field:'cname',title:'分类名称',width:100},   
				        {field:'xxx',title:'操作',width:100,formatter: function(value,row,index){
							return '<a href="#" onclick="edit(&quot;'+row.cid+'&quot;)">编辑</a> | <a href="#" onclick="del(&quot;'+row.cid+'&quot;)">删除</a>';
						}
					}
				    ]],
				    striped:true,
				    rownumbers:true,
	
				    toolbar: [{
						iconCls: 'icon-add',
						handler: function(){
							$('#winAdd').window('open');  					
						}
					}] 

				}); 
			});
			
			function save(){
				$('#formAdd').form({    
				    url:"/store/AdminCategoryServlet?method=addCategory",    
				    success:function(data){    
				       $.messager.show({
				    		title:'提示消息',
				    		msg:"添加成功!",
				    		timeout:3000,
				    		showType:'slide'
				    	});
				    	// 关闭窗口:
				    	$("#winAdd").window("close");
				    	// 重新加载数据:
				    	$("#dg").datagrid("reload");
				    }    
				});    
				// submit the form    
				$('#formAdd').submit();  
			}
			
			
			function edit(id){
				// alert(id);
				$("#winUpdate").window("open");
				$('#formUpdate').form('load',"/store/AdminCategoryServlet?method=findCategoryByCid&cid="+id+"");
			}
			
			
			function update(){
				$('#formUpdate').form({    
				    url:"/store/AdminCategoryServlet?method=updateCategory",    
				    success:function(data){ 
				       $.messager.show({
				    		title:'提示消息',
				    		msg:"修改成功！",
				    		timeout:5000,
				    		showType:'slide'
				    	});
				    	// 关闭窗口:
				    	$("#winUpdate").window("close");
				    	// 重新加载数据:
				    	$("#dg").datagrid("reload");
				    }    
				});    
				// submit the form    
				$('#formUpdate').submit();  
			}
			
			
			function del(id){
				$.post("/store/AdminCategoryServlet?method=deleteCategory",{"cid":id},function(data){
					$.messager.show({
			    		title:'提示消息',
			    		msg:data.msg,
			    		timeout:3000,
			    		showType:'slide'
			    	});
					$("#dg").datagrid("reload");
				},"json");
			}
			
			
		</script>
	</HEAD>
	<body>
		<table id="dg"></table>  
		
	<!-- 添加客户的表单，默认是隐藏的 -->
		<div id="winAdd" class="easyui-window" title="添加分类" style="width:300px;height:200px"   
	        data-options="iconCls:'icon-save',modal:true,closed:true">   
		    
	        <form id="formAdd" method="post">
	        	<input type="hidden" name="cid"/>
				<TABLE cellSpacing=0 cellPadding=5  border=0>
				<TR>
					<td>类别名称：</td>
					<td>
						<INPUT class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="cname">
					</td>
					
				</TR>
					<tr>
						<td rowspan=2>
							<button id="customerBtn" type="button" onclick="save()">保存</button>
						</td>
					</tr>
				</TABLE>   
			</form>
		</div>
		
		<div id="winUpdate" class="easyui-window" title="修改分类" style="width:300px;height:200px"   
	        data-options="iconCls:'icon-save',modal:true,closed:true">   
		    
	        <form id="formUpdate" method="post">
	        	<input type="hidden" name="cid"/>
				<TABLE cellSpacing=0 cellPadding=5  border=0>
				<TR>
					<td>类别名称：</td>
					<td>
						<INPUT class=textbox id=sChannel2 style="WIDTH: 180px" maxLength=50 name="cname">
					</td>
					
				</TR>
					<tr>
						<td rowspan=2>
							<button id="customerBtn" type="button" onclick="update()">保存</button>
						</td>
					</tr>
				</TABLE>   
			</form>
		</div>
	</body>
</HTML>

