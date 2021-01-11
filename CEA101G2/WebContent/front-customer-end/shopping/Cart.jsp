<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.shopping.controller.Food" %>
<html>
<head>
 <title>購物車</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-customer-end/shopping/css/ShoppingCart.css">
</head>
<body><br>
   <% @SuppressWarnings("unchecked")
   Vector<Food> buylist = (Vector<Food>) session.getAttribute("shoppingcart");%>
<%if (buylist != null && (buylist.size() > 0)) {%>

<img src="<%=request.getContextPath()%>/front-customer-end/shopping/images/tomcat.gif"> <font size="+3">目前購物車的內容如下：（Cart.jsp）</font>

<table id="table-1">
    <tr> 
      <th width="200">餐點名稱</th><th width="100">餐點圖片</th><th width="100">售價</th>
      <th width="120">數量</th><th width="120"><img src="<%=request.getContextPath()%>/front-customer-end/shopping/images/view-cart.png"></th>
    </tr></table><table>

	<%
	 for (int index = 0; index < buylist.size(); index++) {
		Food order = buylist.get(index);
	%>
	<tr>
		<td width="200"><%=order.getName()%>     </td>
		<td width="100"><%=order.getFood_no()%>	 </td>
		<td width="100"><%=order.getPrice()%>    </td>
		<td width="120"><%=order.getQuantity()%> </td>
		
        <td width="120">
          <form name="deleteForm" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST">
          <input type="hidden" name="store_no" value="<%=request.getParameter("store_no")%>">
              <input type="hidden" name="action"  value="DELETE">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="submit" value="刪 除" class="button">
          </form></td>
	</tr>
	<%}%>
</table>
<p>
          <form name="checkoutForm" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST">
          	  <input type="hidden" name="store_no" value="<%=request.getParameter("store_no")%>">
              <input type="hidden" name="action"  value="CHECKOUT"> 
              <input type="submit" value="付款結帳" class="button">
          </form>
<%}%>
</body>
</html>