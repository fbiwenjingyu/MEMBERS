<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div id="toolbar">
<form id="list" action="unOpenUserList">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
	<tr>
		<td>用户编号：</td>
		<td><input type="text" id="userId" name="userId"></td>
		<td>注册时间：</td>
		<td><input id="startDate" name="startDate" type="datetime" class="easyui-datebox">
			至 <input id="endDate" name="endDate"  type="datetime" class="easyui-datebox">
		</td>
		<td><button id="queryBtn" type="button" class="button">查询</button></td>
		<td><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">待开通用户列表</div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showAdd('/user/add',850,550);"><span class="menu1"></span>添加</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {
	$('#grid').datagrid({   
	    url:'unOpenUserList', 
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
	                 {width : '120',title : '注册金额',field : 'registerMoney',formatter:function(v,r){if(v == null){ return 0.00;}else{ return fmoney(v);}}},
	                 {width : '140',title : '注册时间 ',field : 'createTime'},
	                 {width : '140',title : '注册单数 ',field : 'regNum'},
	                 {width : '120',title : '用户等级 ',field : 'level',formatter:function(v,r){return JSON.parse('${levelMap}')[v]}},
	                 {width : '120',title : '服务中心编号',field : 'agentNo'},
	                 {width : '140',title : '操作',field : 'id', align:'center',formatter:function(v,r){
						return '<a style="color: blue;" href="javascript:void 0;" onclick="update(\'' + r.accountNo + '\','+v+',1);void 0;">空单激活</a>&nbsp&nbsp<a style="color: blue;" href="javascript:void 0;" onclick="update(\'' + r.accountNo + '\','+v+',2);void 0;">实单激活</a>&nbsp&nbsp<a style="color: blue;" href="#" onclick="deleteInfo('+v+');void 0;">删除</a>';
					 }}
	                 ]
	    		]
	});
});


function update(accountNo,id,type){
	var typeStr = (type == 1 ? "空单" : "实单");
	var tip="确认" + typeStr + "激活[" + accountNo + "]?";
	$.messager.confirm('提示:',tip,function(e){ 
		if(e){
			$.ajax({
				url:'update',
				dataType:'json',
				data:{'id':id,'type':type},
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

function deleteInfo(id){
	$.ajax({
		url:'delete',
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

function fmoney(s, n) {
	  n = n > 0 && n <= 20 ? n : 2;
	  f = s < 0 ? "-" : ""; //判断是否为负数
	  s = s
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