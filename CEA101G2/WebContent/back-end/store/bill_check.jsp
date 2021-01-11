<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bill.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%
    BillService billSvc = new BillService();
	List<BillVO> list = billSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<%@include file="/back-end/leftbar.file"%>
<head>
<meta charset="UTF-8">
<title>帳務管理</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/store/css/mike.css" type="text/css">
</head>
<body>
<section>
	<h2>帳務管理</h2>
<table id="customers">
	<tr>
		<th>傳票編號</th>
		<th>店家編號</th>
		<th>付款總額</th>
		<th>付款日期</th>
		<th>帳務期間</th>
	</tr>
	
	<c:forEach var="billVO" items="${list}" >
	
		<tr>
			<td>${billVO.bill_no}</td>
			<td>${billVO.store_no}</td>
			<td>${billVO.bill_price}</td>
			<td><fmt:formatDate value="${billVO.bill_date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${billVO.bill_period}</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/back-end/scriptpart.file"%>	
</section>
</body>
</html>