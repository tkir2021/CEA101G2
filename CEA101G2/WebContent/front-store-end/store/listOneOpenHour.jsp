<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.storeOpen.model.*"%>
 
<!DOCTYPE html>
<html lang="zxx">
<head>
 <title>Store_Openhour</title>    
    <!-- 自定義的css -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-store-end/store/css/openHour.css" type="text/css">    
    <!-- timepickerCSS -->
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-store-end/store/css/jquery.timepicker.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-store-end/store/css/jquery.timepicker.min.css">
</head>
<%@ include file="header.file" %>

	
	<!-- header end 以上內容待置換-->
	<section>

		<div class="whiteback">
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/store/openHour.do">
				<div class="addTime">
					<h4>新增營業時間</h4>
					<p>
						<b>開始時間:</b>
					</p>
					<input class="timepicker1" placeholder="請選擇開始時間" name="opentime" autocomplete="off"><br>
					<p>
						<b>結束時間:</b>
					</p>
					<input class="timepicker2" placeholder="請選擇結束時間" name="closetime" autocomplete="off"><br>
					<input class="submit" type="submit" value="新增"> <input
						type="hidden" name="action" value="insert">
				</div>
			</FORM>

			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<fieldset class="time">
				<legend>您的營業時段</legend>
				<c:forEach var="openVO" items="${openlist}">
					<div>
						<p class="opentime">${openVO.timeperiod}</p>
						<FORM class="deleteform" METHOD="post"
							ACTION="<%=request.getContextPath()%>/store/openHour.do">
							<input type="button" class="delete" value="刪除">
							 <input
								type="hidden" name="storeno" value="${openVO.storeno}">
							<input type="hidden" name="timeperiod"
								value="${openVO.timeperiod}">
								 <input type="hidden"
								class="godelete" name="action" value="delete">
						</FORM>
					</div>

				</c:forEach>
			</fieldset>
			
		</div>
	</section>
	<!-- 內容end 請在以上區塊做切版-->
	<!-- ===================================Footer====================================== -->
	<%@ include file="footer.file" %> 

	<script src="<%=request.getContextPath() %>/front-store-end/store/js/jquery.timepicker.js"></script>
	<script src="<%=request.getContextPath() %>/front-store-end/store/js/jquery.timepicker.min.js"></script>
	<script src="<%=request.getContextPath() %>/front-store-end/store/js/jquery-storeOpenhour.js"></script>
	
	<script>
		$(document).ready(function() {
			$(".delete").click(function() {
				let yes =confirm('你確定要刪除嗎？');
				if (yes) {
					$(".deleteform").submit();
				} else {
					alert("取消刪除");
				}
			});
		});
	</script>
	
</body>

</html>