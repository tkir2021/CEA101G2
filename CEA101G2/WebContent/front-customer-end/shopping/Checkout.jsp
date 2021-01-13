<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.shopping.controller.Food"%>
<%@ page import="com.food_list.model.*"%>

<html>
<head>
 <title>餐點結帳</title>
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
	
%>
<img src="<%=request.getContextPath()%>/front-customer-end/shopping/images/tomcat.gif"> <font size="+3">網路書店 - 結帳：（Checkout.jsp）</font>
<hr><p>

<table id="table-1" style="margin: auto;">
	<tr>
		<th width="200">餐點名稱</th>
		<th width="100">餐點圖片</th>
		<th width="100">售價</th>
		<th width="100">數量</th>
	</tr></table><table style="margin: auto;">

	<%  @SuppressWarnings("unchecked")
		Vector<Food> buylist = (Vector<Food>) session.getAttribute("shoppingcheckout");
		String amount =  (String) request.getAttribute("amount");
	%>	
	<%	for (int i = 0; i < buylist.size(); i++) {
			Food order = buylist.get(i);
			String name = order.getName();
			String food_no = order.getFood_no();
// 			String store_no = order.getStore_no();
			Integer price = order.getPrice();
			Integer quantity = order.getQuantity();
			
	%>
	<tr>
		<td width="200"><%=name%>     </td>
		<td width="100"><img class="displayImg" style="width:40%" src="<%=request.getContextPath() %>/food/food.do?food_no=<%=food_no %>&action=getOneImage"> </td>
		<td width="100"><%=price%>    </td>
		<td width="100"><%=quantity%> </td>
	</tr>
	<%
		}
	%>
	
	<tr>
		<td colspan="6" style="text-align:right;"> 
		   <font size="+2">總金額： <h4>$<%=amount%></h4> </font>
	    </td>
	</tr>
</table>
       
       <p><a href="<%=request.getContextPath()%>/index/index.jsp"><font size="+1">返回首頁</font></a>

	<script src="<%=request.getContextPath() %>/front-customer-end/shopping/js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/front-customer-end/shopping/js/sweetalert.min.js"></script>
	
	<script>
		
	if("${check}" === "fail"){
    	swal("扣款失敗！餘額不足，請進行儲值。", "", "error");
    }
    else if("${check}" === "success"){
    	swal("購買成功！", "", "success");
    }	
	
	</script>
</body>
</html>