<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_mem.model.*"%>
<%@ page import="com.group.model.*"%>
<%@ page import="java.util.*"%>

<%
	Group_memVO group_memVO = (Group_memVO) request.getAttribute("group_memVO"); //Group_memServlet.java(Concroller), 存入req的Group_memVO物件

	Group_Service group_Svc = new Group_Service();
	List<Group_VO> list = group_Svc.getAll();
	pageContext.setAttribute("list", list);
%>
<%=group_memVO == null%>--${group_memVO.group_no}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>揪團資料新增 - addGroup_mem.jsp</title>

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
	width: 200px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>揪團資料新增 - addGroup_mem.jsp</h3>
			</td>
			<td>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-customer-end/group/select_page.jsp"><img
						src="<%=request.getContextPath()%>/front-customer-end/group/images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/group_mem/group_mem.do"
		name="form1">
		<table>
			<!-- 			<tr> -->
			<!-- 				<td>揪團編號：</td> -->
			<!-- 				<td><input type="TEXT" name="store_no" size="10" maxlength="10" -->
			<!-- 					placeholder="GP00000001" -->
			<%-- 					value="<%=(group_memVO == null) ? "" : group_memVO.getGroup_no()%>" /></td> --%>
			<!-- 			</tr> -->


			<tr>
				<td>揪團編號：</td>
<!-- 			取出只有成功(狀態=1)的揪團資料 -->
				<td><select size="1" name="group_no">
						<c:forEach var="group_VO" items="${list}">
							<c:if test="${group_VO.gp_status == 1}">
								<option value="${group_VO.group_no}">${group_VO.group_no}
								</option>
							</c:if>
						</c:forEach>
				</select></td>

			</tr>


			<tr>
				<td>會員編號：</td>
				<td><input type="TEXT" name="mem_no" size="10" maxlength="10"
					placeholder="MM00000001"
					value="<%=(group_memVO == null) ? "" : group_memVO.getMem_no()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-customer-end/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/front-customer-end/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/front-customer-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; 
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px;
}

</style>

<script>

</script>
</html>