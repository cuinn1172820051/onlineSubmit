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
	        title:'稿件信息列表', 
	        iconCls:'icon-more',//图标 
	        border: true, 
	        collapsible:false,//是否可折叠的 
	        fit: true,//自动大小 
	        method: "post",
	        url:"get_list_by_editorDoubleReview?t="+new Date().getTime(),
	        idField:'manuscript_id', 
	        singleSelect:false,//是否单选 
	        pagination:true,//分页控件 
	        rownumbers:true,//行号 
	        sortName:'id',
	        sortOrder:'DESC',
	        remoteSort: true,
	        columns: [[  
	        	{field:'chk',checkbox: true,width:50},
 		        {field:'author_name',title:'作者姓名',width:60},
 		      	{field:'state',title:'稿件状态',width:60,align:'center',styler:cellStyler},
 		     	{field:'submit_time',title:'投稿日期',width:150,sortable:true},
 		   		{field:'summary',title:'稿件摘要',width:300},
 		     	{field:'title',title:'稿件标题',width:300}
	 		]], 
	        toolbar: "#toolbar"
	    }); 
		function cellStyler(value,row,index){
			if(row.state == "未通过"){
				return 'color:red;';
			}if(row.state == "已通过"){
				return 'color:#006400;';
			}
		}
	    //设置分页控件 
	    var p = $('#dataList').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    }); 
	    //审核
	    $("#edit").click(function(){
	    	table = $("#editTable");
	    	var selectRows = $("#dataList").datagrid("getSelections");
        	if(selectRows.length != 1){
            	$.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
            } else{
		    	$("#editDialog").dialog("open");
            }
	    });
	  //审核稿件
	  	$("#editDialog").dialog({
	  		title: "审核稿件信息",
	    	width: 840,
	    	height: 400,
	    	iconCls: "icon-edit",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//设置值
				$("#manuscript_id").val(selectRow.manuscript_id);
				$("#author_name").textbox('setValue', selectRow.author_name);
				$("#submit_time").textbox('setValue', selectRow.submit_time);
				$("#title").textbox('setValue', selectRow.title);
				$("#summary").textbox('setValue', selectRow.summary);
				$("#download").attr("href","../expert/download?filePath="+selectRow.filePath);
				$("#ok").attr("href","../editor/doubleReview?flag=true&manuscript_id="+selectRow.manuscript_id);
				$("#no").attr("href","../editor/doubleReview?flag=false&manuscript_id="+selectRow.manuscript_id);
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
		<a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">稿件复审</a>
	</div>
	<!-- 审核窗口 -->
	<div id="editDialog" style="padding: 10px;position:relative;">
    	<form id="editForm" method="post">
    		<input type="hidden" name="manuscript_id" id="manuscript_id">
	    	<table id="editTable" border=0 cellpadding="10" >
	    		<tr >
	    			<td>作者姓名:</td>
	    			<td>
	    				<input id="author_name"  class="easyui-textbox" style="width: 100px; height: 30px;" type="text" readonly="true" name="author_name"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>投稿日期:</td>
	    			<td><input id="submit_time" style="width: 150px; height: 30px;" class="easyui-textbox" type="text" readonly="true" name="submit_time"/></td>
	    		</tr>
	    		<tr>
	    			<td>稿件标题:</td>
	    			<td><input id="title" style="width: 500px; height: 30px;" class="easyui-textbox" type="text" readonly="true" name="title"/></td>
	    		</tr>
	    		<tr>
	    			<td>稿件摘要:</td>
	    			<td><input id="summary" style="width: 700px; height: 30px;" class="easyui-textbox" type="phone" readonly="true" name="summary"/></td>
	    		</tr>
	    		<tr>
	    			<td></td>
	    			<td><a id="download" class="easyui-linkbutton">附件下载</a></td>
	    		</tr>
	    	</table>
	    	<div id="buttonBox" style="position:absolute;right:50px;">
	    		<a id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" style="margin-right:20px;">通过</a><a id="no" class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true">退回</a>
	    	</div>
	    </form>
	</div>
</body>
</html>