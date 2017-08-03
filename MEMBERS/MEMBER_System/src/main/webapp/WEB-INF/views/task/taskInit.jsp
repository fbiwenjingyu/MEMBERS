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
  <th>运行状态：</th>
  <td>
   <select name="runState">
    <option value="">全部</option>
    <option value="0">初始化</option>
    <option value="1">执行中</option>
    <option value="2">执行成功</option>
    <option value="3">执行失败</option>
   </select>
  </td>
  <th>任务类型：</th>
  <td>
   <select name="taskType">
    <option value="">全部</option>
    <option value="1">赠送博豆</option>
    <option value="2">更用户</option>
    <option value="3">计算管理</option>
    <option value="4">计算辅导</option>
   </select>
  </td>
  <td>开通日期：</td>
  <td><input id="startDate" name="startDate" type="datetime" class="easyui-datebox"></input>
   至 <input id="endDate" name="endDate"  type="datetime" class="easyui-datebox"/>
  </td>
  <td><button id="queryBtn" type="button" class="button">查询</button></td>
  <td><button id="clearBtn" type="button" class="button">清空</button></td>
 </tr>
   </table>
</div>
</form>
<div class="operate">
 <div class="om-panel-header">任监控列表</div>
 <div class="icon">
		<ul>
			<li><a href="#" onclick="addTask();"><span class="menu13"></span>添加</a></li>
		</ul>
 </div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
var _ctx = '<c:url value="/" />';
$(function() {
 $('#grid').datagrid({   
     url:'list', 
     pageSize :10,
     pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
  	 striped : true,
     rownumbers : true,
     pagination : true,
     toolbar : '#toolbar',
     columns : [[
                  {width : '200',title : '统计日期',field : 'collectDate'},
                  {width : '200',title : '任务名称',field : 'taskName'},
                  {width : '200',title : '执行日期',field : 'runDate'},
                  {width : '200',title : '运行状态',field : 'runState', formatter:function(v,r){
                   var obj = {
                     "0":"初始化",
                   "1":"执行中",
                   "2":"执行成功",
                   "3":"执行失败"
                   };
                   return obj[v];
                   
                  }},
                 {width : '120',title : '操作',field : 'id', align:'center',formatter:function(v,r){
                	 if(r.runState != 2){
                		 return '<a style="color: blue;" href="javascript:void 0;" onclick="reRun('+v+');void 0;">重新执行</a>';
                	 }else{
                		 return "成功执行,无需操作";
                	 }
						
					 }}
	            ]]
	}); 
});


function reRun(id){
	var tip="确认重新执行?";
	$.messager.confirm('提示:',tip,function(e){ 
		if(e){
			$.ajax({
				url:'reRun',
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
	});
}

function addTask(){
	$.dialog.open(_ctx+"task/showAddTask", {
		lock: true,
		width:450,
		height:300
	});
}
</script>
</html>