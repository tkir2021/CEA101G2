<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.booking.model.*"%>

<% 
	String storeno =request.getParameter("storeno");
	String memno = request.getParameter("memno");
	System.out.println(storeno);
	System.out.println(memno);
	
	HttpSession session1 = request.getSession();
	session1.setAttribute("storeno", storeno);
	session1.setAttribute("memno",memno);	
%>



<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Directing Template">
<meta name="keywords" content="Directing, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>BookingPage</title>
<!-- ↑↑↑↑請記得修改title為自己製作頁面的名稱↑↑↑ -->
<!-- css引用 -->

<!-- datepicker -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<!-- main CSS-->
<link rel="stylesheet" href="css/booking.css" type="text/css">
</head>

<body>

	<!-- 以下三行div為bootstrap的格線語法，請不要新增屬性、id，請參考格線語法自行更改 ((RWD課程應該會上))-->
	<!-- ===========================================Content====================================== -->
	<section>
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/front-customer-end/booking/order.do">
<!-- 						<b>會員：</b><input type="text" class="memno" name="memno" size="10" -->
<!-- 							value=""> <b>店家:</b><input type="text" class="storeno" -->
<!-- 							name="storeno" size="10"> -->
						
				
						<div class="whiteback">

							用餐人數：<select class="people" name="people">
								<%-- 						<c:forEach var=""></c:forEach> --%>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
							</select>
							<div class="bookingPart">
							請選擇用餐日期:
								<div class="calendar"></div>
								<input class="bookingDate" name="bookingdate" />
							</div>
							用餐時段： <select class="openHour" name="timeperiod">
								<option value="OP00000001">早餐</option>
								<option value="OP00000002">午餐</option>
								<option value="OP00000003">晚餐</option>
								<option value="OP00000004">消夜</option>
<%-- 													<c:forEach var="b_orderVO" items="${b_orderSvc.all}"> --%>
<%-- 														<option value="${s_orderVO.openno}" ${(param.openno==openhourVO.openno)? 'selected':''}>${openhourVO.openinfo}</option> --%>
<%-- 													</c:forEach> --%>
							</select><br>
							<input type="hidden" name="action" value="insert"> 
								<input type="submit" />
						</div>
					</FORM>
				</div>
			</div>
		</div>
	</section>
	
	<!-- footer end 以上內容待置換-->
	<!-- JS引用 -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<!-- datepicker -->
	<script src="js/jquery-booking.js"></script>
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	
</body>

</html>