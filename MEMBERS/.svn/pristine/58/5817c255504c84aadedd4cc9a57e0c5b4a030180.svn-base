<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div id="toolbar">
<form id="list" action="inOutDetailList">
<input type="text" style="display: none;"/>
<input id="collectDate" name="collectDate"  type="hidden"/>
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
	<tr>
		<td>用户编号：</td>
		<td><input type="text" id="accountNo" name="accountNo"></td>
		<td><button id="queryBtn" type="button" class="button">查询</button></td>
		<td><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">系统收支明细-【<fmt:formatDate value="${collectDate}" pattern="yyyy-MM-dd" />】</div>
	<div class="icon">
		<ul>
			<li><a href="${ctx}/flow/inOutInit"><span class="menu6"></span>返回</a></li>
		</ul>
	</div>
	
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {
	
	var cd='<fmt:formatDate value="${collectDate}" pattern="yyyy-MM-dd" />';
	//console.log(cd);
	$("#collectDate").val(cd);
	$('#grid').datagrid({   
	    url:'inOutDetailList?collectDate='+cd, 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		toolbar : '#toolbar',
	    columns : [[ 
	     		    {width : '140',title : '用户编号',field : 'accountNo'},
	     		    {width : '120',title : '直推奖励',field : 'rewardAmount', formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v/100);}}},
	     		    {width : '120',title : '管理奖励',field : 'manageAmount', formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v/100);}}},
	     		    {width : '120',title : '辅导奖励',field : 'coachAmount', formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v/100);}}},
	                {width : '120',title : '购物积分',field : 'beansNum'}, 
	     		    {width : '120',title : '推广积分',field : 'pointAmount' },
	                {width : '120',title : '个人业绩',field : 'myMoney',formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v);}}},
	     		    {width : '120',title : '团队业绩',field : 'teamMoney',formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v);}}}
	           	]]
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