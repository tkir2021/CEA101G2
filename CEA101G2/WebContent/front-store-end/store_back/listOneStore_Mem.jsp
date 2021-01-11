<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Store_MemVO store_MemVO = (Store_MemVO) request.getAttribute("store_MemVO"); //Food_ListServlet.java(Controller), 存入req的food_ListVO物件
%>

<html>
<head>
<title>店家資料 - listOneFood_List.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>店家資料 - listOneStore_Mem.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-store-end/store/select_page.jsp"><img src="<%=request.getContextPath()%>/front-store-end/store/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>店家編號</th>
		<th>登入帳號</th>
		<th>登入密碼</th>
		<th>店名</th>
		<th>地址</th>
		<th>一周營業日</th>
        <th>E-mail</th>
		<th>店家分類</th>
		<th>店家簡介</th>
		<th>上架狀態</th>
		<th>平台權限狀態</th>
		<th>累積檢舉評分</th>
		<th>被檢舉次數</th>
		<th>評價總分</th>
		<th>總評價次數</th>
		<th>桌位上限</th>
		<th>店家圖片</th>
		
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