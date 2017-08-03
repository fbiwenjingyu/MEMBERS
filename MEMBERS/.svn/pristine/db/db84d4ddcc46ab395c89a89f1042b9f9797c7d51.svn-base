<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div id="toolbar">
<form id="list" action="teamList">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
	<tr>
		<td>邀请人编号：</td>
		<td><input type="text" id="userId" name="userId"></td>
		<input type="text" style="display: none;">
		<td><button id="queryBtn" type="button" class="button">查询</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">邀请用户列表</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
var rAmount=parseInt('${rAmount}');//激活用户所需金额
$(function() {
	$('#grid').datagrid({   
	    url:'teamList', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [
	               [
	                {width : '120',title : '邀请人编号',field : 'recommendNo'},
	                 {width : '120',title : '用户编号 ',field : 'accountNo'},
	                 {width : '140',title : '开通时间 ',field : 'activateTime'},
	                 {width : '120',title : '用户等级 ',field : 'level',formatter:function(v,r){return JSON.parse('${levelMap}')[v]}},
	                 {width : '120',title : '团队业绩 ',field : 'teamMoney' , formatter:function(v,r){if(v == null){ return 0.00;}else{ return fmoney(v);}}},
	                 {width : '120',title : '个人业绩  ',field : 'myMoney' , formatter:function(v,r){if(v == null){ return 0.00;}else{ return fmoney(v);}}}
	                 ]
	    		]
	});
});

//格式化金额
//优化负数格式化问题
function fmoney(s, n) {
  n = n > 0 && n <= 20 ? n : 2;
  f = s < 0 ? "-" : ""; //判断是否为负数
  s = parseFloat((Math.abs(s) + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";//取绝对值处理, 更改这里n数也可确定要保留的小数位
  var l = s.split(".")[0].split("").reverse(),
  r = s.split(".")[1];
  t = "";
  for(i = 0; i < l.length; i++ ) {
     t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
  }
  return f + t.split("").reverse().join("") + "." + r.substring(0,2);//保留2位小数  如果要改动 把substring 最后一位数改动就可
}

</script>
</html>