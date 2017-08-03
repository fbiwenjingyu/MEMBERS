<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<c:url  value='/static/js/validate.js' />" ></script>
<script type="text/javascript" src="<c:url  value='/static/js/opinion.js' />" ></script>
<style>
  body{color:#333;}
  .tip{margin:10px 0 5px 40px}
  .replyDiv{height:30px; weight:800px; background-color:#01CCC0; font-size: 17px; line-height: 30px;margin-left: 20px;margin-top:30px;}
  .fontDiv{ border-bottom: 1px solid #ddd; weight:800px; height:40px; line-height: 40px;font-size: 12px;margin-left: 20px;
  padding:0 0 0 20px;}
  .whoDiv{right: 0px; text-align:right;font-size: 12px; margin-top:3px;}
  .addDiv{margin:10px 0 0 20px}
  #noReply{margin-left:40px;}
  #sureSubmit{
               border:1px solid #01ccc0;color:#fff;line-height:22px; float:right;
               width:80px;background: #01ccc0;text-align: center; margin-right:20px;margin-top:5px;
  }
 .operate{height:37px;}
 .icon{margin:10px 20px 0 0;}
</style>
</head>
<body>
<div id="toolbar">
<form id="list" action="reward/rewardDetail">
<div class="operate">
	<div class="om-panel-header">用户奖励明细</div>
	<div class="icon">
	    <input type=hidden value="${id}" name="userId">
	    <input type="hidden" value="${date}"></input>
	    <select name="rewardType" onchange="changeRewardType()" id="inoutType">
	       <option value=0>请选择奖项的类别</option>
	       <option value="102">管理奖</option>
	       <option value="103">辅导奖</option>
	       <option value="101">直推奖</option>
	    </select>
		<ul>
    		
		</ul>
	</div>
</div>
</form>
</div>

<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {      
	$('#grid').datagrid({   
	    url:ctx+'reward/rewardDetail?userId=${id}&date='+'${date}', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ 
	                 {width : '120',title : '奖项名称',field : 'rewardType',formatter:function(v,r){
	                	   if(v == 101){
	                		   return '直推用户现金奖励';
	                	   }else if(v == 102){
	                		   return '管理现金奖励';
	                	   }else if(v == 103){
	                		   return '辅导奖励';
	                	   }
	                 }},
	                 {width : '120',title : '奖励来源',field : 'accountNo'},
	                 {width : '120',title : '奖金金额',field : 'rewardAmount', formatter:function(v,r){
						  return v/100;
					 }},
	                 {width : '150',title : '结算时间',field : 'collectDate'},
	                 {width : '120',title : '发放状态',field : 'time',formatter:function(v,r){return '已发放';}}]
	                
	      ]
	}); 
});
//根据奖励类型查询
function changeRewardType(){
	$('#grid').datagrid('load',getFormData(document.forms[0]));
}
</script>
</html>