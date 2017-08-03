<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<form id="list" action="list">
<input type="hidden" id="flag" name="flag" value="0"/>
<div id="search" class="easyui-panel" title="查询条件" data-options="fit:true,collapsible:true,border:0"> 
  <table class="searchTable">
		<tr>
			<td>用户编号：</td>
			<td><input type="text" id="accountNo" name="accountNo"></td>
			<td>申请时间：</td>
			<td><input id="startDate" name="startDate" type="datetime" class="easyui-datebox"></input>
				至 <input id="endDate" name="endDate" type="datetime" class="easyui-datebox"/>
			<td><button id="queryBtn2" type="button" class="button">查询</button></td>
			<td><button id="clearBtn" type="button" class="button">清空</button></td>
		</tr>
	   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">服务中心管理列表	</div>
</div>
<div id="tt" class="easyui-tabs">
    <div title="待开通列表" style="padding-top: 10px;"></div>
    <div title="已开通列表" style="overflow:auto;padding-top: 10px;"></div>
</div>

</body>
<script type="text/javascript">
var _ctx = '<c:url value="/" />';
var gridId="grid1" ;
var contentHeight;
$(function(){
	contentHeight= $(window).height() - $('.easyui-fluid').height() - $('.operate').height() - 2;
	//console.log(contentHeight);
	$(".easyui-tabs").css("height",contentHeight+"px");
	
	loadGridData();
	$('#tt').tabs({
		height:contentHeight,
	    border:false,
	    onSelect:function(title){
	    	gridId=title === "待开通列表" ? "grid1" : "grid2";
	    	loadGridData();	  
	    }
	});
	
	$('#queryBtn2').click(function(){
		$('#'+gridId).datagrid('load',getFormData(document.forms[0]));
	});

});

function  loadGridData(){
	
	var flag=0;
	 if(gridId==="grid1"){
		 flag=0;
	 }else if(gridId==="grid2"){
		 flag=1;
	 }
	 $("#flag").val(flag);
	var getSel= $('#tt').tabs('getSelected');
	var dynamicTable =$('<table id="'+gridId+'" data-options="fit:true,border:false"></table>');
	getSel.html(dynamicTable);
	var cols=null;
	if(flag == 0){
		cols=[[ 
	              /*  {width : '120',title : '服务中心编号',field : 'businessNo'}, */
	               {width : '150',title : '用户编号',field : 'accountNo'},
	               {width : '100',title : '用户姓名',field : 'realName'},
	               {width : '100',title : '电话',field : 'phone'},
	               {width : '150',title : '申请时间',field : 'createTime'},
	               {width : '100',title : '开通状态',field : 'openState',formatter:function(v,r){return JSON.parse('${openMap}')[v];}},
	               {width : '120',title : '操作',field : 'id',formatter:function(v,r){
	              	 		return '<a style="color:red;cursor: pointer;" onclick="operate('+v+',1,'+r.version+');">审核</a>&nbsp;&nbsp;'
							 	  	  +'<a style="color:blue;cursor: pointer;" onclick="operate('+v+',2);">删除</a>';
	               	}
	               }
	            ]];
	}else if(flag == 1){
		cols=[[ 
	               {width : '120',title : '服务中心编号',field : 'businessNo'},
	               {width : '120',title : '用户编号',field : 'accountNo'},
	               {width : '100',title : '用户姓名',field : 'realName'},
	               {width : '100',title : '电话',field : 'phone'},
	               {width : '130',title : '申请时间',field : 'createTime'},
	               {width : '130',title : '修改时间',field : 'updateTime'},
	               {width : '70',title : '开通状态',field : 'openState',formatter:function(v,r){return JSON.parse('${openMap}')[v];}},
	               {width : '70',title : '激活状态',field : 'activeState',formatter:function(v,r){return JSON.parse('${activeMap}')[v];}},
	               {width : '100',title : '操作',field : 'id',
	            	   formatter:function(v,r){
							if(r.activeState == 1){
								return '<a style="color:red;cursor: pointer;" onclick="operate('+v+',3,'+r.version+');">冻结</a>&nbsp;&nbsp;'
						 	  	  +'<a style="color:blue;cursor: pointer;" onclick="operate(\''+r.accountNo+'\',5);">用户列表</a>';
							}else if(r.activeState == 2){
								return '<a style="color:green;cursor: pointer;" onclick="operate('+v+',4,'+r.version+');">激活</a>&nbsp;&nbsp;'
						 	  	  +'<a style="color:blue;cursor: pointer;" onclick="operate(\''+r.accountNo+'\',5);">用户列表</a>';
							}
	               		}
	               },
	               {width : '180',title : '备注',field : 'remark'},
	               {width : '60',title : '操作人',field : 'operater'}
	            ]];
	}
	
	dynamicTable.datagrid({   
	    url:'list?flag='+flag, 
	    height:contentHeight,
	    pageSize :10,
		pageList : [10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect:true,
	    columns : cols
	}); 
	
}

function operate(id,flag,version){
	if(flag==1){//开通
		
		$.dialog.open(_ctx+"busCenter/audit?id="+id, {
			lock: true,
			width:550,
			height:250,
			close:function(){
				$('#queryBtn2').click();
			}
		});
	
	}else if(flag==2){//删除
		$.messager.confirm('提示','是否删除此记录？',function(val){ 
			if(val){
				$.post('delete',{"id":id}, function(data) {
					 if(data.success){
						 refreshGrid(gridId);
		  				} else {
		  					if(data.msg){
		  						$.messager.alert('提示:',data.msg,'warning');
		  					}
		  				}
				 }, 'json');
			}
		});
	}else if(flag==3){//冻结
		$.messager.confirm('提示','是否冻结此服务中心？',function(val){ 
			if(val){
				$.post('freeze',{"id":id,"version":version}, function(data) {
					 if(data.success){
						 refreshGrid(gridId);
		  				} else {
		  					if(data.msg){
		  						$.messager.alert('提示:',data.msg,'warning');
		  					}
		  				}
				 }, 'json');
			}
		});
		
	}else if(flag==4){//激活
		$.messager.confirm('提示','是否激活此服务中心？',function(val){ 
			if(val){
				$.post('active',{"id":id,"version":version}, function(data) {
					 if(data.success){
						 refreshGrid(gridId);
		  				} else {
		  					if(data.msg){
		  						$.messager.alert('提示:',data.msg,'warning');
		  					}
		  				}
				 }, 'json');
			}
		});
		
	}else if(flag==5){//查看用户列表
		$.dialog.open(_ctx+"busCenter/showList?id="+id, {
			lock: true,
			width:900,
			height:400
		});
	}
}

</script>
</html>