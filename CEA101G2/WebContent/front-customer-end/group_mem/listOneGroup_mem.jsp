<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_mem.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<html>
<head>
<title>揪團資料 - listOneGroup_mem.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>揪團資料 - ListOneGroup_mem.jsp</h3>
				<h4>
					<a href="<%= request.getContextPath()%>/front-customer-end/group_mem/select_page.jsp"><img src="<%= request.getContextPath()%>/front-customer-end/group_mem/images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>揪團編號</th>
			<th>會員編號</th>

		</tr>

		<c:forEach var="group_memVO" items="${requestScope.oneGroupList}">
		<tr>
			<td>${group_memVO.group_no}</td>
			<td>${group_memVO.mem_no}</td>
		</tr>
		</c:forEach>
		
	</table>

</body>
</html>