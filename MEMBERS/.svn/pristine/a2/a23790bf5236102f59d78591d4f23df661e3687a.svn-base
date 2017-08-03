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
		<td><input type="text" id="accountNo" name="accountNo"></td>
		<td>创建日期：</td>
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
	<div class="om-panel-header">用户账户记录列表</div>
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
		singleSelect : true,
		toolbar : '#toolbar',
	    columns : [[ 
	                {width : '120',title : '用户编号',field : 'ACCOUNT_NO'},
	                {width : '110',title : '用户姓名',field : 'REAL_NAME'}, 
	     		    {width : '100',title : '现金币(元)',field : 'BALANCE',formatter:function(v){
	     		    		if(v){
		     		    		return (v/100).toFixed(2);
	     		    		}
	     		    		return 0;
	     		    	}
	                },
	     		    {width : '100',title : '博豆',field : 'SHOPINGSCORE',formatter:function(v){
		     		    	if(v){
		     		    		return v;
		     		    	}
		     		    	return 0;
	     		    	}
	     		    },
	     		    {width : '100',title : '积分',field : 'GENERALIZESCORE',formatter:function(v){
		     		    	if(v){
		     		    		return v;
		     		    	}
		     		    	return 0;
	     		    	}
	     		    },
	     		   {width : '100',title : '股权',field : 'SHAREHOLDING' ,formatter:function(v){
		     		    	if(v){
		     		    		return v;
		     		    	}
		     		    	return 0;
    		    		} 
    		   		 },
	     		    /* {width : '80',title : '用户类型',field : 'TYPE',formatter:function(v){return JSON.parse('${typeMap}')[v];}}, */
	                {width : '150',title : '创建日期',field : 'CREATE_TIME'},
	                {width : '100',title : '操作',field : 'ID',formatter:function(v,r){
						 return '<a href="<c:url value='/flow/detailInit'/>?uId='+v+'&uAN='+r.ACCOUNT_NO+'" style="color:blue;">查看流水明细</a>';
				 	}}
	           	]]
	}); 
});
</script>
</html>