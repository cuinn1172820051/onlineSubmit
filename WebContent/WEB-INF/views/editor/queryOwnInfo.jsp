<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
	<script type="text/javascript" src="../easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	$(function() {	
		var table;
		//datagrid初始化 
	    $('#dataList').datagrid({ 
	        title:'个人信息列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"getOwnInfoByEditor?t="+new Date().getTime(),
	        idField:'id', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rownumbers:false,//行号 
	        sortName:'id',
	        sortOrder:'DESC',
	        remoteSort: true,
	        columns: [[  
	        		{field:'chk',checkbox: true,width:50},
	        		{field:'editor_id',title:'编辑ID',width:50,sortable:true},
	        		{field:'username',title:'用户名',width:150},
	        		{field:'password',title:'密码',width:150},
	        		{field:'editor_name',title:'作者姓名',width:100},
	        		{field:'sex',title:'性别',width:35}
	 			]],
	        toolbar: "#toolbar"
	    }); 
	    	//{field:'author_name',title:'作者姓名',width:60},
	      	//{field:'state',title:'稿件状态',width:60},
	     	//{field:'submit_time',title:'投稿日期',width:150,sortable:true},
	   		//{field:'summary',title:'稿件摘要',width:300},
	     	//{field:'title',title:'稿件标题',width:300},
	    //设置分页控件 
	    var p = $('#dataList').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    }); 
	    
	    //修改
	    $("#edit").click(function(){
	    	table = $("#editTable");
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	if(selectRows.length != 1){
            	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
            } else{
		    	$("#editDialog").dialog("open");
            }
	    });
	  	
	  	//编辑用户信息
	  	$("#editDialog").dialog({
	  		title: "修改个人信息",
	    	width: 350,
	    	height: 300,
	    	iconCls: "icon-edit",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'提交',
					plain: true,
					iconCls:'icon-edit',
					handler:function(){
						var validate = $("#editForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							var data = $("#editForm").serialize();
							$.ajax({
								type: "post",
								url: "editOwnInfoByEditor",
								data: data,
								dataType:'json',
								success: function(data){
									if(data.type == "success"){
										$.messager.alert("消息提醒",data.msg,"info");
										//关闭窗口
										$("#editDialog").dialog("close");
										
										//重新刷新页面数据
							  			$('#dataList').datagrid("reload");
							  			$('#dataList').datagrid("uncheckAll");
										
									} else{
										$.messager.alert("消息提醒",data.msg,"warning");
										return;
									}
								}
							});
						}
					}
				},
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值
				$("#editor_id").val(selectRow.editor_id);
				$("#edit_username").textbox('setValue', selectRow.username);
				$("#edit_password").textbox('setValue', selectRow.password);
				$("#editor_name").textbox('setValue', selectRow.editor_name);
			}
	    });
	});
	</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0"> 
	</table>
	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="position:absolute;left:515px;top:28px;z-index:999;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
	</div>
	<!-- 修改窗口 -->
	<div id="editDialog" style="padding: 10px">
    	<form id="editForm" method="post">
    		<input type="hidden" name="editor_id" id="editor_id">
	    	<table id="editTable" border=0 cellpadding="8" >
	    		<tr >
	    			<td>用户名:</td>
	    			<td>
	    				<input id="edit_username"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="username" data-options="required:true, missingMessage:'请填写用户名'"  />
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td><input id="edit_password" style="width: 200px; height: 30px;" class="easyui-textbox" type="password" name="password" data-options="required:true, missingMessage:'请填写密码'"  /></td>
	    		</tr>
	    		<tr>
	    			<td>姓名:</td>
	    			<td><input id="editor_name" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="editor_name" data-options="required:true, missingMessage:'请填写姓名'"  /></td>
	    		</tr>
	    		<tr>
	    			<td>性别:</td>
	    			<td>
						<input type="radio" id="radio-1" name="sex" checked value="男" />
						<label for="radio-2">男</label>
						<input type="radio" id="radio-2" name="sex" value="女" />
						<label for="radio-1">女</label>
					</td>
	    		</tr>
	    	</table>
	    </form>
	</div> 
</body>
</html>