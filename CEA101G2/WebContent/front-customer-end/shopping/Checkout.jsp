<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.shopping.controller.Food"%>
<%@ page import="com.food_list.model.*"%>

<!DOCTYPE html>
<html lang="zh-tw">
<%@ include file="/front-customer-end/shopping/header.file" %>

<head>
    <title>餐點結帳</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-customer-end/shopping/css/ShoppingCart.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css">
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

    <div id="shopping">
        <div id="shoppingtop">感謝購買<i class="far fa-smile"></i></div>
        <div id="shoppingbottom">
            <div class="buytop">
                <div>餐點圖片</div>
                <div>餐點名稱</div>
                <div>數量</div>
                <div>售價</div>
            </div>
            <div class="buybottom">
            
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
            
                <div class="buyitem">
                    <div class="buyimg">
                        <img class="displayImg" src="<%=request.getContextPath() %>/food/food.do?food_no=<%=food_no %>&action=getOneImage">
                    </div>
                    <div><%=name%></div>
                    <div><%=quantity%></div>
                    <div>$<span><%=price%></span></div>
                </div>
    <%
		}
	%>          
            </div>
            <div class="total">
                <div>總金額：$<span><%=amount%></span></div>
            </div>
        </div>
        <div class="backindex">
            <a href="<%=request.getContextPath()%>/index/index.jsp">返回首頁</a>
        </div>
    </div>
    
    
    <script src="<%=request.getContextPath() %>/front-customer-end/shopping/js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/front-customer-end/shopping/js/sweetalert.min.js"></script>
	
    <script>
    if ("${check}" === "fail") {
        swal("扣款失敗！餘額不足，請進行儲值。", "", "error");
    } else if ("${check}" === "success") {
        swal("購買成功！", "", "success");
    }
    </script>
    
    <jsp:include page="/front-customer-end/shopping/Cart.jsp" flush="true" />
</body>

</html>