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
				    url:'/store/AdminProductServlet?method=findAllProductsWithPage',    
				     fitColumns:true, 	
				    autoRowHeight:true,  
				    columns:[[    
				        {field:'pname',title:'商品名称',width:100},
				        {field:'pimage',title:'商品图片',width:25,formatter: function(value,row,index){
							return '<img width="80%" src="/store/'+row.pimage+'">';
							
						}
				        },
				        {field:'market_price',title:'市场价',width:30},
				        {field:'shop_price',title:'商城价',width:30},
				        {field:'pdate',title:'上架时间',width:30},
				      {field:'is_hot',title:'是否热门',width:30 ,formatter: function(value,row,index){
				        	if (row.is_hot==1){
								return "是";
							} else {
								return "否";
							}
				        	}
				        }, 
				        {field:'pdesc',title:'商品描述',width:200},
				        {field:'xxx',title:'操作',width:40,formatter: function(value,row,index){
							return '<a href="#" onclick="edit(&quot;'+row.pid+'&quot;)">编辑</a> | <a href="#" onclick="del(&quot;'+row.pid+'&quot;)">删除</a>';
						}
					}
				    ]],
				   // 显示分页工具条
			        pagination:true,
			        // 初始化的页数
			         pageNumber:1,
			        // 每页显示记录数:
			        pageSize:10,
			        // 分页工具条中下拉列表中的值：
			       /*  pageList:false, */
				    striped:true, 
				    rownumbers:true,  
				    //显示添加图标
				    toolbar: [{
						iconCls: 'icon-add',
						handler: function(){
							$('#winAdd').window('open');
						}
					}] 

				}); 
			});
			
		 	function findallcats (){
				$.ajax({
				    type : "post",
				    url : "/store/AdminCategoryServlet?method=findAllCats", //此次url改为真正需要的url
				    dataType:"json",
				    error:function() {
						alert("error");
					},
				    success : function(data, status) {
				    	if($("select[id='select']>option").length<=1){
				    		var data2 = eval(data);
					    	$.each(data2, function(index, item) {
					    		$("#select").append(  //此处向select中循环绑定数据
									    '<option value="'+item.cid+'"> '+ item.cname+' </option>');
					    	}); 
				    		
				    	}
				    	
				    
				    }
				});
			} 
			
		 	
			function findallcats2 (){
				$.ajax({
				    type : "post",
				    url : "/store/AdminCategoryServlet?method=findAllCats", //此次url改为真正需要的url
				    dataType:"json",
				    error:function() {
						alert("error");
					},
				    success : function(data, status) {
				    	if($("select[id='updateselect']>option").length<=1){
				    		var data2 = eval(data);
					    	$.each(data2, function(index, item) {
					    		$("#updateselect").append(  //此处向select中循环绑定数据
									    '<option value="'+item.cid+'"> '+ item.cname+' </option>');
					    	}); 
				    		
				    	}
				    	
				    
				    }
				});
			} 
		 	
			
			

			
			function save(){
				$('#formAdd').form({    
				    url:"/store/AdminProductServlet?method=addProduct",    
				    success:function(data){    
				    	var jsonData = eval("("+data+")");
				       $.messager.show({
				    		title:'提示消息',
				    		msg:jsonData.msg,
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
				findallcats2();
				$('#formUpdate').form('load',"/store/AdminProductServlet?method=findProductByPid&pid="+id+"");
				
			}
			
			
			function update(){
				$('#formUpdate').form({    
				    url:"/store/AdminProductServlet?method=updateProduct",    
				    success:function(data){ 
				    	var jsonData = eval("("+data+")");
				       $.messager.show({
				    		title:'提示消息',
				    		msg:jsonData.msg,
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
				$.post("/store/AdminProductServlet?method=deleteProduct",{"pid":id},function(data){
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

	<!-- 添加商品的表单，默认是隐藏的 -->
	<div id="winAdd" class="easyui-window" title="添加商品"
		style="width: 600px; height: 400px"
		data-options="iconCls:'icon-save',modal:true,closed:true">

		<form id="formAdd" method="post" enctype="multipart/form-data" >
			<table cellSpacing="1" cellPadding="5" width="100%" align="center"
				bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26"><STRONG>添加商品</STRONG></td>
				</tr>

				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品名称：</td>
					<td class="ta_01" bgColor="#ffffff"><input type="text"
						name="pname" value="" id="userAction_save_do_logonName" class="bg" />
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						是否热门：</td>
					<td class="ta_01" bgColor="#ffffff"><select name="is_hot">
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						市场价格：</td>
					<td class="ta_01" bgColor="#ffffff"><input type="text"
						name="market_price" value="" id="userAction_save_do_logonName"
						class="bg" /></td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商城价格：</td>
					<td class="ta_01" bgColor="#ffffff"><input type="text"
						name="shop_price" value="" id="userAction_save_do_logonName"
						class="bg" /></td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品图片：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3"><input
						type="file" name="pimage" /></td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						所属的分类：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3"><select
						name="cid" id="select"  onclick="findallcats()" >
							<option value="-1">--请选择--</option>
							<!-- <option value="1">类别</option> -->
							
					</select></td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品描述：</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3"><textarea
							name="pdesc" rows="5" cols="30"></textarea></td>
				</tr>
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="button" id="userAction_save_do_submit" value="确定" onclick="save()"
							class="button_ok">&#30830;&#23450;</button> <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="button_cancel"
							>&#37325;&#32622;</button> <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)"
						value="返回" /> <span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</div>



	<!-- 修改商品的表单，默认是隐藏的 -->
	<div id="winUpdate" class="easyui-window" title="修改商品"
		style="width: 640px; height: 400px"
		data-options="iconCls:'icon-save',modal:true,closed:true">
		
				<form  id="formUpdate"   method="post" enctype="multipart/form-data">
			<input type="hidden" name="pid" >
		<!-- 	<input type="hidden" name="pimage" > -->
			
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>编辑商品</STRONG>
						</strong>
					</td>
				</tr>

				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品名称：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="pname"  id="userAction_save_do_logonName" class="bg"/>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						是否热门：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						
						<select name="is_hot">
							<option value="1" >是</option>
							<option value="0" >否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						市场价格：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="market_price"  id="userAction_save_do_logonName" class="bg"/>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商城价格：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="shop_price" id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品图片：
					</td>
					<td class="ta_01" bgColor="#ffffff" >
						<input type="file" name="upload" />
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
					
					</td>
					<td class="ta_01" bgColor="#ffffff">
					
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						所属的二级分类：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<select name="cid"  id="updateselect" >
							<!-- <option value="1">手机数码</option>
							<option value="2">电脑办公</option>
							<option value="3">家具家居</option>
							<option value="4">鞋靴箱包</option>
							<option value="5">图书音像</option>
							<option value="6">母婴孕婴</option> -->
						</select>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						商品描述：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<textarea name="pdesc" rows="5" cols="30"></textarea>
					</td>
				</tr>
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="button" id="userAction_save_do_submit" value="确定" class="button_ok"  onclick="update()" >
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
		
		
		</div>
		
		




<!-- 	<div id="winUpdate" class="easyui-window" title="修改分类"
		style="width: 300px; height: 200px"
		data-options="iconCls:'icon-save',modal:true,closed:true">

		<form id="formUpdate" method="post">
			<input type="hidden" name="cid" />
			<TABLE cellSpacing=0 cellPadding=5 border=0>
				<TR>
					<td>类别名称：</td>
					<td><INPUT class=textbox id=sChannel2 style="WIDTH: 180px"
						maxLength=50 name="cname"></td>

				</TR>
				<tr>
					<td rowspan=2>
						<button id="customerBtn" type="button" onclick="update()">保存</button>
					</td>
				</tr>
			</TABLE>
		</form>
	</div> -->
	
	
</body>
</HTML>

