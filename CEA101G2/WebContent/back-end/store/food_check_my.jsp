<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.food_list.model.*"%>
<!DOCTYPE html>
<%
	Food_ListService foodlistSvc = new Food_ListService();
	List<Food_ListVO> list = foodlistSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<%@include file="/back-end/leftbar.file"%>
<meta charset="UTF-8">
<head>
<title>商品上架審核</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/store/css/mike.css" type="text/css">
<style>
#displayImg {
	width: 60px;
	height: 50px;
}
</style>
</head>
<body>
<section>
	<h2>商品上架審核</h2>
	<table id="customers">
		<tr>
			<th>餐點編號</th>
			<th>店家編號</th>
			<th>餐點名稱</th>
			<th>餐點簡介</th>
			<th>餐點圖片</th>
			<th>餐點狀況</th>
			<th>審核</th>
		</tr>
		<%@ include file="page1.file" %> 
		<c:forEach var="food_ListVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/food/food.do" name="form1">
			<tr>
				<td>${food_ListVO.food_no}</td>
				<td>${food_ListVO.store_no}</td>
				<td>${food_ListVO.food_name}</td>
				<td>${food_ListVO.food_info}</td>
				<td><img id="displayImg"
					src="<%=request.getContextPath() %>/food/food.do?food_no=${food_ListVO.food_no}&action=getOneImage"></td>
				<td>
					<c:if test="${food_ListVO.food_status == 0}">
					【未審核】
					</c:if>
					<c:if test="${food_ListVO.food_status == 1}">
					【已審核】
					</c:if>
				</td>

				<td>
				<input type="hidden" name="action" value="update_food_status">
				<input type="hidden" name="food_no" value="${food_ListVO.food_no}">
				<input type="submit" value="審核確認" ${food_ListVO.food_status == 1 ? 'disabled':''}>
				</td>
			</tr>
		</FORM>
			
		</c:forEach>
	</table>
	<%@ include file="page2.file" %>
	<%@ include file="/back-end/scriptpart.file"%>	
	<section>
</body>
</html>
