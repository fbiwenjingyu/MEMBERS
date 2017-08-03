<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>大博智家会员系统</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<div class="wrap">
  <jsp:include page="../memu.jsp">
  	<jsp:param name="on" value="2"></jsp:param>
  </jsp:include>
  <div class="container">
    <div class="wrap_tit">
      <a href="${ctx}/message/goMessage" class="on">消息中心</a>
      <%-- <a href="${ctx}/message/goHelp">帮助中心</a> --%>
    </div>
    <div class="wrap_cont tab-pane active">
        <div class="ibox white">
            <h4 class="ibox_tit">最新消息<a href="${ctx}/message/addOpinion" class="tit_a" id="feedback_btn">留言</a></h4>  
            <c:if test="${data.total == 0}">
            <div class="notice_none">暂无任何消息</div>
            </c:if>
            <c:if test="${data.total != 0}">
            <ul class="msg_box">
            <c:forEach items="${data.rows}" var="item">
                <li>
                	<a href="${ctx}/message/toReply/${item.id}">
	                    <i>&#xe605;</i>
	                    <b class="ellipsis">标题：${item.title}</b>
	                    <p class="ellipsis">${item.comtent}</p>
	                    <span>
	                      	<c:if test="${item.isNoRead eq 1}"><em></em></c:if>
	                    	<fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
	                    </span>
                 	</a>
                 </li>
            </c:forEach>
            </ul>
		        <div class="page_box">
					<table width="100%">
						<tr>
          				 <c:if test="${data.total != 0}">
							<td align="right">
              <em id="page-desc">共<span id="sum">${data.total}</span>条记录</em>
              <div id="paging" class="page"></div>
								
								<input type="hidden" value ="${currentPage}" id="curPage">
							</td>
						</c:if>
						</tr>
					</table>
				</div>
           </c:if>
           
			
		</div>
    </div>
  </div>
 </div>
</body>
<script>
//初始化分页
function pageInit(sum, pageSize){
  $("#paging").pagination({
      items: sum,//总记录数
      itemsOnPage: pageSize,   
      currentPage: parseInt($('#curPage').val()) || 1,		  //当前页
      cssStyle: 'compact-theme',							  //插件样式
      onPageClick: pageHandler							  //点击分页按钮
   });
}
function pageHandler(pageNumber){
	//自定义处理查询分页函数
	doQuery(pageNumber);
}
//点击按钮分页 ，远程获取数据
function doQuery(pageNumber){
	
	pageNumber = pageNumber || 1;
	window.location.href = ctx+'message/goMessage?pageNumber='+pageNumber;
	   
}
var sum = $("#sum").html();
pageInit(sum,10);
</script>
</html>