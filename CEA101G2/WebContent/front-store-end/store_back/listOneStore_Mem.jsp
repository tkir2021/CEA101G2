<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.store.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  Store_MemVO store_MemVO = (Store_MemVO) request.getAttribute("store_MemVO"); //Food_ListServlet.java(Controller), �s�Jreq��food_ListVO����
%>

<html>
<head>
<title>���a��� - listOneFood_List.jsp</title>

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
	width: 600px;
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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���a��� - listOneStore_Mem.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-store-end/store/select_page.jsp"><img src="<%=request.getContextPath()%>/front-store-end/store/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

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
	<tr>

		<td><%=store_MemVO.getStore_no()%></td>
		<td><%=store_MemVO.getStore_acct()%></td>
		<td><%=store_MemVO.getStore_pwd()%></td>
		<td><%=store_MemVO.getStore_name()%></td>
		<td><%=store_MemVO.getAddr()%></td>
		<td><%=store_MemVO.getOpen_dates()%></td>
		<td><%=store_MemVO.getEmail()%></td>
		<td><%=store_MemVO.getS_category()%></td>
		<td><%=store_MemVO.getStore_info()%></td>
		<td><%=store_MemVO.getUpload_status()%></td>
		<td><%=store_MemVO.getS_permission()%></td>
		<td><%=store_MemVO.getSum_grade()%></td>
		<td><%=store_MemVO.getBlocked()%></td>
		<td><%=store_MemVO.getStar_total()%></td>
		<td><%=store_MemVO.getStar_times()%></td>
		<td><%=store_MemVO.getTable_limit()%></td>
		<td><img id="displayImg" src="<%=request.getContextPath() %>/store/store.do?store_no=${store_MemVO.store_no}&action=getOneImage"></td>
	</tr>
</table>

</body>
</html>