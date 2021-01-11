<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
 

	Store_MemService store_MemSvc = new Store_MemService();
    List<Store_MemVO> list = store_MemSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有店家資料 - listAllStore_Mem.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有店家資料 - listAllStore_Mem.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-store-end/store/select_page.jsp"><img src="<%=request.getContextPath()%>/front-store-end/store/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
			     <input type="submit" value="修改">
			     <input type="hidden" name="store_no"  value="${store_MemVO.store_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td> 
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" style="margin-bottom: 0px;">
			  	 <input type="submit" value="刪除">
			     <input type="hidden" name="store_no"  value="${store_MemVO.store_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>