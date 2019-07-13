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
				    url:'/store/AdminUserServlet?method=findAllUsers',    
				     fitColumns:true, 	
				    autoRowHeight:true,  
				    columns:[[    
				        {field:'username',title:'用户名',width:30},
				        {field:'password',title:'密码',width:30},
				        {field:'name',title:'昵称',width:30},
				        {field:'email',title:'电子邮箱',width:30},
				        {field:'telephone',title:'电话',width:30},
				        {field:'birthday',title:'生日',width:30},
				        {field:'sex',title:'性别',width:30},
				      {field:'state',title:'状态',width:30 ,formatter: function(value,row,index){
				        	if (row.state==1){
								return "已激活";
							} else {
								return "未激活";
							}
				        	}
				        }, 
				        {field:'xxx',title:'操作',width:40,formatter: function(value,row,index){
							return '<a href="#" onclick="edit(&quot;'+row.uid+'&quot;)">编辑</a> | <a href="#" onclick="del(&quot;'+row.uid+'&quot;)">删除</a>';
						}
					}
				    ]],
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
				    url:"/store/AdminUserServlet?method=adduser",    
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
				$('#formUpdate').form('load',"/store/AdminUserServlet?method=findUserByUid&uid="+id+"");
			}
			
			
			function update(){
				$('#formUpdate').form({    
				    url:"/store/AdminUserServlet?method=updateCategory",    
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
				$.post("/store/AdminUserServlet?method=deleteUser",{"uid":id},function(data){
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
	<div id="winAdd" class="easyui-window" title="添加用户"
		style="width: 500px; height: 300px"
		data-options="iconCls:'icon-save',modal:true,closed:true">

		<form id="formAdd" method="post"  >
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>添加用户</STRONG>
						</strong>
					</td>
				</tr>

				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						用户名称：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="username"  id="userAction_save_do_logonName" class="bg"/>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						密码：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="password"  id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						真实姓名：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="name"  id="userAction_save_do_logonName" class="bg"/>
						</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						邮箱：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="email" id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						电话：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="phone"  id="userAction_save_do_logonName" class="bg"/>
						</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						地址：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="addr"  id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
			
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="button" id="userAction_save_do_submit" value="确定" onclick="save()"  class="button_ok">
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
	
	
		<!-- 修改用户的表单，默认是隐藏的 -->
			<div id="winUpdate" class="easyui-window" title="修改用户"
		style="width: 640px; height: 400px"
		data-options="iconCls:'icon-save',modal:true,closed:true">
		
				<form  id="formUpdate"   method="post">
				<input type="hidden" name="uid" >
				<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>编辑用户</STRONG>
						</strong>
					</td>
				</tr>

				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						用户名称：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="username"  id="userAction_save_do_logonName" class="bg"/>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						密码：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="password"  id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						昵称 ：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="name"  id="userAction_save_do_logonName" class="bg"/>
						</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						邮箱：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="email" id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						电话：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="phone"  id="userAction_save_do_logonName" class="bg"/>
						</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						地址：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="addr"  id="userAction_save_do_logonName" class="bg"/>
					</td>
				</tr>
			
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="button" id="userAction_save_do_submit" value="确定" onclick="update()"  class="button_ok">
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
	
	
</body>

</HTML>

