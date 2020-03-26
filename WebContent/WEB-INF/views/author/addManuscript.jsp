<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>用户列表</title>
	<link href="../h-ui/css/H-ui.login.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
	<script type="text/javascript" src="../easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	 	function submitForm(){
			/*$('#ff').form('submit');*/
			var value = $("#select option:selected").text();
			console.log(value);
	 		if($("#file_upload").filebox("getValue") == ''){
	  			$.messager.alert("消息提醒","请选择文件!","warning");
	  			return;
	  		}
			$("#fileForm").submit();
		}
	 	function uploaded(e){
			var data = $("#file_target").contents().find("body pre").text();//获取frames框架内的文档的内容
			if(data == ''){
				return;
			}
			data = JSON.parse(data);
			if(data.type == "success"){
				$.messager.alert("消息提醒",data.msg,"info");
			}else{
				$.messager.alert("消息提醒",data.msg,"warning");
			}
	 	}
	 	//清空表单
		function clearForm(){
			$('#fileForm').form('clear');
		}
	</script>
</head>
<body>
	<!-- 添加稿件 -->
	<!-- 
		<form id="ff" method="post">
			<table id="dataList" cellspacing="10" cellpadding="0"> 
			    <tr><td><label>作者姓名:</label> </td><td><input class="easyui-textbox" type="text" name="author_name" data-options="required:false"/></td></tr>
			    <tr><td><label>稿件标题:</label> </td><td><input class="easyui-textbox" name="title" style="width:100%;height:32px"/></td></tr>
			    <tr><td>稿件摘要:</td><td><textarea rows="15" cols="79" name="summary"></textarea></td></tr>
			    <tr><td>投稿日期:</td><td><input class="easyui-datebox" name="submit_time"></input></td></tr>
			</table> 
		</form> 
	-->
	<!-- 上传稿件表单 -->
	<form id="fileForm" action="/onlineSubmit/author/upload_file" enctype="multipart/form-data" method="post" target="file_target">
		<table id="dataList" cellspacing="10" cellpadding="8">
			<tr><td><label>作者姓名:</label> </td><td><input class="easyui-textbox" type="text" name="author_name" data-options="required:false"/></td></tr>
			<tr><td><label>稿件标题:</label> </td><td><input class="easyui-textbox" name="title" style="width:100%;height:32px"/></td></tr>
		    <tr><td>稿件摘要:</td><td><textarea rows="15" cols="80" name="summary"></textarea></td></tr>
		    <tr><td>稿件类别:</td><td>
		    <select id="select" name="nav_id" style="width:100px;">
		    	<c:forEach items="${dataList}" var="nav">
	    			<option value="${nav.nav_id }">${nav.nav_name}</option>
	    		</c:forEach>
		    </select></td></tr>
			<tr><td>添加附件:</td><td><input id="file_upload" class="easyui-filebox" name="manuscript_file" data-options="prompt:'选择一个文件'" style="width:500px;"/></td></tr>
		</table>
	</form>
	<div style="text-align:center;padding:5px;margin-right:500px;">
   		<a href="javascript:void(0)" class="easyui-linkbutton" style="margin-right:30px;width:60px;" onclick="submitForm()">提交</a>
   		<a href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;" onclick="clearForm()">重置</a>
	</div>
	<!-- 提交表单处理iframe框架 -->
	<iframe id="file_target" name="file_target" onload="uploaded(this)" style="display:none;"></iframe> 
</body>
</html>