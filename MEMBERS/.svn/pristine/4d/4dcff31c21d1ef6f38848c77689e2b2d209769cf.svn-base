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
		<td>收货人电话：</td>
		<td><input type="text" id="recepitPhone" name="recepitPhone"></td>
		<td>发货状态：</td>
		<td><input type="combo" id="state" name="state"></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>申请时间：</td>
		<td><input id="startDate" name="startDate" type="datetime" class="easyui-datebox"></input>
			至 <input id="endDate" name="endDate"  type="datetime" class="easyui-datebox"/>
		</td>
		<td>兑换类型：</td>
		<td><input type="combo" id="type" name="type"></td>
		<td><button id="queryBtn" type="button" class="button">查询</button></td>
		<td><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">用户发货列表</div>
	<div class="icon">
		<ul>
			<li><a href="#" onclick="exportExcel();"><span class="menu2"></span>导出</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false" ></table>
<form  action="#" id="exportForm" method="post"></form>
</body>
<script type="text/javascript">
$(function() {
	$('#grid').datagrid({   
	    url:'list', 
	    pageSize :20,
		pageList : [10, 20, 30, 40, 50, 100],
		striped : true,
		rownumbers : true,
		pagination : true,
		toolbar : '#toolbar',
	    columns : [[ 
					 {width : '70',title : '兑换物品',field : 'goodType',formatter:function(v){return JSON.parse('${goodTypeJson}')[v];}},
	                 {width : '80',title : '用户名称',field : 'userName'},
	                 {width : '100',title : '用户编号',field : 'accountNo'},
	                 {width : '135',title : '申请时间',field : 'applyTime'},
	                 {width : '70',title : '兑换数量',align:'center',field : 'exchangeNum'},
	                 {width : '80',title : '收货人姓名',field : 'recepitUserName'},
	                 {width : '90',title : '收货人电话',field : 'recepitPhone'},
	                 {width : '240',title : '地址',field : 'fullAddress',formatter:function(v,r){return r.province+r.city+r.area+v;}},
	                 {width : '135',title : '发货时间',field : 'sendTime'},
	                 {width : '70',title : '发货人',field : 'sendUser'},
	                 {width : '90',title : '快递公司',field : 'expressCompany'},
	                 {width : '90',title : '发货单号',field : 'expressOrder'},
	                 {width : '70',title : '发货状态',field : 'state',align:'center',formatter:function(v){return JSON.parse('${stateCombojson}')[v];}},
	                 {width : '100',title : '操作' ,field : 'id', align:'center', formatter:function(v,r){
							 if(r.state==1){
								 return  '<a href="#" onclick="auditSend('+v+',2,'+r.version+','+r.goodType+');"><span style="color:red">发货</span></a>&nbsp;&nbsp;'
								 		+'<a href="#" onclick="auditSend('+v+',3,'+r.version+','+r.goodType+');"><span style="color:red">取消发货</span></a>';
							 }
							 else {
								 return '<span style="color:blue">暂无操作</span>'
							 }
			                }
						}
	            	]
	    		 ]    
	}); 
    $('#state').combobox({  
    	data:JSON.parse('${stateCombo}'),
    	editable:false,
    	panelHeight:'auto'
    }); 
    $('#type').combobox({  
    	data:JSON.parse('${goodTypeCombo}'),
    	editable:false,
    	panelHeight:'auto'
    }); 
    type
});

function auditSend(id,audit_flag,version,goodType){
	/**如果确认出库，则填写快递信息**/
	if(audit_flag==2){
		//确认发货，填写快递公司和物流单号，更新发货明细状态和库存数量
		$.dialog.open(ctx+"exchange/showConfirmSend?id="+id+"&opType=2"+"&version="+version, {
			lock: true,
			width:380,
			height:220,
			close:function(){
				$('#grid').datagrid('reload');
			}
		});  
    }
	/**如果确认发货，则直接更新状态**/
	 else{
		$.messager.confirm('提示:','取消发货？',function(e){ 
			if(e){
				$.ajax({
					type: 'POST',
					url:'confirmSend',
					dataType:'json',
					data:{id:id,opType:3,version:version,gType:goodType},
					success:function(data){
						if(data.success==true){
							$('#grid').datagrid('reload');
							showSlide(data.msg);
						}else{
							showSlide(data.msg);
						}
					},
					error:function(req,err,ecp){
						showSlide("操作异常，请重新操作");
					}
				});
			}
		}); 
	  }
}

//导出Excel表格
function exportExcel(){
	var params=$("#list").serialize();
	$("#exportForm").attr("action","export?"+params);
	$("#exportForm").submit();
}

function showSlide(msg){
	$.messager.show({
		title:'发货确认消息',
		msg:msg,
		timeout:5000,
		showType:'slide'
	});
}

</script>
</html>