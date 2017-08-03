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
		<td>公告发布日期：</td>
		<td><input id="startDate" name="startDate" type="datetime" class="easyui-datebox"></input>
			至 <input id="endDate" name="endDate"  type="datetime" class="easyui-datebox"/>
		</td>
		<td><button id="queryBtn" type="button" class="button">查询</button></td>
		<td><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">公告列表</div>
	<div class="icon">
		<ul>
			<li><a href="#" onclick="add();"><span class="menu1"></span>发布新公告</a></li>
		</ul>
	</div>
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
		toolbar : '#toolbar',
	    columns : [[ 
	                 {width : '200',title : '公告标题',field : 'TITLE'},
	                 {width : '150',title : '发布时间',field : 'DEPLOY_TIME'},
	                 {width : '100',title : '发布人',field : 'DEPLOY_BY'},
					 {width : '120',title : '操作',field : 'ID', align:'center',formatter:function(v,r){
						 return '<a style="color: blue;" href="javascript:void 0;" onclick="update('+v+',0);">修改</a> <a style="color: blue;" href="javascript:void 0;" onclick="update('+v+',1);">删除</a> <a style="color: blue;" href="javascript:void 0;" onclick="update('+v+',2);">置顶</a>';
					 }}
	            	]
	    		]
	});
});

function update(id,flag){
	if(flag==0){
		$.dialog.open('<c:url value = "/notice/toEdit" />?noticeId='+id, {
			lock: true,
			width:1000,
			height:600
		});
	}else{
		var tip=null;
		var obj={};
		obj.id=id;
		if(flag==1){
			obj.isDelete=1;
			tip="确认删除?";
		}else if(flag==2){
			obj.isTop=1;
			tip="确认置顶?";
		}
		if(tip==null){
			return;
		}
		$.messager.confirm('提示:',tip,function(e){ 
			if(e){
				$.ajax({
					url:'edit',
					dataType:'json',
					data:obj,
					success:function(data){
						if(data.code==0){
							refreshGrid("grid");
						}else{
							$.messager.alert('提示:',data.msg,'warning'); 
						}
					},
					error:function(req,err,ecp){
						$.messager.alert('提示:',err,'error'); 
					}
				});
			}
		});
	}
}

function add(){
	$.dialog.open('<c:url value = "/notice/toEdit" />', {
		lock: true,
		width:1000,
		height:600
	});
}
</script>
</html>