<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.food_list.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
<%
	String store_no= (String)session.getAttribute("store_no");
	Food_ListService foodListSvc = new Food_ListService();
    List<Food_ListVO> list = foodListSvc.getFoodFromOne(store_no);
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>�Ҧ��\�I��� </title>
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

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
<th>�\�I�s��</th>
		<th>���a�s��</th>
		<th>�\�I�W��</th>
		<th>���</th>
		<th>����i�P��q</th>
		<th>�\�I²��</th>
        <th>�\�I���A</th>
		<th>�ק�</th>
<!-- 		<th>�R��</th> -->
		 <th>�\�I�Ϥ�</th>
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
			<c:when test="${food_ListVO.food_status ==0}">�U�[</c:when>
			<c:otherwise>�W�[</c:otherwise>
			</c:choose></td>
			<td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/food.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
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