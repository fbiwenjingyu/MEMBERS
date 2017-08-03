<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<c:url  value='/static/js/validate.js' />" ></script>
<script type="text/javascript" src="<c:url  value='/static/js/auth.js' />" ></script>
</head>
<body>
<div id="toolbar">
<form id="list" action="opinion/opinionList">
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
		<tr>
			<td>反馈内容：</td>
			<td><input type="text" id="phone" name="comtent"><input style="display:none"></td>
			<td id="nameTd">手机号：</td>
			<td><input type="text" id="comtent" name="responsePhone"><input style="display:none"></td>
			<td><button id="queryBtn" type="button" class="button">查询</button></td>
			<td><button id="clearBtn" type="button" class="button">清空</button></td>
		</tr>
	   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">用户反馈列表</div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="removeRow('id');"><span class="menu11"></span>删除</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
$(function() {      
	$('#grid').datagrid({   
	    url:'opinionList', 
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ {width : '100', field : 'ck',checkbox:true},
	                 {width : '80',title : '序号',field : 'id'},
	                 {width : '120',title : '反馈人',field : 'responsePhone'},
	                 {width : '350',title : '反馈主题',field : 'title'},
	                 {width : '150',title : '反馈时间',field : 'time'},
	                 {width : '120',title : '是否有回复',field : 'isReply',align:'center',formatter:function(v,r){
	                	 if(v == 1){
                           return '已回复';                		 
	                	 }else{
	                	   return '未回复';
	                	 }
					 }},
	                 {width : '100',title : '操作',field : 'sid', align:'center',formatter:function(v,r){
						  return '<a style="color: blue;" href="javascript:void 0;" onclick="addReplay('+r.id+');">详情</a>';
					 }}]
	      ]
	}); 
});
//弹出反馈详情
function addReplay(id){
	showAdd('opinion\\opinionDetail\\'+id,900,540);
}

</script>
</html>