<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.food_list.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
	String store_no= (String)session.getAttribute("store_no");
	Food_ListService foodListSvc = new Food_ListService();
    List<Food_ListVO> list = foodListSvc.getFoodFromOne(store_no);
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有餐點資料 </title>
<%@ include file="header.file" %>
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
	margin:0 auto 140px auto;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  
   #displayImg{
   width: 150px;
   height: 150px;
  }
</style>

</head>
<body bgcolor='white'>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
<th>餐點編號</th>
		<th>店家編號</th>
		<th>餐點名稱</th>
		<th>售價</th>
		<th>限制可銷售量</th>
		<th>餐點簡介</th>
        <th>餐點狀態</th>
		<th>修改</th>
<!-- 		<th>刪除</th> -->
		 <th>餐點圖片</th>
	</tr>
	
	<c:forEach var="food_ListVO" items="${list}" >
		
		<tr>
			<td>${food_ListVO.food_no}</td>
			<td>${food_ListVO.store_no}</td>
			<td>${food_ListVO.food_name}</td>
			<td>${food_ListVO.food_price}</td>
			<td>${food_ListVO.limit_}</td>
			<td>${food_ListVO.food_info}</td> 
			<td><c:choose>
			<c:when test="${food_ListVO.food_status ==0}">下架</c:when>
			<c:otherwise>上架</c:otherwise>
			</c:choose></td>
			<td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/food.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="food_no"  value="${food_ListVO.food_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td><img id="displayImg" src="<%=request.getContextPath() %>/store/food.do?food_no=${food_ListVO.food_no}&action=getOneImage"></td>
		</tr>
	</c:forEach>
</table>
<%@ include file="footer.file" %> 
</body>
</html>