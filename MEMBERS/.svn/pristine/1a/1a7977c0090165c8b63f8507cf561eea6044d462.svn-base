<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
.changetab{display:block;padding-left:10px;}
.changetab span{margin:10px 0;display:inline-block;width:50px;height:30px;line-height:30px;text-align:center;color:#666;float:left;}
.changetab span.on{background:#f5f5f5;}
</style>
</head>
<body>
<div id="toolbar">
<form id="list" action="list">
<input type="hidden" id="mulstatus" name="mulstatus" value="=0"/>
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
	<tr>
		<td>用户编号：</td>
		<td>
			<input type="text" id="memberNo" name="memberNo">
		</td>
		<td>提现申请日期：</td>
		<td><input id="startDate" name="startDate" type="datetime" class="easyui-datebox"></input>
			至 <input id="endDate" name="endDate"  type="datetime" class="easyui-datebox"/>
		</td>
		<td></td>
		<td>
			<button id="queryBtn" type="button" class="button">查询</button>
			<button id="clearBtn" type="button" class="button">清空</button>
		</td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">提现申请列表</div>
	<div class="icon">
		<ul>
			<li><a href="#" onclick="edit();"><span class="menu13"></span>修改</a></li>
			<li><a href="#" onclick="exportExcel();"><span class="menu2"></span>导出</a></li>
		</ul>
	</div>
</div>
<div class="changetab">
    <span id='ctab1' class="on">申请</span>
    <span id='ctab2'>其它</span>
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
		pagination : true,
		singleSelect : true,
		toolbar : '#toolbar',
	    columns : [[ 
	                 {width : '100',title : '用户编号',field : 'MEMBER_NO'},
	                 {width : '130',title : '提现申请日期',field : 'CREATE_TIME'},
	                 {width : '60',title : '提现金额',field : 'AMOUNT',formatter:function(v,r){return (v/100).toFixed(2);}},
	                 {width : '60',title : '手续费',field : 'FEE',formatter:function(v,r){return (v/100).toFixed(2);}},
	                 {width : '60',title : '到账金额',field : 'ARRIVAL_AMOUNT',formatter:function(v,r){return (v/100).toFixed(2);}},
	                 {width : '60',title : '转化积分',field : 'JF'},
	                 {width : '70',title : '银行卡姓名',field : 'CARD_OWNER_NAME'},
	                 {width : '150',title : '银行卡帐号',field : 'CARD_NO'},
	                 {width : '100',title : '开户行',field : 'BANK_NAME'},
	                 {width : '60',title : '所属城市',field : 'OPEN_BANK_CITY'},
					 {width : '70',title : '提现状态',field : 'STATUS',formatter:function(v,r){return JSON.parse('${statusMap}')[v]}},
					 {width : '120',title : '操作',field : 'ID', align:'center',formatter:function(v,r){
						 if(r.STATUS==0){
							 return '<a style="color: blue;" href="javascript:void 0;" onclick="update('+v+',2);void 0;">审核通过</a> <a style="color: blue;" href="javascript:void 0;" onclick="update('+v+',5);void 0;">提现成功</a>';
						 }else if(r.STATUS==2 || r.STATUS==3){
							 return '<a style="color: blue;" href="javascript:void 0;" onclick="update('+v+',5);void 0;">提现成功</a>';
						 }else{
							 return '';
						 }
					 }},
					 {width : '80',title : '操作员',field : 'OPERATOR'},
					 {width : '120',title : '备注',field : 'MEMO'}
	            	]
	    		]
	});

	$(".changetab span").click(function(){
		$(this).addClass("on");
		$(this).siblings().removeClass("on");
		
		if($(this).attr("id")=="ctab1"){
			$("#mulstatus").val("=0");
		}else{
			$("#mulstatus").val("!=0");
		}
		var obj={};
		$("#list :input[type='text']").each(function() {
			obj[$(this).attr("name")] = $(this).val();
		});
		$("#list :input[type='hidden']").each(function() {
			obj[$(this).attr("name")] = $(this).val();
		});
		$("#list :input[type='date']").each(function() {
			obj[$(this).attr("name")] = $(this).val();
		});
		
		$('#grid').datagrid('load',obj);
	})
});


function update(drawId,drawStatus){
	var tip="确定提现成功？";
	if(drawStatus==2){
		tip="确定审核通过？";
	}
	
	$.messager.confirm('提示:',tip,function(e){ 
		if(e){
			$.ajax({
				url:'changeStatus',
				dataType:'json',
				data:{drawId:drawId,drawStatus:drawStatus},
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

//导出Excel表格
function exportExcel(){
	var params=$("#list").serialize();
	$("#exportForm").attr("action","export?"+params);
	$("#exportForm").submit();
}

function edit(){
	var selections = $('#grid').datagrid('getSelections');
	if (selections.length != 1) {		
		$.messager.alert('提示:','请选择一行记录!'); 
		return false;
	}
	var drawId = selections[0]["ID"];
	var drawStatus=selections[0]["DRAW_STATUS"];
	$.dialog.open('<c:url value = "/draw/toEdit" />?drawId='+drawId+'&drawStatus='+drawStatus, {
		lock: true,
		width:600,
		height:230
	});
}
</script>
</html>