<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div id="toolbar">
<form id="list" action="list">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
	<tr>
		<td>用户编号：</td>
		<td><input type="text" id="userId" name="userId"></td>
		<td>审核状态：</td>
		<td><input type="combo" id="status" name="status"></td>
		</tr>
		<tr>
		<td>提交日期：</td>
		<td><input id="startDate" name="startDate" type="datetime" class="easyui-datebox">
			至 <input id="endDate" name="endDate"  type="datetime" class="easyui-datebox">
		</td>
		<td></td>
		<td><button id="queryBtn" type="button" class="button">查询</button>
		<button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">用户等级管理列表</div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showAdd('/userLevelManage/showAdd',600,250);"><span class="menu1"></span>用户等级调整</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {
	$('#grid').datagrid({   
	    url:'list', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [
	               [
	                {width : '120',title : '用户编号',field : 'userId'},
	                 {width : '120',title : '调整前等级 ',field : 'oldLevel',formatter:function(v,r){return JSON.parse('${levelMap}')[v]}},
	                 {width : '120',title : '调整后等级 ',field : 'newLevel',formatter:function(v,r){return JSON.parse('${levelMap}')[v]}},
	                 {width : '140',title : '提交时间 ',field : 'createTime'},
	                 {width : '140',title : '审核时间 ',field : 'authTime'},
	                 {width : '120',title : '状态 ',field : 'status',formatter:function(v,r){return JSON.parse('${statusMap}')[v]}},
	                 {width : '120',title : '操作',field : 'id', align:'center',formatter:function(v,r){
						 if(r.status==0){
							 return '<a style="color: blue;" href="javascript:void 0;" onclick="update('+v+',1);void 0;">通过</a> <a style="color: blue;" href="#" onclick="update('+v+',2);void 0;">拒绝</a>';
						 }else if(r.status==2){
							 return '<a style="color: blue;" href="javascript:void 0;" onclick="deleteInfo('+v+')">删除</a>';
						 }else{
							 return '-';
						 }
					 }}
	                 ]
	    		]
	});
	$('#status').combobox({  
    	data:JSON.parse('${status}'),
    	editable:false,
    	panelHeight:'auto'
    }); 
});


function update(id,status){
	var tip="确认审核通过?";
	if(status==2){
		tip="确定拒绝审核?";
	}
	$.messager.confirm('提示:',tip,function(e){ 
		if(e){
			$.ajax({
				url:'update',
				dataType:'json',
				data:{'id':id,'status':status},
				success:function(data){
					if(data.success){
						refreshGrid("grid");
					}else{
						$.messager.alert('提示:',data.msg,'warning'); 
					}
				},
				error:function(req,err,ecp){
					$.messager.alert('提示:','系统异常','error'); 
				}
			});
		}
	});
}

function deleteInfo(id){
	$.ajax({
		url:'delete',
		dataType:'json',
		data:{'id':id},
		success:function(data){
			if(data.success){
				refreshGrid("grid");
			}else{
				$.messager.alert('提示:',data.msg,'warning'); 
			}
		},
		error:function(req,err,ecp){
			$.messager.alert('提示:','系统异常','error'); 
		}
	});
}
</script>
</html>