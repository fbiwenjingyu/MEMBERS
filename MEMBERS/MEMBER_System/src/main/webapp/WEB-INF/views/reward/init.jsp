<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<c:url  value='/static/js/validate.js' />" ></script>
<script type="text/javascript" src="<c:url  value='/static/js/auth.js' />" ></script>
<style>
   .searchTable td #accountNo{width:150px;}
</style>
</head>
<body>
<div id="toolbar">
<form id="list" action="opinion/opinionList">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
		<tr>
			<td id="nameTd">用户编号：</td>
			<td><input type="text" id="accountNo" name="accountNo"><input style="display:none"></td>
			<td>结算日期：</td>
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
	<div class="om-panel-header">系统奖励列表</div>
	<div class="icon">
		<ul>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {      
	$('#grid').datagrid({   
	    url:'rewardList', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ 
	                 {width : '120',title : '用户编号',field : 'accountNo'},
	                 {width : '100',title : '用户姓名',field : 'userName'},
	                 {width : '100',title : '推广积分',field : 'pointAmount'},
	                 {width : '100',title : '管理奖',field : 'manageAmount', formatter:function(v,r){
						  return v/100;
					 }},
	                 {width : '100',title : '购物积分',field : 'beansNum'},
	                 {width : '100',title : '推荐奖励',field : 'rewardAmount', formatter:function(v,r){
						  return v/100;
					 }},
	                 {width : '100',title : '辅导奖励',field : 'coachAmount', formatter:function(v,r){
						  return v/100;
					 }},
	                 {width : '100',title : '实发',field : 'allReward', formatter:function(v,r){
						  return (v/100+r.pointAmount+r.beansNum);
					 }},
	                 {width : '150',title : '结算日期',field : 'collectDate',formatter:function(v,r){
	                	 return v.substr(0,10);
	                 }},
	                 {width : '100',title : '操作',field : 'sid', align:'center',formatter:function(v,r){
						  return '<a style="color: blue;" href="javascript:void 0;" onclick=\'showDetail('+r.userId+',"'+r.collectDate+'");\'>查看明细</a>';
					 }}]
	      ]
	}); 
});
//弹出反馈详情
function showDetail(id,date){
	var dt = date.substr(0,10);
	console.log(dt);
	showAdd('reward/rewardDetail/'+id+'/'+dt,780,440);
}
</script>
</html>