<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
  
  <script type="text/javascript">
  		var rAmount=parseInt('${rAmount}');//激活用户所需金额
		var tabindex=0;
		var id=0;
		var totalId=0;
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
		});
		
		function doTeamMemberQuery(userNo) {
			totalId=userNo;
			doQuery(1);
		}
		
		//点击按钮分页 ，远程获取数据
		function doQuery(pageNumber) {
			var type,_url;;
			pageNumber = pageNumber || 1;
			type=$('#zttype').val();
			if(type==1){
				_url = ctx + 'memberManage/findMemberInfo?pageNumber=' + pageNumber
				+ '&pageSize=' + pageSize + '&startDate='
				+ $('#startDate').val() + '&endDate=' + $('#endDate').val()
				+ '&level=' + $('#level').val() + '&realName='
				+ $('#realName').val() + '&accountNo='
				+ $('#accountNo').val() + '&totalId='
				+ totalId + '&type=1';
			}else if(type==2){
				_url = ctx + 'memberManage/findMemberInfo?pageNumber=' + pageNumber
				+ '&pageSize=' + pageSize + '&startDate='
				+ $('#startDate').val() + '&endDate=' + $('#endDate').val()
				+ '&level=' + $('#level').val() + '&realName='
				+ $('#realName').val() + '&accountNo='
				+ $('#accountNo').val() + '&totalId='
				+ totalId + '&type=2';
			}else{
				alert("请选择直推类型");
				return;
			}
			$.ajax({
				url : _url,
				type : 'GET',
				dataType : 'json',
				success : function(data) {
					if (data.result == false) {
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
			if($('#zttype').val()==1){
				var htm = '<tr><th width="10%">姓名</th><th width="12%">帐号</th><th width="12%">注册金额</th><th width="13%">用户状态</th><th width="13%">用户等级</th><th width="16%">开通日期</th><th width="10%">单数</th><th width="10%">业绩</th></tr>';
				for ( var key in list) {
					var obj = list[key];
					htm += '<tr>' + '<td>' + obj.realName + '</td>' + '<td><a href="#" onclick="doTeamMemberQuery('+obj.accountNo+')">'
							+ obj.accountNo + '</a></td>' + '<td>'
							+ moneyFormat(obj.registerMoney) + '</td> ' + '<td>'
							+ typeFormat(obj.type) + '</td> ' + '<td>'
							+ levelFormat(obj.level) + '</td> ' + '<td>'
							+ obj.activateTime + '</td> ' + '<td>'
							+ 1 + '</td> ' + '<td>'
							+ moneyFormat(obj.teamMoney) + '</td> ';
					htm += '</tr>';
				}
			}else if($('#zttype').val()==2){
				var htm = '<tr><th width="12%">邀请人</th><th width="12%">姓名</th><th width="12%">帐号</th><th width="10%">注册金额</th><th width="10%">用户状态</th><th width="10%">用户等级</th><th width="16%">开通日期</th><th width="10%">单数</th><th>业绩</th></tr>';
				for ( var key in list) {
					var obj = list[key];
					htm += '<tr>' + '<td>' + obj.recommendName + '</td>' + '<td>'
					+ obj.realName + '</td>' + '<td><a href="#" onclick="doTeamMemberQuery('+obj.accountNo+')">'
					+ obj.accountNo + '</a></td>' + '<td>'
					+ moneyFormat(obj.registerMoney) + '</td> ' + '<td>'
					+ typeFormat(obj.type) + '</td> ' + '<td>'
					+ levelFormat(obj.level) + '</td> ' + '<td>'
					+ obj.activateTime + '</td> ' + '<td>'
					+ 1 + '</td> ' + '<td>'
					+ moneyFormat(obj.teamMoney) + '</td> ';
					htm += '</tr>';
				}
			}
			$('.table_ui').empty().append(htm);
		}
		
		function levelFormat(v) {
			var map = [  {
				"value" : 0,
				"text" : "普通"
			}, {
				"value" : 1,
				"text" : "铜卡"
			}, {
				"value" : 2,
				"text" : "银卡"
			}, {
				"value" : 3,
				"text" : "金卡"
			} ];
			for ( var m in map) {
				if (map[m].value == v) {
					return map[m].text;
				}
			}
			return "";
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
		
		function moneyFormat(v) {
			if(v==null||v==''){
				return 0;
			}
			return v;
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
   		<jsp:param name="on" value="2"></jsp:param>
    </jsp:include>
    <div class="wrap_cont tab-pane active">
        <div class="ibox white">
            <form action="" class="sui-form sui-validate ass_form form-horizontal">
              <div class="ass_fl s1">
                <div data-toggle="datepicker" class="control-group input-daterange">
                  <label class="control-label">开通日期</label>
                  <div class="controls">
                    <input id="startDate" name="startDate" type="text" class="input-medium input-date"><span>至</span>
                    <input id="endDate" name="endDate" type="text" class="input-medium input-date">
                  </div>
                </div>
              </div>
              <div class="ass_fl s2">
                <div class="control-group">
                  <label for="awardtype" class="control-label">用户等级</label>
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
              <div class="ass_fl s6">
                <div class="control-group">
                  <label for="zttype" class="control-label">直推类型</label>
                  <div class="controls">
                    <select name="" id="zttype" onchange="doQuery(1)">
                      <option value="2">全部</option>
                      <option value="1">个人直推</option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="ass_fl s3">
                <div class="control-group">
                  <button type="button" class="sui-btn sui-search" onclick="doQuery(1)" >搜索</button>
                </div>
              </div>
            </form>
        </div>
        <div class="zt_cont">
          <!-- 以下是个人直推 -->
          <div class="ibox white" id="zt1">
              <table id="usertable" class="table_ui" border="0" cellpadding="0" cellspacing="1">
              </table>
              <div class="notice_none" style="display:none;">暂无数据！</div>
          </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/views/pageLink.jsp" %>
  </div>
</div>
</body>
</html>