<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.food_list.model.*"%>
<%@ page import="com.store.model.*"%>


<%
	String store_no =(String) session.getAttribute("store_no");
	Store_MemVO store_memVO = new Store_MemService().getOneStore_Mem(store_no);
	String store_no2 = store_memVO.getStore_no();


	pageContext.setAttribute("store_memVO",store_memVO);

	Food_ListVO food_ListVO = (Food_ListVO) request.getAttribute("food_ListVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>店家餐點資料新增 - addFood_List.jsp</title>
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
	width: 450px;
	background-color: white;
	margin:0 auto;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
footer{
	top:80px;
}
 .submit{
  margin-left:565px;	
  }
  h3{
  position:relative;
  left:50%;
  margin-left:-100px;
  display:inline-block;
  }
</style>

</head>
<body bgcolor='white'>

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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/food/food.do"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>店家編號:</td>
				<td><input type="TEXT" name="store_no" size="45"
					value="<%=store_no2 %>" readonly="true" /></td>
			</tr>
			<tr>
				<td>餐點名稱:</td>
				<td><input type="TEXT" name="food_name" size="45"
					value="<%=(food_ListVO == null) ? "xizza" : food_ListVO.getFood_name()%>" /></td>
			</tr>
			<tr>
				<td>售價:</td>
				<td><input type="TEXT" name="food_price" size="45"
					value="<%=(food_ListVO == null) ? "100" : food_ListVO.getFood_price()%>" />
				</td>
			</tr>
			<tr>
				<td>限制可銷售數量:</td>
				<td><input type="TEXT" name="limit_" size="45"
					value="<%=(food_ListVO == null) ? "100" : food_ListVO.getLimit_()%>" /></td>
			</tr>
			<tr>
				<td>餐點簡介:</td>
				<td><input type="TEXT" name="food_info" size="45"
					value="<%=(food_ListVO == null) ? "好吃的菜" : food_ListVO.getFood_info()%>" /></td>
			</tr>

			<jsp:useBean id="foodListSvc" scope="page"
				class="com.food_list.model.Food_ListService" />

			<!-- 	<tr> -->
			<!-- 		<td>餐點狀態:</td> -->
			<!-- 		<td><input type="TEXT" name="food_status" size="45" -->
			<%-- 			 value="<%= (food_ListVO==null)? "狀態" : food_ListVO.getFood_status()%>" /></td> --%>
			<!-- 	</tr> -->

			<tr>
				<td>餐點狀態:<font color=red><b>*</b></font></td>
				<td><select size="1" name="food_status">

						<option value="1" >上架</option>
						<option value="0" >下架</option>

				</select></td>
			</tr>
			<tr>
			
			
			
				<td>
				上傳圖片:<input type="file" name="food_img">
				
				</td>		

			</tr>
		</table>
<!-- 		<div name="uploadPic"></div> -->
		<br> <input type="hidden" name="action" value="insert"> 
		<input class="submit" type="submit" value="送出新增">
	</FORM>
	<%@ include file="footer.file" %> 
</body>
</html>