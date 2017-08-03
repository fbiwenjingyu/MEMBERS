<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
  <style type="text/css">
  	.tabTil-box{display: block;margin:0 0 20px;position: relative;text-align: center;font-size: 15px;height: 32px;line-height: 32px;}
	.tabTil{text-align: center;font-size: 15px;border: 1px solid #808181;width: 190px;margin:0 auto;height: 32px;border-radius: 5px;position: relative;}
	.tabTil li{width: 95px;display: inline-block;height: 32px;line-height: 32px;float: left;color:#808181;cursor: pointer;}
	.tabTil li.show_on{background-color: #808181;color:#fff;}
  	.ibox{display:block;clear:both;}
  </style>
  <script type="text/javascript" src="${js}/assetes.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <div class="container">
 	<jsp:include page="../memu.jsp">
 		<jsp:param value="4" name="on"/>
 	</jsp:include>
 	<jsp:include page="tab.jsp">
 		<jsp:param value="3" name="on"/>
 	</jsp:include>
 	
    <div class="wrap_cont">
    	<div class="tabTil-box">	
           	<ul class="tabTil">
		        <li  class="show_on" onclick="changeTab(0)">资产流水</li>
		        <li onclick="changeTab(1)">统计汇总</li>
		      </ul>
	    </div>
    	<!-- 我的资产信息 -->
        <div class="ibox white" id="my_count" style="display: none;">
            <h4 class="ibox_tit">我的资产<a onclick="switchDiv(1);" class="tit_a">搜索</a></h4>
            <div class="my_asset">
                <span>现金：<b id="balance">${user.balance}</b></span>
                <span>积分：<b id="generalizeScore">${user.generalizeScore }</b></span>
                <span>博豆：<b id="shoppingScore">${user.shoppingScore }</b></span>
            </div>
        </div>
        
        <!-- 搜索DIV -->
        <div class="ibox white" id="my_count_seach" style="display: block;">
         <h4 class="ibox_tit">搜索<a onclick="switchDiv(0);" class="tit_a" >我的资产</a></h4>
            <form  class="sui-form form-horizontal sui-validate ass_form">
            <input  id="userId" name="userId" type="hidden" value="${user.id }"/>
              <div class="ass_fl s1">
                <div data-toggle="datepicker" class="control-group input-daterange">
                  <label class="control-label">流水日期</label>
                  <div class="controls">
                    <input type="text" class="input-medium input-date" id="startDate"><span>至</span>
                    <input type="text" class="input-medium input-date" id="endDate">
                  </div>
                </div>
              </div>
              <div class="ass_fl s2" id="awardSelect" >
                <div class="control-group">
                  <label for="awardtype" class="control-label">奖项类型</label>
                  <div class="controls">
                    <select name="awardtype" id="awardtype">
                      <option value="">全部</option>
                      <option value="1">现金币</option>
                      <option value="3">积分</option>
                      <option value="2">博豆</option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="ass_fl s1" id="businessSelect" >
                <div class="control-group">
                  <label for="businesstype" class="control-label">业务类型</label>
                  <div class="controls">
                    <select name="businesstype" id="businesstype">
                      <option value="">全部</option>
                      <option value="101">推荐奖励</option>
                      <option value="102">管理奖励</option>
                      <option value="103">辅导奖励</option>
                      <option value="hlg">韩流馆</option>
                    </select>
                  </div>
                </div>
              </div>
              
              <div class="ass_fl s2" id="hlgSelect" style="display: none;">
                <div class="control-group">
                  <label for="hlgbusinesstype" class="control-label">具体业务</label>
                  <div class="controls">
                    <select name="hlgbusinesstype" id="hlgbusinesstype">
                      <option value="all">全部</option>
                      <option value="125">现金账户余额消费</option>
                      <option value="225">博豆账户余额消费</option>
                      <option value="113">现金冲正</option>
                      <option value="213">博豆冲正</option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="ass_fl" style="width: 20%;">
                <div class="control-group">
                  <button type="button" class="sui-btn sui-search" onclick="doQuery(1);">搜索</button>
                </div>
              </div>
            </form>
        </div>
        
        <div class="ibox white">	
            <div class="notice_none" style="display: none;">暂无数据!</div>
            <table class="table_ui" border="0" cellpadding="0" cellspacing="1"></table>
            <%@ include file="/WEB-INF/views/pageLink.jsp" %>
        </div>
    </div>
  </div>
</div>
</body>
</html>