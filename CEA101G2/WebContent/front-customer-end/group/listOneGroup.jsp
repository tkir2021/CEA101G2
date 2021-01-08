<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.group.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	Group_VO group_VO = (Group_VO) request.getAttribute("group_VO"); //Group_Servlet.java(Concroller), 存入req的Group_VO物件
%>

<html>
<head>
<title>揪團資料 - listOneGroup.jsp</title>

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
				<h3>揪團資料 - ListOneGroup.jsp</h3>
				<h4>
					<a href="<%= request.getContextPath()%>/front-customer-end/group/select_page.jsp"><img src="<%= request.getContextPath()%>/front-customer-end/group/images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>揪團編號</th>
			<th>會員編號</th>
			<th>店家編號</th>
			<th>訂位編號</th>
			<th>揪團分類</th>
			<th>參團人數(下限)</th>
			<th>參團人數(上限)</th>
			<th>用餐日期</th>
			<th>開團日</th>
			<th>截團日</th>
			<th>揪團簡介</th>
			<th>揪團狀態</th>
		</tr>
		<tr>
			<td><%=group_VO.getGroup_no()%></td>
			<td><%=group_VO.getMem_no()%></td>
			<td><%=group_VO.getStore_no()%></td>
			<td><%=group_VO.getBooking_no()%></td>
			<td><%=group_VO.getGp_kind()%></td>
			<td><%=group_VO.getMem_least()%></td>
			<td><%=group_VO.getMem_limit()%></td>
			<td><%=group_VO.getDining_date()%></td>
			<td><%=group_VO.getStart_date()%></td>
			<td><%=group_VO.getEnd_date()%></td>
			<td><%=group_VO.getGp_info()%></td>
			<td><%=group_VO.getGp_status()%></td>
		</tr>
	</table>

</body>
</html>