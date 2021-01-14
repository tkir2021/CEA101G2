<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>

<%
String store_no = (String)session.getAttribute("store_no");
Store_MemService storeSvc =new Store_MemService();
Store_MemVO store_MemVO = storeSvc.getOneStore_Mem(store_no); 
%>

<%@ include file="header.file" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>店家資料修改 - update_Store_Mem_input.jsp</title>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
	margin:0 auto;
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
  
  footer{
  top:0px!important;
  }
  
  #send{
  	margin-left: 650px;
  }
  
  .red{
   color:red;
  }
</style>

</head>
<body bgcolor='white'>
<!-- Page Preloder -->
    <div id="preloder">
        <img class="loaderpic" src="<%=request.getContextPath() %>/index/img/logo-07.png" alt="">
        <div class="loader"></div>
    </div>
<h3>店家資料修改:</h3>
<section>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<jsp:useBean id="storeSvc2" scope="page" class="com.store.model.Store_MemService" />

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" name="form1">
<table>
	<tr>
		<td>店家編號:<font color=red></font></td>
		<td>${storeSvc2.getOneStore_Mem(store_no).store_no}</td>
	</tr>
	<tr>
		<td>登入帳號:</td>
		<td><input type="TEXT" name="store_acct" size="45" value="<%=store_MemVO.getStore_acct()%>" readonly="true" /></td>
	</tr>
	<tr>
		<td><b class="red">*</b>登入密碼:</td>
		<td><input type="TEXT" name="store_pwd" size="45"	value="<%=store_MemVO.getStore_pwd()%>" /></td>
	</tr>
	<tr>
		<td><b class="red">*</b>店名:</td>
		<td><input type="TEXT" name="store_name" size="45" type="text" value="<%=store_MemVO.getStore_name()%>" /></td>
	</tr>
	<tr>
		<td><b class="red">*</b>地址:</td>
		<td><input type="TEXT" name="addr" size="45"	value="<%=store_MemVO.getAddr()%>" /></td>
	</tr>
	<tr>
		<td><b class="red">*</b>一周營業日:</td>
		<td><input type="TEXT" name="open_dates" size="45" placeholder="營業日請設定1，非營業日請設定0。EX：1111111" pattern="^[0-1]{7}[\d]*$" title="只能數字1或0" value="<%=store_MemVO.getOpen_dates()%>" maxlength=7/></td>
	</tr>
	
	<tr>
		<td><b class="red">*</b>E-mail:</td>
		<td><input type="TEXT" name="email" size="45" value="<%=store_MemVO.getEmail()%>" /></td>
	</tr>
	
	<tr>
		<td><b class="red">*</b>店家分類:</td>
		<td><input type="TEXT" name="s_category" size="45" value="<%=store_MemVO.getS_category()%>" /></td>
	</tr>
	
	<tr>
		<td><b class="red">*</b>店家簡介:</td>
		<td><input type="TEXT" name="store_info" size="45" value="<%=store_MemVO.getStore_info()%>" /></td>
	</tr>
	
	<tr>
		<td>上架狀態:</td>
		<td><input type="TEXT" name="upload_status" size="45" value="<%=store_MemVO.getUpload_status()%>" readonly="true" /></td>
	</tr>
	
	<tr>
		<td>平台權限狀態:</td>
		<td><input type="TEXT" name="s_permission" size="45" value="<%=store_MemVO.getS_permission()%>" readonly="true" /></td>
	</tr>
	
	<tr>
		<td>累計檢舉評分:</td>
		<td><input type="TEXT" name="sum_grade" size="45" value="<%=store_MemVO.getSum_grade()%>" readonly="true" /></td>
	</tr>
	
	<tr>
		<td>被檢舉次數:</td>
		<td><input type="TEXT" name="blocked" size="45" value="<%=store_MemVO.getBlocked()%>" readonly="true" /></td>
	</tr>
	
	<tr>
		<td>評價總分:</td>
		<td><input type="TEXT" name="star_total" size="45" value="<%=store_MemVO.getStar_total()%>" readonly="true" /></td>
	</tr>
	
	<tr>
		<td>總評價次數:</td>
		<td><input type="TEXT" name="star_times" size="45" value="<%=store_MemVO.getStar_times()%>" readonly="true" /></td>
	</tr>
	
	<tr>
		<td><b class="red">*</b>桌位上限:</td>
		<td><input type="TEXT" name="table_limit" size="45" value="<%=store_MemVO.getTable_limit()%>" /></td>
	</tr>
		
		
		<jsp:useBean id="store_MemSvc" scope="page" class="com.store.model.Store_MemService" />
		
		
		

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>餐點狀態:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="food_status"> -->
			
<!-- 				<option value="0"'selected':''} >上架</option> -->
<!-- 				<option value="1"'selected':''} >下架</option> -->
			
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="store_no" value="<%=store_MemVO.getStore_no()%>">
<input id="send" type="submit" value="送出修改"></FORM>
</section>
<%@ include file="footer.file" %>
</body>



