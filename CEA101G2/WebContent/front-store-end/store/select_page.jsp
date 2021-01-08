<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>CEA101G2 Store_Mem: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>CEA101G2 Store_Mem: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for CEA101G2 Store_Mem: Home</p>

<h3>店家會員資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-store-end/store/listAllStore_Mem.jsp'>List</a> all FoodList.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" >
        <b>輸入店家會員編號 (如SM00000001):</b>
        <input type="text" name="store_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="store_MemSvc" scope="page" class="com.store.model.Store_MemService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" >
       <b>選擇店家會員編號:</b>
       <select size="1" name="store_no">
         <c:forEach var="store_MemVO" items="${store_MemSvc.all}" > 
          <option value="${store_MemVO.store_no}">${store_MemVO.store_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" >
       <b>選擇店名名稱:</b>
       <select size="1" name="store_no">
         <c:forEach var="store_MemVO" items="${store_MemSvc.all}" > 
          <option value="${store_MemVO.store_no}">${store_MemVO.store_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>


<h3>店家管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-store-end/store/addStore_Mem.jsp'>Add</a> a new Store.</li>
</ul>

</body>
</html>