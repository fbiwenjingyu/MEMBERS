<!DOCTYPE html>
<%@ page language="java" contentType="application/msexcel; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Content-disposition","attachment; filename=recharge.xls"); 
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
                <x:Name>recharge</x:Name>
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
			<th>充值流水</th>
			<th>用户编号</th>
			<th>充值时间</th>
			<th>账户类型</th>
			<th>充值数值</th>
			<th>充值类型</th>
			<th>充值方式</th>
			<th>状态</th>
			<th>操作员</th>
			<th>备注</th>
		</tr>
		<c:forEach var="row" items="${rows}">  
	  		<tr>
	  			<td>${row.SERIAL_NO}</td>
				<td>${row.MEMBER_NO}</td>
				<td><fmt:formatDate value="${row.CREATE_TIME}"  pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${accountType[fn:trim(row.ACCOUNT_TYPE)]}</td>
				<td>
					<c:if test="${row.ACCOUNT_TYPE eq 1 }">
						<fmt:formatNumber value="${row.AMOUNT/100.0}" pattern="##.##" minFractionDigits="2" />
					</c:if>
					<c:if test="${not (row.ACCOUNT_TYPE eq 1) }">
						${row.AMOUNT}
					</c:if>
				</td>
				<td>${rechargeForward[fn:trim(row.FORWARD)]}</td>
				<td>${rechargeWay[fn:trim(row.WAY)]}</td>
				<td>${rechargeStatus[fn:trim(row.STATUS)]}</td>
				<td>${row.OPERATOR}</td>
				<td>${row.MEMO}</td>
			</tr>
		</c:forEach>  
	</table>
</body>
</html>