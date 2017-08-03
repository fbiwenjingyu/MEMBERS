<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <script type="text/javascript" src="${js}/assetes.js"></script>
  <title>大博智家会员系统</title>
  <style type="text/css">
  .hfont{
    color:#ff5722;
	}
	  .gfont{
    color:#02CDC1;
	}
  </style>
  <script type="text/javascript">
		$(function() {
			doQuery(1);
		});
		
		//点击按钮分页 ，远程获取数据
		function doQuery(pageNumber) {
			var _url;
			pageNumber = pageNumber || 1;
			_url = ctx + 'transfer/list?pageNumber=' + pageNumber
			+ '&pageSize=' + pageSize + '&startDate='
			+ $('#startDate').val() + '&endDate=' + $('#endDate').val()
			+ '&type=' + $('#type').val();
			

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
			var htm = '<tr><th width="20%">账户类型</th><th width="20%">对方账号</th><th width="20%">用户姓名</th><th width="20%">转账金额</th><th >转账日期</th></tr>';
			for ( var key in list) {
				var obj = list[key];
				if(obj.transferType==1){
					htm += '<tr>' + '<td>' + typeFormat(obj.type) + '</td>' + '<td>'
					+ obj.transferPhone + '</td>' + '<td>'
					+ obj.transferName + '</td> ' + '<td class="gfont">'
					+ accountFormat(obj.transferAmount,obj.type,obj.transferType) + '</td> ' + '<td>'
					+ obj.createTime + '</td> ';
				}else{
					htm += '<tr>' + '<td>' + typeFormat(obj.type) + '</td>' + '<td>'
					+ obj.transferPhone + '</td>' + '<td>'
					+ obj.transferName + '</td> ' + '<td class="hfont">'
					+ accountFormat(obj.transferAmount,obj.type,obj.transferType) + '</td> ' + '<td>'
					+ obj.createTime + '</td> ';
				}
				htm += '</tr>';
			}
			$('.table_ui').empty().append(htm);
		}
		
		function accountFormat(v,type,transferType) {
			if(v==null||v==''){
				if(type==1){
					v='0元';
				}
				v=0;
			}
			if(type==1){
				v=v/100+'元';
			}
			if(transferType==1){
				v="-"+v;
			}else{
				v="+"+v
			}
			return v;
		}
		
		//转账类别
		function typeFormat(v) {
			var map = [  {
				"value" : "1",
				"text" : "现金"
			}, {
				"value" : "2",
				"text" : "博豆"
			}, {
				"value" : "3",
				"text" : "积分"
			} ];
			for ( var m in map) {
				if (map[m].value == v) {
					return map[m].text;
				}
			}
			return "";
		}
	</script>
  
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <div class="container">
    <jsp:include page="../memu.jsp">
 		<jsp:param value="4" name="on"/>
 	</jsp:include>
 	<jsp:include page="tab.jsp">
 		<jsp:param value="5" name="on"/>
 	</jsp:include>
 	
    <div class="wrap_cont">
        <div class="ibox white">
            <h4 class="ibox_tit">我的资产<a href="toAdd" class="tit_a">转账</a></h4>
            <div class="my_asset">
                <span>现金：<b id="balance">${user.balance}</b></span>
                <span>积分：<b id="generalizeScore">${user.generalizeScore }</b></span>
                <span>博豆：<b id="shoppingScore">${user.shoppingScore }</b></span>
            </div>
        </div>
        <div class="ibox white">
            <h4 class="ibox_tit" id="toltext">转账记录</h4>
            <form action="" class="sui-form sui-validate ass_form form-horizontal">
              <div class="ass_fl s1">
                <div data-toggle="datepicker" class="control-group input-daterange">
                  <label class="control-label">日期</label>
                  <div class="controls">
                    <input id="startDate" name="startDate" type="text" class="input-medium input-date"><span>至</span>
                    <input id="endDate" name="endDate" type="text" class="input-medium input-date">
                  </div>
                </div>
              </div>
              <div class="ass_fl s2">
                <div class="control-group">
                  <label for="awardtype1" class="control-label">转账类型</label>
                  <div class="controls">
                    <select name="type" id="type">
                      <option value="0">全部</option>
                      <option value="1">转出记录</option>
                      <option value="2">转入记录</option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="ass_fl s3">
                <div class="control-group">
                  <button type="button" class="sui-btn sui-search" onclick="doQuery(1)">搜索</button>
                </div>
              </div>
            </form>
            <table class="table_ui" border="0" cellpadding="0" cellspacing="1">
            </table>
            <div class="notice_none" style="display:none;">暂无数据！</div>
        </div>
    </div>
    <%@ include file="/WEB-INF/views/pageLink.jsp" %>
  </div>
</div>
</body>
</html>