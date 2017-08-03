<!DOCTYPE html>
<%@ page language="java" contentType="application/msexcel; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	response.setHeader("Content-disposition","attachment; filename=draw.xls"); 
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
			<th>提现流水</th>
			<th>用户编号</th>
			<th>用户姓名</th>
			<th>申请时间</th>
			<th>提现金额</th>
			<th>手续费</th>
			<th>到账金额</th>
			<th>转化积分</th>
			<th>银行卡姓名</th>
			<th>银行卡卡号</th>
			<th>开户行</th>
			<th>银行卡所属城市</th>
			<th>状态</th>
			<th>备注</th>
		</tr>
		<c:forEach var="row" items="${rows}">  
	  		<tr>
	  			<td>${row.SERIAL_NO}</td>
				<td>${row.MEMBER_NO}</td>
				<td>${row.REAL_NAME}</td>
				<td><fmt:formatDate value="${row.CREATE_TIME}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatNumber value="${row.AMOUNT/100.0}" pattern="##.##" minFractionDigits="2" /></td>
				<td><fmt:formatNumber value="${row.FEE/100.0}" pattern="##.##" minFractionDigits="2" /></td>
				<td><fmt:formatNumber value="${row.ARRIVAL_AMOUNT/100.0}" pattern="##.##" minFractionDigits="2" /></td>
				<td>${row.JF}</td>
				<td>${row.CARD_OWNER_NAME}</td>
				<td>${row.CARD_NO}</td>
				<td>${row.BANK_NAME}</td>
				<td>${row.OPEN_BANK_CITY}</td>
				<td>${statusMap[fn:trim(row.STATUS)]}</td>
				<td>${row.MEMO}</td>
			</tr>
		</c:forEach>  
	</table>
</body>
</html>