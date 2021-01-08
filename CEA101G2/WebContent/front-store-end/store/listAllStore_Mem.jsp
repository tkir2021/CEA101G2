<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
<%
 

	Store_MemService store_MemSvc = new Store_MemService();
    List<Store_MemVO> list = store_MemSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����a��� - listAllStore_Mem.jsp</title>

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
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����a��� - listAllStore_Mem.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-store-end/store/select_page.jsp"><img src="<%=request.getContextPath()%>/front-store-end/store/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

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
		<th>���a�s��</th>
		<th>�n�J�b��</th>
		<th>�n�J�K�X</th>
		<th>���W</th>
		<th>�a�}</th>
		<th>�@�P��~��</th>
        <th>E-mail</th>
		<th>���a����</th>
		<th>���a²��</th>
		<th>�W�[���A</th>
		<th>���x�v�����A</th>
		<th>�ֿn���|����</th>
		<th>�Q���|����</th>
		<th>�����`��</th>
		<th>�`��������</th>
		<th>���W��</th>
		<th>���a�Ϥ�</th>
		
	</tr>
	
	<c:forEach var="store_MemVO" items="${list}" >
		
		<tr>
			  	
				<td>${store_MemVO.store_no};</td>
				<td>${store_MemVO.store_acct}</td>
				<td>${store_MemVO.store_pwd}</td>
				<td>${store_MemVO.store_name}</td>
				<td>${store_MemVO.addr}</td>
				<td>${store_MemVO.open_dates}</td>
				<td>${store_MemVO.email}</td>
				<td>${store_MemVO.s_category}</td>
				<td>${store_MemVO.store_info}</td>
				<td>${store_MemVO.upload_status}</td>
				<td>${store_MemVO.s_permission}</td>
				<td>${store_MemVO.sum_grade}</td>
				<td>${store_MemVO.blocked}</td>
				<td>${store_MemVO.star_total}</td>
				<td>${store_MemVO.star_times}</td>
				<td>${store_MemVO.table_limit}</td>
				<td><img id="displayImg" src="<%=request.getContextPath() %>/store/store.do?store_no=${store_MemVO.store_no}&action=getOneImage"></td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="store_no"  value="${store_MemVO.store_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td> 
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" style="margin-bottom: 0px;">
			  	 <input type="submit" value="�R��">
			     <input type="hidden" name="store_no"  value="${store_MemVO.store_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>