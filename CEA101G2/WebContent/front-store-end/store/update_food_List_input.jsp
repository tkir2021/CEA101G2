<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.food_list.model.*"%>

<%
	Food_ListVO food_ListVO = (Food_ListVO) request.getAttribute("food_ListVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<%@ include file="header.file" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�\�I��ƭק� - update_food_List_input.jsp</title>

<style>
	body{
	overflow-x:hidden;
	}
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
  .submit{
  margin-left:565px;	
  }
  .footer{
  top:80px;
  }
</style>

<style>
  table {
  	margin: 0 auto;
	width: 450px;
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
   h3{
  position:relative;
  left:50%;
  margin-left:-100px;
  }
</style>

</head>
<body bgcolor='white'>
<section>
<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/food.do" name="form1">
<table>
	<tr>
		<td>�\�I�s��:<font color=red><b>*</b></font></td>
		<td><%=food_ListVO.getFood_no()%></td>
	</tr>
	<tr>
		<td>���a�s��:</td>
		<td><input type="TEXT" name="store_no" size="45" value="<%=food_ListVO.getStore_no()%>" /></td>
	</tr>
	<tr>
		<td>�\�I�W��:</td>
		<td><input type="TEXT" name="food_name" size="45"	value="<%=food_ListVO.getFood_name()%>" /></td>
	</tr>
	<tr>
		<td>���:</td>
		<td><input type="TEXT" name="food_price" size="45" type="text" value="<%=food_ListVO.getFood_price()%>" /></td>
	</tr>
	<tr>
		<td>����i�P��ƶq:</td>
		<td><input type="TEXT" name="limit_" size="45"	value="<%=food_ListVO.getLimit_()%>" /></td>
	</tr>
	<tr>
		<td>�\�I²��:</td>
		<td><input type="TEXT" name="food_info" size="45" value="<%=food_ListVO.getFood_info()%>" /></td>
	</tr>
	
<!-- 	<tr> -->
<!-- 		<td>�\�I���A:</td> -->
<%-- 		<td><input type="TEXT" name="food_status" size="45" value="<%=food_ListVO.getFood_status()%>" /></td> --%>
<!-- 	</tr> -->
		<jsp:useBean id="foodListSvc" scope="page" class="com.food_list.model.Food_ListService" />

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
	<tr>
		<td>�\�I���A:<font color=red><b>*</b></font></td>
		<td><select size="1" name="food_status">
			
				<option value="1"'selected':''} >�W�[</option>
				<option value="0"'selected':''} >�U�[</option>
			
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="food_no" value="<%=food_ListVO.getFood_no()%>">
<input class="submit" type="submit" value="�e�X�ק�"></FORM>
</section>
<%@ include file="footer.file" %> 
</body>
</html>


