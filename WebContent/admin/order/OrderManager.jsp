<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
		
			$(function(){
			
				
				$('#dg').datagrid({    
				    url:'/store/AdminOrderServlet?method=findOrders',    
				     fitColumns:true, 	
				    autoRowHeight:true,  
				    columns:[[    
				    	 {field:'oid',title:'订单编号',width:50},
				        {field:'ordertime',title:'下单时间',width:25},
				        {field:'total',title:'总价',width:30},
				        {field:'address',title:'收获地址',width:30},
				        {field:'name',title:'收获人',width:30},
				        {field:'telephone',title:'收货人电话',width:30},
				      {field:'state',title:'订单状态',width:30 ,formatter: function(value,row,index){
				        	if (row.state==1){
								return "未付款";
							} else if(row.state==2){
								return "已付款";
							}else if(row.state==3){
								return "已发货";
							}else {
								return "已收货";
							}
				        	}
				        }, 
				        {field:'xxx',title:'操作',width:40,formatter: function(value,row,index){
							return '<a href="#" onclick="edit(&quot;'+row.oid+'&quot;)">编辑</a> | <a href="#" onclick="del(&quot;'+row.oid+'&quot;)">删除</a>';
						}
					}
				    ]]

				}); 
			});
			
		 	function findallcats (){
				$.ajax({
				    type : "post",
				    url : "/store/AdminOrderServlet?method=findAllCats", //此次url改为真正需要的url
				    dataType:"json",
				    error:function() {
						alert("error");
					},
				    success : function(data, status) {
				    	if($("select[id='select']>option").length<=1){
				    		var data2 = eval(data);
					    	$.each(data2, function(index, item) {
					    		$("#select").append(  //此处向select中循环绑定数据
									    "<option value="+item.cid+">" + item.cname+ "</option>");
					    	}); 
				    		
				    	}
				    	
				    
				    }
				});
			} 
			
			
			
			

			
	
			
			function edit(id){
				// alert(id);
				$("#winUpdate").window("open");
				$('#formUpdate').form('load',"/store/AdminOrderServlet?method=findOrderByOid&oid="+id+"");
			}
			
			
			function update(){
				$('#formUpdate').form({    
				    url:"/store/AdminOrderServlet?method=updateOrder",    
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
				$.post("/store/AdminOrderServlet?method=deleteOrder",{"oid":id},function(data){
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
	<table id="dg" fit="true"></table>

	

	<div id="winUpdate" class="easyui-window" title="修改订单"
		style="width: 500px; height: 250px"
		data-options="iconCls:'icon-save',modal:true,closed:true">

		<form id="formUpdate" method="post">
			<input type="hidden" name="oid" />
			<input type="hidden" name="ordertime" />
			<input type="hidden" name="total" />
			<TABLE cellSpacing=0 cellPadding=5 border=0>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						收货人电话
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="telephone"  id="userAction_save_do_logonName" class="bg"/>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						订单收货人：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="name"  id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						订单地址：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="address"  id="userAction_save_do_logonName" class="bg"/>
						</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						订单状态：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<select name="state"  id="updateselect" >
							<option value="1">未付款</option>
							<option value="2">已付款</option>
							<option value="3">已发货</option>
							<option value="4">已收货</option>
						</select>
					</td>
				</tr>
				
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

