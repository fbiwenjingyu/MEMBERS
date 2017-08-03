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
		<td><input type="text" id="memberNo" name="memberNo"></td>
		<td>充值日期：</td>
		<td><input id="startDate" name="startDate" type="datetime" class="easyui-datebox"></input>
			至 <input id="endDate" name="endDate"  type="datetime" class="easyui-datebox"/>
		</td>
		<td>充值类型: </td>
		<td><input type="combo" id="forward" name="forward"></td>
	</tr>
	<tr>
		<td>账户类型：</td>
		<td><input type="combo" id="accountType" name="accountType"></td>
		<td>充值方式：</td>
		<td><input type="combo" id="way" name="way"></td>
		<td colspan="2">
			<button id="queryBtn" type="button" class="button">查询</button>
			<button id="clearBtn" type="button" class="button">清空</button>
		</td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">充值记录列表</div>
	<div class="icon">
		<ul>
			<li><a href="#" onclick="add();"><span class="menu1"></span>充值</a></li>
			<li><a href="#" onclick="exportExcel();"><span class="menu2"></span>导出</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
<form  action="#" id="exportForm" method="post"></form>
</body>
<script type="text/javascript">
$(function() {
	$('#grid').datagrid({   
	    url:'list', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ 
	                 {width : '120',title : '用户编号',field : 'MEMBER_NO'},
	                 {width : '100',title : '账户类型',field : 'ACCOUNT_TYPE',formatter:function(v,r){return JSON.parse('${accountType}')[v];}},
	                 {width : '100',title : '充值数量',field : 'AMOUNT',formatter:function(v,r){
	                	 if(r.ACCOUNT_TYPE=="1"){
	                		 return (v/100).toFixed(2);
	                	 }else{
	                		 return v;
	                	 }
	                 }},
	                 {width : '90',title : '充值类型',field : 'FORWARD',formatter:function(v,r){return JSON.parse('${rechargeForward}')[v];}},
	                 {width : '90',title : '充值方式',field : 'WAY',formatter:function(v,r){return JSON.parse('${rechargeWay}')[v];}},
	                 {width : '80',title : '状态',field : 'STATUS',formatter:function(v,r){return JSON.parse('${rechargeStatus}')[v];}},
	                 {width : '150',title : '充值日期',field : 'CREATE_TIME'},
	                 {width : '100',title : '操作员',field : 'OPERATOR'},
	                 {width : '200',title : '备注',field : 'MEMO'}
	            	]
	    		]
	});
	
	$('#accountType').combobox({  
    	data:JSON.parse('${accountTypeCombo}'),
    	editable:false,
    	panelHeight:'auto'
    }); 
	
	$('#way').combobox({  
    	data:JSON.parse('${rechargeWayCombo}'),
    	editable:false,
    	panelHeight:'auto'
    }); 
	
	$('#forward').combobox({  
    	data:JSON.parse('${rechargeForwardCombo}'),
    	editable:false,
    	panelHeight:'auto'
    }); 
});

function add(){
// 	parent.openChildTab(ctx+"recharge/toRecharge","recharge1","货币充值");
	$.dialog.open('<c:url value = "/recharge/toRecharge" />', {
		lock: true,
		width:700,
		height:300
	});
}

//导出Excel表格
function exportExcel(){
	var params=$("#list").serialize();
	$("#exportForm").attr("action","export?"+params);
	$("#exportForm").submit();
}

</script>
</html>