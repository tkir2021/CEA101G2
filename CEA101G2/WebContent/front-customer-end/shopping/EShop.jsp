<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.food_list.model.*"%>

<!DOCTYPE html>
<html lang="zh-tw">
<%@ include file="/front-customer-end/shopping/header.file" %>

<head>
    <title>Let's Eat 購物車</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-customer-end/shopping/css/ShoppingCart.css">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css">
</head>

<body>
<% String account =(String) session.getAttribute("account");
 	
%>
<%=account %>

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

    <div class="main">
        <div id="shopname">
            <h2><i class="fas fa-store"></i><span><%=stroe_Name %></span></h2>
        </div>
        <div id="menu" class="animate__animated animate__backInDown animate__slow animate__repeat-1">
            <c:forEach var="food_listVO" items="${list}">
                <form class="shopmenu" name="shoppingForm" action="<%=request.getContextPath()%>/shopping/shopping.do" method="POST">
                    <input type="hidden" name="store_no" value="<%=request.getParameter("store_no")%>">
                    <div class="item">
                        <div class="itempic"><img class="displayImg" src="<%=request.getContextPath() %>/food/food.do?food_no=${food_listVO.getFood_no()}&action=getOneImage"></div>
                        <div class="itemdesc">
                            <div class="itemTop">
                                <div class="itemname">
                                    ${food_listVO.getFood_name()}
                                </div>
                                <div class="desc">
                                   ${food_listVO.getFood_info()}
                                </div>
                            </div>
                            <div class="itemBottom">
                                <div class="order">
                                    <div class="count">數量：<input type="text" name="quantity" size="3" value=1 pattern="^[1-9]{1}[\d]*$" title="數量不能為0" min=1 max=100></div>
                                    <div class="price"><span>單價：</span>${food_listVO.getFood_price()}</div>
                                </div>
                                <div class="cart"><input type="submit" class="button" value="加入購物車"></div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="food_no" value="${food_listVO.getFood_no()}">
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

