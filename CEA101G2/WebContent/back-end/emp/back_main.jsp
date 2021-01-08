<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>


<html>
<%@include file="/back-end/leftbar.file"%>
<head>
<title></title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/emp/css/back_system/emp.css"
	type=" text/css">

</head>

<body class="ov-hid">

	<section class="main">
		<div class="emptodo">
			<span><%=session.getAttribute("emp_no")%> 您好</span>
		</div>
	</section>
	<!-- Sidebar End -->
	<%@ include file="/back-end/scriptpart.file"%>
	<!-- 自訂新增 -->
</body>
</head>
<body>

</body>
</html>