<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div id="toolbar">
<form id="list" action="detailList">
<input id="userId" name="userId" value="${userId }" type="hidden"/>
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
	<tr>
		<td>账户类型：</td>
		<td><input type="combo" id="accountType" name="accountType"></td>
		<td>流水日期：</td>
		<td><input id="startDate" name="startDate" type="datetime" class="easyui-datebox"></input>
			至 <input id="endDate" name="endDate"  type="datetime" class="easyui-datebox"/>
		</td>
		<td><button id="queryBtn" type="button" class="button">查询</button></td>
		<td>
			<button id="clearBtn" type="button" class="button">清空</button>
		</td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">用户流水记录列表-【${uAN }】</div>
	<div class="icon">
		<ul>
			<li><a href="${ctx}/flow/init"><span class="menu6"></span>返回</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {
	
	$('#grid').datagrid({   
	    url:'detailList?userId='+$("#userId").val(), 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		toolbar : '#toolbar',
	    columns : [[ 
	     		    {width : '100',title : '账户类型',field : 'ACCOUNT_TYPE',formatter:function(v,r){return JSON.parse('${accountTypeMap}')[v];}},
	                {width : '150',title : '业务类型',field : 'INOUT_TYPE',formatter:function(v,r){return JSON.parse('${accountMap}')[v];}},
	                {width : '100',title : '收入',field : 'AMOUNT1',formatter:function(v,r){
	               	 	var _amont=parseInt(r.AMOUNT);
	               	 	var _amoutDes="";
	               	 	if(_amont>=0){
	               	 		if (r.ACCOUNT_TYPE == 1) {
	               				_amoutDes='+'+ (r.AMOUNT / 100).toFixed(2) + '元';
	               			} else if (r.ACCOUNT_TYPE == 2) {
	               				_amoutDes='+'+ r.AMOUNT + '博豆';
	               			} else if (r.ACCOUNT_TYPE == 3) {
	               				_amoutDes='+'+ r.AMOUNT + '积分';
	               			} else {
	               				_amoutDes= 'undefined';
	               			}
	               	 		return '<b style="color: #02CDC1;">'+_amoutDes+'</b>';
	               	 	}else{
	               	 		return '-';
	               	 	}
	               	 }
	                },
	                {width : '100',title : '支出',field : 'AMOUNT2',formatter:function(v,r){
	               	 	var _amont=parseInt(r.AMOUNT);
	               	 	var _amoutDes="";
	               	 	if(_amont>=0){
	               	 		return '-';
	               	 	}else{
	               	 		if (r.ACCOUNT_TYPE == 1) {
	               				_amoutDes= (r.AMOUNT / 100).toFixed(2) + '元';
	               			} else if (r.ACCOUNT_TYPE == 2) {
	               				_amoutDes= r.AMOUNT + '博豆';
	               			} else if (r.ACCOUNT_TYPE == 3) {
	               				_amoutDes= r.AMOUNT + '积分';
	               			} else {
	               				_amoutDes= 'undefined';
	               			}
	               	 		return '<b style="color: #ff5722;">'+_amoutDes+'</b>';
	               	 	}
	               	 }
	                },
	                {width : '300',title : '业务描述',field : 'INOUT_DESC',formatter:function(v,r){
	                		if(parseInt(r.USER_TYPE) == 22){//如果是笨笨商城导入的数据
	                			return v;
	                		}else{
	                			var tt=parseInt(r.INOUT_TYPE);
	                			if(tt == 201){
	                				return v+',来自【系统奖励】';
	                			}else if(r.TRADE_NO && (tt == 101 ||tt == 102 ||tt == 103 ||tt == 112 ||tt == 114 ||tt == 115||tt == 121 ||tt == 124 ||tt == 127 ||tt == 216 ||tt == 218||tt == 224 ||tt == 226||tt == 228 || tt == 301 ||tt == 324 )){       
	                				return v+',来自用户【'+r.TRADE_NO +'】';
                				}else if(r.TRADE_NO && (tt == 110 ||tt == 111 ||tt == 113 ||tt == 120 ||tt == 123|| tt == 125 ||tt == 126 ||tt == 210 ||tt == 212 ||tt == 213 ||tt == 214 ||tt == 222 ||tt == 225|| tt == 227 ||tt == 310||tt == 312||tt == 313||tt == 314||tt == 325)){    
                					return v+',单号【'+r.TRADE_NO +'】';        
                				}else if(tt == 122){//现金账户转出
                					return v+',转给用户【'+r.TRADE_NO +'】';    
                				}               				
                				else{
                					return v;
                					
                				}
	                		}
	                	}
	                },
	                {width : '150',title : '流水日期',field : 'CREATE_TIME'},
	     		    {width : '110',title : '操作员',field : 'OPERATOR_NO'}
	           	]]
	}); 
	
    $('#accountType').combobox({  
    	data:JSON.parse('${accountTypeCombo}'),
    	editable:false,
    	panelHeight:'auto'
    }); 
});
</script>
</html>