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
		<td>开通日期：</td>
		<td><input id="startDate" name="startDate" type="datetime" class="easyui-datebox" ></input>
			至 <input id="endDate" name="endDate"  type="datetime" class="easyui-datebox" />
		</td>
		<td><button id="queryBtn" type="button" class="button">查询</button></td>
		<td><button id="clearBtn" type="button" class="button">清空</button></td>
	</tr>
   </table>
</div>
</form>
<div class="operate">
	<div class="om-panel-header">用户信息列表</div>
	<div class="icon">
		<ul>
    		<li><a href="#" onclick="showAdd('/user/add',850,550);"><span class="menu1"></span>添加</a></li>
    		<li><a href="#" onclick="showEdit('/user/edit','id',850,550);"><span class="menu13"></span>修改</a></li>
    		<li><a href="#" onclick="showEdit('/user/show','id',500,510);"><span class="menu7"></span>查看</a></li>
    		<li><a href="#" onclick="initPwd();"><span class="menu9"></span>重置密码</a></li>
    		<li><a href="#" onclick="showEdit('/user/nodeEdit','id',300,200);"><span class="menu13"></span>节点调整</a></li>
		</ul>
	</div>
</div>
</div>
<table id="grid" data-options="fit:true,border:false"></table>
</body>
<script type="text/javascript">
var rAmount=parseInt('${rAmount}');//激活用户所需金额
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
					 {width : '50', field : 'ck',checkbox:true},
	                 {width : '100',title : '邀请人编号',field : 'recommendNo'},
	                 {width : '100',title : '用户编号',field : 'accountNo'},
	                 {width : '80',title : '用户姓名',field : 'realName'},
	                 {width : '125',title : '开通时间',field : 'activateTime'},
	                 {width : '80',title : '用户类型',field : 'type',formatter:function(v){return JSON.parse('${typeMap}')[v]}},
	                 {width : '60',title : '用户等级',field : 'level',formatter:function(v){return JSON.parse('${levelMap}')[v]}},
	                 {width : '100',title : '推广积分',field : 'generalizeScore', formatter:function(v,r){if(v == null){ return 0;}else{return v;}}},
	                 {width : '100',title : '现金币(元)',field : 'balance', formatter:function(v,r){if(v == null){ return 0.00;}else{ return fmoney(v/100);}}},
	                 {width : '100',title : '购物积分',field : 'shoppingScore', formatter:function(v,r){if(v == null){ return 0;}else{return v;}}},
	                 {width : '90',title : '团队业绩',field : 'teamMoney', formatter:function(v,r){if(v == null){ return 0.00;}else{ return fmoney(v);}}},
	                 {width : '90',title : '个人业绩',field : 'myMoney', formatter:function(v,r){if(v == null){ return 0.00;}else{ return fmoney(v);}}},
	                 {width : '75',title : '激活类型',field : 'isOpend',formatter:function(v,r){return v == 2 ? '实单': '空单';}},
	                 {width : '75',title : '直推铜卡',field : 'copperCardNum', formatter:function(v,r){if(v == null|| v == ''){ return 0;}else{return v;}}},
	                 {width : '75',title : '直推银卡',field : 'silverCardNum', formatter:function(v,r){if(v == null|| v == ''){ return 0;}else{return v;}}},
	                 {width : '60',title : '操作',field : 'id', formatter:function(v,r){
	                	 
	                	 if(r.type == -1){
	                		 type = 1;
	                		 msg ="激活";
	                		 color = 'green';
	                	 }else if(r.type == 1){
	                		 type = -1;
	                		 msg ="冻结";
	                		 color = 'red';
	                	 }else if(r.type == 2){
	                		 type = 3;
	                		 msg ="激活";
	                		 color = 'green';
	                	 }else if(r.type == 3){
	                		 type = 2;
	                		 msg ="冻结";
	                		 color = 'red';
	                	 }
	                	 var str = "<a href=\"javascript:frozen(" + v + ",'"+r.accountNo+ "', " + type + ");\"><span style='color:" + color + "'>" + msg + "</span></a>";
						 return str;
					   }
	                }
	            ]]
	}); 
});

//检查日期
function checkDate(){
	var sDate = $.trim($("input[name='startDate']").eq(0).val());
	var eDate = $.trim($("input[name='endDate']").eq(0).val());
	if((sDate == ''  && eDate == '') || (sDate.length == 10  && eDate.length == 10)){
		return true;
	}else{
		alert("请指定开通时间的范围");
		return false;
	}
	
}

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

function frozen(id,realName, type){
    var msg  = (type == -1 || type == 2) ? '确认冻结【'+realName+'】的用户账户？':'确认激活【'+realName+'】的用户账户?' ;
	$.messager.confirm('提示:',msg,function(e){ 
		if(e){
			$.ajax({
				url:'frozen',
				dataType:'json',
				data:{id:id, type:type},
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

function showSlide(msg){
	$.messager.show({
		title:'温馨提示：',
		msg:msg,
		timeout:2000,
		showType:'slide'
	});
}

function initPwd(){
	var selections = $('#grid').datagrid('getSelections');
	if (selections.length != 1) {
		$.messager.alert('提示:','请选择一行记录!'); 
		return false;
	}
	$.messager.confirm('提示:','确定重置【' + selections[0].accountNo + '】的登录密码为(<span style="color:red">111111</span>)，支付密码为(<span style="color:red">222222</span>)？',function(e){ 
		if(e){ 
		   var ids = [];
  		   $.post('initPwd',{"id":selections[0].id}, function(data) {
  			 if(data.success){
  					$("#grid").datagrid('reload'); 	
  					$.messager.show({ 
  						title:'温馨提示:', 
  						msg:'【' + selections[0].accountNo + '】密码重置成功!', 
  						timeout:1500, 
  						showType:'slide'
  					}); 
  				} else {
  					$.messager.alert('提示:',data.msg,'warning'); 
  				}
  			}, 'json');
		}
	}); 	
}

</script>
</html>