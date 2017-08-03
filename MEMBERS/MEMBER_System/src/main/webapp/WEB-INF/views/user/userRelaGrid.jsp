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
<input type="text" style="display: none;">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
	<tr>
		<td>邀请人编号：</td>
		<td><input type="text" id=memberNo name="memberNo"></td>
		<td></td>
		<td>
			<button id="queryBtn" type="button" class="button">查询</button>
			<button id="backBtn" type="button" class="button" onclick="backTop();">返回顶层</button>
			<button id="clearBtn" type="button" class="button">清空</button>
		</td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">用户信息列表</div>
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
	    url:'${ctx}/user/queryUserRelaGrid', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[
					 {width : '100',title : '邀请人',field : 'RECOMMEND_NO'},
	                 {width : '100',title : '用户编号',field : 'ACCOUNT_NO',align:'center',formatter:function(v,r){
	                	 return '<a style="color: blue;" href="javascript:void 0;" onclick="down(\''+v+'\');">'+v+'</a>';
					 }},
	                 {width : '100',title : '用户手机',field : 'PHONE'},
	                 {width : '80',title : '用户姓名',field : 'REAL_NAME'},
	                 {width : '100',title : '注册金额（元）',field : 'REGISTER_MONEY'},
	                 {width : '80',title : '用户等级',field : 'LEVEL',formatter:function(v,r){return JSON.parse('${userLevel}')[v]}},
	                 {width : '80',title : '类型',field : 'TYPE',formatter:function(v,r){return JSON.parse('${userType}')[v]}},
	                 {width : '140',title : '开通日期',field : 'ACTIVATE_TIME'},
	                 {width : '100',title : '团队业绩（元）',field : 'TEAM_MONEY'},
	                 {width : '100',title : '个人业绩（元）',field : 'MY_MONEY'}
	                 ]]
	}); 
});

function down(memberNo){
	$('#grid').datagrid('load',{memberNo:memberNo});
}

function backTop(){
	$("#memberNo").val("");
	$('#grid').datagrid('load',{memberNo:""});
}
</script>
</html>