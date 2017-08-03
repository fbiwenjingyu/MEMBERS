<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
  <%@ include file="/WEB-INF/views/taglibs.jsp" %>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="wrap">
  <jsp:include page="memu.jsp">
  	<jsp:param name="on" value="1"></jsp:param>
  </jsp:include>
  <div class="container">
    <div class="wrap_tit">
      <a href="#" class="on">首页</a>
    </div>
    <div class="wrap_cont">
        <div class="ibox white">
            <h4 class="ibox_tit">我的资产</h4>
            <div class="my_asset">
                <span>现金：<b id="balance"></b></span>
                <span>积分：<b id="generalizeScore"></b></span>
                <span>博豆：<b id="shoppingScore"></b></span>
            </div>
        </div>
        <div class="ibox white">
            <h4 class="ibox_tit">最新公告</h4>
            
            <c:if test="${fn:length(gdm.rows) == 0 }">
				<div class="notice_none">暂无公告!</div>
			</c:if>            
            <c:if test="${fn:length(gdm.rows) != 0 }">
	            <ul class="new_notice">
	            	<c:forEach var="item" items="${gdm.rows}">
	            	<li><a href='notice/detail?noticeId=${item["ID"] }'>
	                    <i>&#xe605;</i>
	                    <p class="ellipsis">${item["TITLE"] }</p>
	                    <span><fmt:formatDate value="${item.DEPLOY_TIME }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
	                  </a></li>
					</c:forEach>
	            </ul>
	            
	            <jsp:include page="pageLinkAlone.jsp">
	   				<jsp:param name="page" value="${po.currPage }"></jsp:param>
	   				<jsp:param name="pageSize" value="${po.pageSize }"></jsp:param>
	   				<jsp:param name="sum" value="${gdm.total }"></jsp:param>
	   				<jsp:param name="url" value="${ctx }/toIndex"></jsp:param>
	    		</jsp:include>
    		</c:if>
        </div> 
    </div>
  </div>
</div>
<script type="text/javascript">
$(function(){
	$.post("<%= request.getContextPath() %>/user/getAccount",{}, function(data) {
		$("#balance").html(fmoney(data.balance,2) +"元");
		$("#generalizeScore").html(score(data.generalizeScore) + "分");
		$("#shoppingScore").html(score(data.shoppingScore) + "个");
	}, "json");
});

function score(s){
	if(s == ''){
		return 0;
	}else{
		return s;
	}
}

function fmoney(s, n) {
	  if(s == ''){
		  return 0.00;
	 }else{
	  n = n > 0 && n <= 20 ? n : 2;
	  f = s < 0 ? "-" : ""; //判断是否为负数
	  s = s/100
	  s = parseFloat((Math.abs(s) + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";//取绝对值处理, 更改这里n数也可确定要保留的小数位
	  var l = s.split(".")[0].split("").reverse(),
	  r = s.split(".")[1];
	  t = "";
	  for(i = 0; i < l.length; i++ ) {
	     t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	  }
	  return f + t.split("").reverse().join("") + "." + r.substring(0,2);//保留2位小数  如果要改动 把substring 最后一位数改动就可
	}
}
</script>
</body>
</html>