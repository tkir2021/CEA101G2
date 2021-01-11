<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.shopping.controller.Food"%>
<html>
<head>
 <title>Mode II 範例程式 - Checkout.jsp</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-customer-end/shopping/css/ShoppingCart.css">
 </head>
<body>
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
		<td width="100"><%=food_no %> </td>
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

</body>
</html>