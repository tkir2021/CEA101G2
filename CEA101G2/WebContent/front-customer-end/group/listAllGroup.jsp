<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Group_Service group_Svc = new Group_Service();
	List<Group_VO> list = group_Svc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有揪團資料 - listAllGroup.jsp</title>

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
	width: 800px;
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

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有揪團資料 - listAllGroup.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

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
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="group_VO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${group_VO.group_no}</td>
				<td>${group_VO.mem_no}</td>
				<td>${group_VO.store_no}</td>
				<td>${group_VO.booking_no}</td>
				<td>${(group_VO.gp_status == "1")? "公開" : "私密"}</td>
				<td>${group_VO.mem_least}</td>
				<td>${group_VO.mem_limit}</td>
				<td>${group_VO.dining_date}</td>
				<td>${group_VO.start_date}</td>
				<td>${group_VO.end_date}</td>
				<td>${group_VO.gp_info}</td>
				<td>${(group_VO.gp_status == "1")? "成功" : "失敗"}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/group/group.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="group_no" value="${group_VO.group_no}"> <input type="hidden"
							name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/group/group.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="group_no" value="${group_VO.group_no}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>