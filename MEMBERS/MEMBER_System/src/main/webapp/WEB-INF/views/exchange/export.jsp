<!DOCTYPE html>
<%@ page language="java" contentType="application/msexcel; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Content-disposition","attachment; filename=exchange.xls"); 
%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html xmlns:x="urn:schemas-microsoft-com:office:excel">
<head>
<!-- 网格线 -->
<!--[if gte mso 9]>
<xml>
    <x:ExcelWorkbook>
        <x:ExcelWorksheets>
            <x:ExcelWorksheet>
                <x:Name>工作表标题</x:Name>
                <x:WorksheetOptions>
                    <x:Print>
                        <x:ValidPrinterInfo />
                    </x:Print>
                </x:WorksheetOptions>
            </x:ExcelWorksheet>
        </x:ExcelWorksheets>
    </x:ExcelWorkbook>
</xml>
<![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style>
	/*
	table, th, td {
	    border: 1px solid #000;
	}
	*/
	
	/*屏蔽科学计数法*/
	td {
	    mso-number-format: '\@';
	}
</style>
</head>
<body>
	<table>
		<tr>
			<th>兑换物品</th>
			<th>用户名称</th>
			<th>用户编号</th>
			<th>申请时间</th>
			<th>兑换数量</th>
			<th>收货人姓名</th>
			<th>收货人电话</th>
			<th>地址</th>
			<th>发货时间</th>
			<th>发货人</th>
			<th>快递公司</th>
			<th>发货单号</th>
			<th>发货状态</th>
		</tr>
		<c:forEach var="row" items="${rows}">  
	  		<tr>
	  			<th>${goodTypeMap[fn:trim(row.goodType)]}</th>
	  			<td>${row.userName}</td>
				<td>${row.accountNo}</td>
				<td><fmt:formatDate value="${row.applyTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${row.exchangeNum}</td>
				<td>${row.recepitUserName}</td>
				<td>${row.recepitPhone}</td>
				<td>${row.province}${row.city}${row.area}${row.fullAddress}</td>
				<td><fmt:formatDate value="${row.sendTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${row.sendUser}</td>
				<td>${row.expressCompany}</td>
				<td>${row.expressOrder}</td>
				<td>${stateCombo[fn:trim(row.state)]}</td>
			</tr>
		</c:forEach>  
	</table>
</body>
</html>