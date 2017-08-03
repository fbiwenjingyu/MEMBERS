<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div id="toolbar">
<form id="list" action="inOutList">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
	<tr>
		<td>结算日期：</td>
		<td><input id="startDate" name="startDate" type="datetime" class="easyui-datebox"></input>
			至 <input id="endDate" name="endDate"  type="datetime" class="easyui-datebox"/>
		</td>
		<td>
			<button id="queryBtn" type="button" class="button">查询</button>
		</td>
		<td><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">系统收支记录列表</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {
	
	$('#grid').datagrid({   
	    url:'inOutList', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		toolbar : '#toolbar',
	    columns : [[ 
	                {width : '80',title : '结算日期',field : 'collectDate', formatter:function(v){
	                		var date=new Date(v);
	                		return  date.getFullYear() +"-" + (date.getMonth()+1) +"-"+date.getDate();
	                	}
	                },
	                {width : '80',title : '直推奖励',field : 'rewardAmount', formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v/100);}}},
	     		    {width : '80',title : '管理奖励',field : 'manageAmount', formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v/100);}}},
	     		    {width : '80',title : '辅导奖励',field : 'coachAmount', formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v/100);}}},
	                {width : '70',title : '购物积分',field : 'beansNum'}, 
	     		    {width : '70',title : '推广积分',field : 'pointAmount' },
	     		    {width : '80',title : '实发',field : 'coachAmount1',formatter:function(v,r){
	     		    		return fmoney(r.rewardAmount/100+r.beansNum+r.pointAmount+r.manageAmount/100+r.coachAmount/100);
	     		    	}
	     		    },
	     		   {width : '80',title : '激活会员人数',field : 'vipNum'},
	     		   {width : '80',title : '充值金额',field : 'rechargeAmount', formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v/100);}}},
	     		   {width : '80',title : '充值博豆',field : 'rechargeBeans'},
	     		   {width : '80',title : '充值积分',field : 'rechargePoint'},
	     		   {width : '80',title : '提现申请金额',field : 'drawReq', formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v/100);}}},
	     		   {width : '80',title : '实际提现金额',field : 'drawAmount', formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v/100);}}},
	     		   {width : '80',title : '兑换数量',field : 'exchangeNum'},
	     		   {width : '80',title : '发货数量 ',field : 'sendNum'},
	     		   {width : '120',title : '韩流馆消费会员数量 ',field : 'hlgNum'},
	     		   {width : '110',title : '韩流馆消费的现金',field : 'hlgAmount', formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v/100);}}},
	     		   {width : '110',title : '韩流馆消费的积分 ',field : 'hlgBeans'},
	     		   {width : '120',title : '激活用户消费的现金',field : 'actUserAmount', formatter:function(v,r){if(!v){ return 0.00;}else{ return fmoney(v/100);}}},
	     		   {width : '120',title : '激活用户消费的积分 ',field : 'actUserPoint'},
	     		   {width : '120',title : '激活用户消费的博豆 ',field : 'actUserBeans'},
	                {width : '70',title : '操作',field : 'id',formatter:function(v,r){
						 return '<a href="<c:url value='/flow/inOutDetailInit'/>?collectDate='+r.collectDate+'" style="color:blue;">查看明细</a>';
				 	}}
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