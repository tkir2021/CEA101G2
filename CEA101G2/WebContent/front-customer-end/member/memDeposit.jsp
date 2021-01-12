<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.mem.model.*"%>

<%
	String account = (String) session.getAttribute("account");
	Mem_DataVO mem_dataVO = new Mem_DataService().getMemAcc(account);
	pageContext.setAttribute("mem_dataVO", mem_dataVO);
%>


<!DOCTYPE html>
<html lang="zh-tw">
<%@ include file="/front-customer-end/member/header.file"%>

<head>
<meta name="description" content="Directing Template">
<meta name="keywords" content="Directing, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>儲值記錄</title>
<!-- css引用 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-customer-end/member/css/memDeposit.css" type="text/css">

</head>

<body>

	<!-- =======================================Content========================================== -->
	<section>
		<jsp:useBean id="depositSvc" scope="page" class="com.deposit.model.DepositService" />
<!-- 		<table> -->
<!-- 			<tr> -->
<!-- 				<th>儲值序號</th> -->
<!-- 				<th>會員編號</th> -->
<!-- 				<th>儲值金額</th> -->
<!-- 				<th>儲值時間</th> -->
<!-- 			</tr> -->
<%-- 			<c:forEach var="depositVO" items="${depositSvc.getOneMemDeposit(mem_dataVO.getMem_no())}"> --%>
<!-- 				<tr> -->
<%-- 					<td>${depositVO.getDeposit_no()}</td> --%>
<%-- 					<td>${depositVO.getMem_no()}</td> --%>
<%-- 					<td>${depositVO.getCharge()}</td> --%>
<%-- 					<td>${depositVO.getCharge_time()}</td> --%>
<!-- 				</tr> -->


<%-- 			</c:forEach> --%>

<!-- 		</table> -->

		<div class="table">

			<div class="row header blue">
				<div class="cell">儲值序號</div>
				<div class="cell">會員編號</div>
				<div class="cell">儲值金額</div>
				<div class="cell">儲值時間</div>
			</div>
<%-- ${depositVO.getMem_no()} --%>
			<c:forEach var="depositVO" items="${depositSvc.getOneMemDeposit(mem_dataVO.getMem_no())}">
			<div class="row">
				<div class="cell" data-title="Username">${depositVO.getDeposit_no()}</div>
				<div class="cell" data-title="Username">${depositVO.getMem_no()}</div>
				<div class="cell" data-title="Username">${depositVO.getCharge()}</div>
				<div class="cell" data-title="Username"><fmt:formatDate value="${depositVO.getCharge_time()}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
			</div>
			</c:forEach>


		</div>
	</section>

	<!-- 內容end 請在以上區塊做切版-->
	<!-- ===========================================Footer====================================== -->
	<%@ include file="footer.file"%>
	<script src="<%=request.getContextPath() %>/front-customer-end/member/js/jquery-3.3.1.min.js"></script>
	<!-- JS引用 -->
	<script>
		
	</script>
</body>

</html>