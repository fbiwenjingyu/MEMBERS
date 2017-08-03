<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${css}/vip.css">
</head>
<body>
<div class="dialogPage">
	<div class="om-panel-header">查看详情</div>
	<div class="editDiv">
		<div class="subtitle">银行卡信息</div>
        <div class="desc spedesc">
          <span id="c1">开户人：${model.cardName}</span>
          <span id="c2">开户行：${model.bankName}</span>
          <span id="c3">开户支行：${model.subbranchBank}</span>
          <span id="c4">银行卡号：${model.cardNo}</span>
        </div>
        <div class="subtitle">基本资料信息</div>
        <div class="desc spedesc">
          <span id="b1">姓名：${model.realName}</span>
          <span id="b2">联系电话：${model.phone}</span>
          <span id="b3">地址：${model.province}${model.city}${model.area}${model.fullAddress}</span>
          <span>邀请人：${model.recommendName}</span>
          <span>等级：${level}</span>
        </div>
	   <div class="editBtn">
			<button type="button" class="button" onclick="javascript:art.dialog.close();">&nbsp;关闭&nbsp;</button>
		</div>
	</div>
</div>
</body>
</html>