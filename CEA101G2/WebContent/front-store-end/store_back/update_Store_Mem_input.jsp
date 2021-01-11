<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>

<%
Store_MemVO store_MemVO = (Store_MemVO) request.getAttribute("store_MemVO"); 
%>
<%= store_MemVO==null %>--${store_MemVO.store_no}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���a��ƭק� - update_Store_Mem_input.jsp</title>

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
	<tr><td>
		 <h3>���a��ƭק� - update_Store_Mem_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-store-end/store/select_page.jsp"><img src="<%=request.getContextPath()%>/front-store-end/store/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>���a��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" name="form1">
<table>
	<tr>
		<td>���a�s��:<font color=red><b>*</b></font></td>
		<td><%=store_MemVO.getStore_no()%></td>
	</tr>
	<tr>
		<td>�n�J�b��:</td>
		<td><input type="TEXT" name="store_acct" size="45" value="<%=store_MemVO.getStore_acct()%>" /></td>
	</tr>
	<tr>
		<td>�n�J�K�X:</td>
		<td><input type="TEXT" name="store_pwd" size="45"	value="<%=store_MemVO.getStore_pwd()%>" /></td>
	</tr>
	<tr>
		<td>���W:</td>
		<td><input type="TEXT" name="store_name" size="45" type="text" value="<%=store_MemVO.getStore_name()%>" /></td>
	</tr>
	<tr>
		<td>�a�}:</td>
		<td><input type="TEXT" name="addr" size="45"	value="<%=store_MemVO.getAddr()%>" /></td>
	</tr>
	<tr>
		<td>�@�P��~��:</td>
		<td><input type="TEXT" name="open_dates" size="45" value="<%=store_MemVO.getOpen_dates()%>" /></td>
	</tr>
	
	<tr>
		<td>E-mail:</td>
		<td><input type="TEXT" name="email" size="45" value="<%=store_MemVO.getEmail()%>" /></td>
	</tr>
	
	<tr>
		<td>���a����:</td>
		<td><input type="TEXT" name="s_category" size="45" value="<%=store_MemVO.getS_category()%>" /></td>
	</tr>
	
	<tr>
		<td>���a²��:</td>
		<td><input type="TEXT" name="store_info" size="45" value="<%=store_MemVO.getStore_info()%>" /></td>
	</tr>
	
	<tr>
		<td>�W�[���A:</td>
		<td><input type="TEXT" name="upload_status" size="45" value="<%=store_MemVO.getUpload_status()%>" /></td>
	</tr>
	
	<tr>
		<td>���x�v�����A:</td>
		<td><input type="TEXT" name="s_permission" size="45" value="<%=store_MemVO.getS_permission()%>" /></td>
	</tr>
	
	<tr>
		<td>�֭p���|����:</td>
		<td><input type="TEXT" name="sum_grade" size="45" value="<%=store_MemVO.getSum_grade()%>" /></td>
	</tr>
	
	<tr>
		<td>�Q���|����:</td>
		<td><input type="TEXT" name="blocked" size="45" value="<%=store_MemVO.getBlocked()%>" /></td>
	</tr>
	
	<tr>
		<td>�����`��:</td>
		<td><input type="TEXT" name="star_total" size="45" value="<%=store_MemVO.getStar_total()%>" /></td>
	</tr>
	
	<tr>
		<td>�`��������:</td>
		<td><input type="TEXT" name="star_times" size="45" value="<%=store_MemVO.getStar_times()%>" /></td>
	</tr>
	
	<tr>
		<td>���W��:</td>
		<td><input type="TEXT" name="table_limit" size="45" value="<%=store_MemVO.getTable_limit()%>" /></td>
	</tr>
		
		
		<jsp:useBean id="store_MemSvc" scope="page" class="com.store.model.Store_MemService" />
		
		
		

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>�\�I���A:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="food_status"> -->
			
<!-- 				<option value="0"'selected':''} >�W�[</option> -->
<!-- 				<option value="1"'selected':''} >�U�[</option> -->
			
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="store_no" value="<%=store_MemVO.getStore_no()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>



