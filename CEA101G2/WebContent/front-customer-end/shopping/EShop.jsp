<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.food_list.model.*"%>

<!DOCTYPE html>
<html lang="zh-tw">
<%@ include file="/front-customer-end/member/header.file" %>

<head>
<title>Let's Eat 購物車</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-customer-end/shopping/css/ShoppingCart.css">
</head>
<body>

<% 
	String store_no="";
	if(session.getAttribute("store_no")==null){
		 store_no = request.getParameter("store_no");
	}else{
		 store_no =(String) session.getAttribute("store_no");
	}
	String stroe_Name = new Store_MemService().getOneStore_Mem(store_no).getStore_name();
	
	Food_ListService food_listSvc = new Food_ListService();
	List<Food_ListVO> list = food_listSvc.getAllFood(store_no);
	pageContext.setAttribute("list", list);
	
%>
<div class=main>
<div class="shopname">
<font size="+3"><%=stroe_Name %></font></div>
<!-- <hr> -->
<div>
<table id="table-1">
  <tr> 
    <th width="150">餐點名稱</th><th width="100">餐點圖片</th><th width="100">售價</th><th width="150">餐點簡介</th>
    <th width="120">數量</th><th width="120"><img src="<%= request.getContextPath() %>/front-customer-end/shopping/images/shopping-cart.png" width="45px" height="35px"></th>
  
  </tr></table>
 
 <!--  
       第一種action寫法: <form name="shoppingForm" action="Shopping.html" method="POST">
       第二種action寫法: <form name="shoppingForm" action="/IBM_MVC/Shopping.html" method="POST">
       第三種action寫法: <form name="shoppingForm" action="<%=request.getContextPath()%>/Shopping.html" method="POST">
 -->
 <!-- 
       當某網頁可能成為被forward的網頁時, 此網頁內的所有html連結 , 如果採用相對路徑寫法時, 因為會被加上原先forward者的路徑
       在更複雜的MVC架構中, 上面第三種寫法, 先以request.getContextPath()方法, 先取得環境(Servlet Context)目錄路徑的寫法,
       將是最佳解決方案
 -->
	<c:forEach var="food_listVO" items="${list}">
	<form name="shoppingForm" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST">
	<input type="hidden" name="store_no" value="<%=request.getParameter("store_no")%>">
		<table>
			<tr>
			<td width="150"><div align="center">${food_listVO.getFood_name()}</div></td>
			<td width="100"><div align="center"><img class="displayImg" src="<%=request.getContextPath() %>/food/food.do?food_no=${food_listVO.getFood_no()}&action=getOneImage"></div></td>
    		<td width="100"><div align="center">${food_listVO.getFood_price()}</div></td>
    		<td width="150"><div align="center">${food_listVO.getFood_info()}</div></td>
    		<td width="120"><div align="center">數量：<input type="text" name="quantity" size="3" value=1 pattern="^[1-9]{1}[\d]*$" title="數量不能為0" min=1 max=100></div></td>
    		<td width="120"><div align="center">     <input type="submit" class="button" value="放入購物車"> </div></td>
		</tr>
		
	</table>
		<input type="hidden" name="food_no" value="${food_listVO.getFood_no()}">
<%-- 		<input type="hidden" name="store_no" value="${food_listVO.getStore_no()}"> --%>
		<input type="hidden" name="name" value="${food_listVO.getFood_name()}">
      	<input type="hidden" name="price" value="${food_listVO.getFood_price()}">
      	<input type="hidden" name="action" value="ADD">
	</form>
	</c:forEach>
	</div>
	</div>

  <jsp:include page="/front-customer-end/shopping/Cart.jsp" flush="true" />
</body>
</html>