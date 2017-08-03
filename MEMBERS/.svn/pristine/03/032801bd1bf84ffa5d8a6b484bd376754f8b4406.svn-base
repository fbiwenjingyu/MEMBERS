<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
   <style type="text/css">
	  .gfont{
    color:#ff5722;
	}
  </style>
    <script src="${js}/vip.js"></script>
	<script type="text/javascript">
		var tabindex=0;
		var id=0;
		
		$(function() {
			var levelMap = ${levelMap};
			for ( var level in levelMap) {
				if (levelMap[level].value == "") {
					$("#level").append(
							"<option value='" + levelMap[level].value + "' selected='selected'> "
									+ levelMap[level].text + "</option>");
				} else {
					$("#level").append(
							"<option value='" + levelMap[level].value + "'> "
									+ levelMap[level].text + "</option>");
				}
			}
			doQuery(1);
		});
		
		//切换tab
		function changeTab(index){
			if(index==0){
				tabindex=0;
				$("#datetext").text("注册日期");
			}else{
				tabindex=1;
				$("#datetext").text("开通日期");
			}
			doQuery(1);
		}
		
		//check余额是否充足
		function checkAcount(){
			var type,flag;
			if($("#radio1").hasClass("checked")){
				type=1;
			}
			if($("#radio2").hasClass("checked")){
				type=2;
			}
			if($("#radio3").hasClass("checked")){
				type=3;
			}
			$("#type").val(type);
			$.ajax({
				url:'checkAccount',
				dataType:'json',
				async: false,
				data:{'type': type,"memberId":id},
				success:function(data){
					if(data.code=='200'){
						flag = true;
					}else{
						flag = false;
					}
				},
				error:function(req,err,ecp){
					alert(data.msg);
				}
			});
			return flag;
		}
		
		
		//点击按钮分页 ，远程获取数据
		function doQuery(pageNumber) {
			var _url;
			pageNumber = pageNumber || 1;
			if(tabindex==0){
				_url = ctx + 'memberManage/findUnOpenUser?pageNumber=' + pageNumber
				+ '&pageSize=' + pageSize + '&startDate='
				+ $('#startDate').val() + '&endDate=' + $('#endDate').val()
				+ '&level=' + $('#level').val() + '&realName='
				+ $('#realName').val() + '&accountNo='
				+ $('#accountNo').val();
			}else{
				_url = ctx + 'memberManage/findOpenUser?pageNumber=' + pageNumber
				+ '&pageSize=' + pageSize + '&startDate='
				+ $('#startDate').val() + '&endDate=' + $('#endDate').val()
				+ '&level=' + $('#level').val() + '&realName='
				+ $('#realName').val() + '&accountNo='
				+ $('#accountNo').val();
			}

			$.ajax({
				url : _url,
				type : 'GET',
				dataType : 'json',
				success : function(data) {
					if (data.result == false) {
						alert(data.msg);
						return;
					}
					if (data.total === 0) {//如果没有记录则隐藏分页插件
						$(".page_box").hide();
						$('.table_ui').empty();
						$(".notice_none").show();	
						return;
					} else {//有数据
						$(".notice_none").hide();
						$(".page_box").show();
					}	
					
					$('#sum').html(data.total);

					//自定义解析数据
					parseHtml(data.rows);

					if (pageNumber === 1) {
						sum = data.total;
						pageInit(sum, pageSize);
					}
				},
				error : function(text, xhr) {
					console.log(xhr);
				}
			});
		}

		//拼接table
		function parseHtml(data) {
			var list = data;
			if(tabindex==0){
				var htm = '<tr><th width="20%">姓名</th><th width="15%">帐号</th><th width="12%">注册金额</th><th width="13%">用户等级</th><th width="20%">注册日期</th><th width="10%">注册单数</th><th width="20%">操作</th></tr>';
				for ( var key in list) {
					var obj = list[key];
					htm += '<tr>' + '<td>' + obj.realName + '</td>' + '<td>'
							+ obj.accountNo + '</td>' + '<td>'
							+ moneyFormat(obj.registerMoney) + '</td> ' + '<td>'
							+ levelFormat(obj.level) + '</td> ' + '<td>'
							+ obj.createTime + '</td> ' + '<td>'
							+ obj.regNum + '</td> '+ '<td><a onclick="setId('+obj.id+','+obj.regNum+')" href="#activeModal" data-toggle="modal">激活</a>&nbsp;<a style="color: blue;margin-left:10px" href="#" onclick="deleteInfo('+obj.id+');">删除</a></td>';
					htm += '</tr>';
				}
			}else{
				var htm = '<tr><th width="12%">姓名</th><th width="12%">帐号</th><th width="12%">注册金额</th><th width="16%">用户状态</th><th width="8%">用户等级</th><th width="20%">注册日期</th><th width="20%">开通日期</th></tr>';
				for ( var key in list) {
					var obj = list[key];
					htm += '<tr>' + '<td>' + obj.realName + '</td>' + '<td>'
							+ obj.accountNo + '</td>' + '<td>'
							+ moneyFormat(obj.registerMoney) + '</td> ' + '<td>'
							+ typeFormat(obj.type) + '</td> ' + '<td>'
							+ levelFormat(obj.level) + '</td> ' + '<td>'
							+ obj.createTime + '</td> ' + '<td>'
							+ obj.activateTime + '</td> ';
					htm += '</tr>';
				}
			}
			$('.table_ui').empty().append(htm);
		}
		
		function moneyFormat(v) {
			if(v==null||v==''){
				return 0;
			}
			return v;
		}
		
		//转换级别
		function levelFormat(v) {
			var map = [  {
				"value" : "0",
				"text" : "普通"
			}, {
				"value" : "1",
				"text" : "铜卡"
			}, {
				"value" : "2",
				"text" : "银卡"
			}, {
				"value" : "3",
				"text" : "金卡"
			} ];
			for ( var m in map) {
				if (map[m].value == v) {
					return map[m].text;
				}
			}
			return "";
		}
		
		function deleteInfo(id){
			$.ajax({
				url:'delete',
				dataType:'json',
				data:{'id':id},
				success:function(data){
					if(data.success){
						doQuery(1);
					}else{
						$.messager.alert('提示:',data.msg,'warning'); 
					}
				},
				error:function(req,err,ecp){
					$.messager.alert('提示:','系统异常','error'); 
				}
			});
		}
		
		//状态级别
		function typeFormat(v) {
			var map = [  {
				"value" : "-1",
				"text" : "冻结的用户"
			}, {
				"value" : "0",
				"text" : "未激活"
			}, {
				"value" : "1",
				"text" : "已激活"
			}, {
				"value" : "2",
				"text" : "冻结的服务中心"
			}, {
				"value" : "3",
				"text" : "服务中心"
			} ];
			for ( var m in map) {
				if (map[m].value == v) {
					return map[m].text;
				}
			}
			return "";
		}
		function setId(userId,regNum){
			id=userId;
			$("#userId").val(userId);
			$("#sp1").html("现金币激活，"+${rAmount}*regNum+"元");
			$("#sp2").html("现金币+博豆激活，"+${rAmount*cashScale/100}*regNum+"元+"+${rAmount-rAmount*cashScale/100}*regNum+"博豆");
			$("#sp3").html("现金币+积分激活，"+${rAmount*cashScale/100}*regNum+"元+"+${rAmount-rAmount*cashScale/100}*regNum+"积分");
		}
		
	</script>
  
</head>
<body id="vip">
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <jsp:include page="../memu.jsp">
  		<jsp:param name="on" value="3"></jsp:param>
  	</jsp:include>
  <div class="container">
    <jsp:include page="tab.jsp">
   		<jsp:param name="on" value="1"></jsp:param>
    </jsp:include>
    <div class="small_nav_box">
      <ul class="nav-tabs nav-large">
        <li id="unopen" class="active"><a href="#preactive" data-toggle="tab" onclick="changeTab(0)">待开通</a></li>
        <li id="open" ><a href="#actived" data-toggle="tab" onclick="changeTab(1)">已开通</a></li>
      </ul>
    </div>
    <div class="wrap_cont tab-pane active">
        <div class="ibox white">
            <form action="" class="sui-form sui-validate ass_form ">
              <div class="ass_fl s1">
                <div data-toggle="datepicker" class="control-group input-daterange">
                  <label id="datetext" class="control-label">注册日期</label>
                  <div class="controls">
                    <input id="startDate" name="startDate" type="text" class="input-medium input-date"><span>至</span>
                    <input id="endDate" name="endDate" type="text" class="input-medium input-date">
                  </div>
                </div>
              </div>
              <div class="ass_fl s2">
                <div class="control-group">
                  <label for="awardtype1" class="control-label">用户等级</label>
                  <div class="controls">
                    <select name="level" id="level">
                    </select>
                  </div>
                </div>
              </div>
              <div class="ass_fl s4">
                <div class="control-group">
                  <label for="businesstype" class="control-label">姓名</label>
                  <div class="controls">
                    <input id="realName" name="realName" type="text" class="input-medium">
                  </div>
                </div>
              </div>
               <div class="ass_fl s5">
                <div class="control-group">
                  <label for="businesstype" class="control-label">帐户</label>
                  <div class="controls">
                    <input id="accountNo" name="accountNo" type="text" class="input-medium">
                  </div>
                </div>
              </div>
              <div class="ass_fl s3">
                <div class="control-group">
                  <button type="button" class="sui-btn sui-search" onclick="doQuery(1)">搜索</button>
                </div>
              </div>
            </form>
        </div>
        <div class="ibox white">
            <table id="usertable" class="table_ui" border="0" cellpadding="0" cellspacing="1">
            </table>
            <div class="notice_none" style="display:none;">暂无数据！</div>
        </div>
    </div>
    <%@ include file="/WEB-INF/views/pageLink.jsp" %>
  </div>
</div>
<div id="activeModal" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade window_box">
  <div class="window_cont">
    <h4>选择激活方式</h4>
    <form action="" method="post" class="sui-form sui-validate">
      <label id="radio1" class="radio-pretty checked">
        <input type="radio" name="radio" value="1"><span id="sp1">现金币激活，${rAmount}元</span>
      </label>
      <label id="radio2" class="radio-pretty">
        <input type="radio" name="radio" value="2">
        	<span id="sp2">现金币+博豆激活，<fmt:formatNumber value="${rAmount*cashScale/100}" type="currency" pattern="#"/>元+<fmt:formatNumber value="${rAmount-rAmount*cashScale/100}" type="currency" pattern="#"/>元博豆</span>
      </label>
      <label id="radio3" class="radio-pretty">
        <input type="radio" name="radio" value="3">
        	<span id="sp3">现金币+积分激活，<fmt:formatNumber value="${rAmount*cashScale/100}" type="currency" pattern="#"/>元+<fmt:formatNumber value="${rAmount-rAmount*cashScale/100}" type="currency" pattern="#"/>元积分</span>
      </label>
      <div class="control-group ">
        <div class="controls">
          <button type="button" data-ok="modal" class="btn btn-ok">确定</button>
          <button type="button" data-dismiss="modal" class="btn btn-cancel">取消</button>
        </div>
      </div>
    </form>
  </div>
</div>
<div id="payModal" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade window_box">
  <div class="window_cont">
    <h4>请输入支付密码</h4>
    <form id="openform" onsubmit="return false;" class="sui-form sui-validate">
      <div class="control-group ">
        <label for="" class="control-label">
        <i class="star">*</i>支付密码</label>
        <div class="controls">
        <input id="userId" name="userId" type="hidden">
          <input id="type" name="type" type="hidden">
          <input type="password" style="display:none">
          <input id="password" type="password" data-rules="required|number|minlength=6|maxlength=24">
          <span class="help-inline" data-rules="required|number|minlength=6|maxlength=24">仅支持6到24位字符</span>
          
        </div>
      </div>
      <div class="control-group ">
        <div class="controls">
          <button id="paybtn" type="submit"  class="sui-btn btn-primary" >确定</button>
            <button type="button" data-dismiss="modal" class="btn btn-cancel">取消</button>
        </div>
      </div>
      <p class="gfont" id="error"></p>
    </form>
  </div>
</div>
<div id="payNoModal" tabindex="-1" role="dialog" data-hasfoot="false" class="sui-modal hide fade window_box">
  <div class="window_cont">
    <h4>温馨提示</h4>
    <form action="" method="post" class="sui-form sui-validate">
      <label>帐户余额不足，请您先充值</label>
      <div class="control-group ">
        <div class="controls">
          <button type="button" data-ok="modal" class="btn btn-ok" onclick="toRecharge()">充值</button>
          <button type="button" data-dismiss="modal" class="btn btn-cancel">取消</button>
        </div>
      </div>
    </form>
  </div>
</div>
<div class="sui-msg msg-large msg-success" id="sucess" style="display:none;z-index:100;position:absolute;left:50%;top:50%;width:130px;height:46px;margin:-23px 0 0 -65px;">
  	<div class="msg-con">操作成功</div>
 	 <s class="msg-icon"></s>
 </div>
<script type="text/javascript">
	$("#openform").validate({
			    success: function() {
			    	$("#paybtn").attr({"disabled":"disabled"});
			    	$.post("openMember", {"type" : $("#type").val(),"memberId" : $("#userId").val(), "password" : $("#password").val()}, function(data) {
						if (data.code=="200") {
							$('#payModal').modal('hide');
							$("#sucess").fadeIn();
							setTimeout(function(){
								$("#sucess").fadeOut();
							},2000);
							doQuery(1);
						} else {
							$("#paybtn").removeAttr("disabled");
							$("#error").html(data.msg);
						}
					}, "json");
			        return false;
			      }
			});
</script>
</body>
</html>