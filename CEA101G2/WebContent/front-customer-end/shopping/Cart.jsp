<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.shopping.controller.Food" %>
<html>
<head>
 <title>購物車</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-customer-end/shopping/css/ShoppingCart.css">
 <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
 <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css">
</head>
<body><br>
   <% @SuppressWarnings("unchecked")
   Vector<Food> buylist = (Vector<Food>) session.getAttribute("shoppingcart");%>
   <%if (buylist != null && (buylist.size() > 0)) {%>

<div id="shoppingcart">
	
        <div id="top"><i class="fas fa-shopping-cart"></i>購買清單</div>
        
        <div id="bottom"> 
        <%
	 		for (int index = 0; index < buylist.size(); index++) {
			Food order = buylist.get(index);
		%>       
            <div class="cartitem">
                <div class="itemleft">
                    <img class="displayImg" src="<%=request.getContextPath() %>/food/food.do?food_no=<%=order.getFood_no()%>&action=getOneImage">
                </div>
                <div class="itemright">
                    <div class="cartname"><%=order.getName()%></div>
                    <div class="cartcount">
                        <div>數量</div>
                        <div><%=order.getQuantity()%></div>
                    </div>
                    <div class="cartprice">
                        <div>價格</div>
                        <div>$<span><%=order.getPrice()%> </span></div>
                    </div>
                    <div class="delbtn">
                       <form name="deleteForm" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST">
                            <input type="hidden" name="store_no" value="<%=request.getParameter("store_no")%>">
                            <input type="hidden" name="action" value="DELETE">
                            <input type="hidden" name="del" value="<%= index %>">
                            <input type="submit" value="刪 除" class="delbutton">
                        </form>
                    </div>
                </div>
            </div>
            <%}%>
        </div>
        
        
        <div class="pay">
            <form name="checkoutForm" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST">
                <input type="hidden" name="store_no" value="null">
                <input type="hidden" name="action" value="CHECKOUT">
                <input type="submit" value="付款結帳" class="paybutton">
            </form>
         <%}%>
        </div>
    </div>
    
    <%@ include file="footer.file" %>
</body>
</html>