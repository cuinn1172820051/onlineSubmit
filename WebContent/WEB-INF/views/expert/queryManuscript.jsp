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
	        url:"get_list_by_expert?t="+new Date().getTime(),
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
 		      	{field:'state',title:'稿件状态',width:60,styler:cellStyler},
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
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	    }); 
	  	//搜索按钮
	  	$("#search-btn").click(function(){
	  		$('#dataList').datagrid('reload',{
	  			state:$("#select option:selected").text()
	  		});
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
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label>请选择查询类别:</label>
		&nbsp;&nbsp;&nbsp;
		<select id="select" name="state" style="width:120px;height:28px;cursor:pointer;">
					<option value="0">显示所有稿件</option>
					<option value="1">已审核</option>
	    			<option value="2">已通过</option>
	    			<option value="3">未通过</option>
	    			<option value="4">待审核</option>
		    </select>
		    &nbsp;&nbsp;&nbsp;
		<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
	</div>
</body>
</html>