<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.booking.model.*"%>
<%@ page import="com.ordermaster.model.*"%>

<title>Store_Detail</title>

<!DOCTYPE html>
<html lang="zxx">

<head>
<%@ include file="header.file" %>
    <!-- 自定義css -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-store-end/store/css/store_detail.css">
</head>

<body style="height: fit-content;">
 <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
        <img class="loaderpic" src="<%=request.getContextPath() %>/front-store-end/store/img/logo-07.png" alt="">
    </div>
<div class="showDetail">
        <p>close</p>
        <table>
            <tr>
                <th>食物名稱</th>
                <th>食物規格</th>
                <th>數量</th>
                <th>單價</th>
                <th>總額</th>
            </tr>
        </table>
    </div>
    <div class="detailBack"></div>
    <!-- =======================================Content========================================== -->
    <section style="height: 697px;padding-bottom:0px" >
        <!-- 以下三行div為bootstrap的格線語法，請不要新增屬性、id，請參考格線語法自行更改 ((RWD課程應該會上))-->
        <input id="panel-1-ctrl" class="panel-radios" type="radio" name="tab-radios" checked>
        <input id="panel-2-ctrl" class="panel-radios" type="radio" name="tab-radios">
        <div id="introduction">
            <h1>Welcome Back!</h1>
        </div>
        <!-- TABS LIST -->
        <ul id="tabs-list">
            <!-- MENU TOGGLE -->
            <label id="open-nav-label" for="nav-ctrl"></label>
            <li id="li-for-panel-1">
                <label class="panel-label" for="panel-1-ctrl">訂位紀錄</label>
            </li>
            <!--INLINE-BLOCK FIX
 -->
            <li id="li-for-panel-2">
                <label class="panel-label" for="panel-2-ctrl">訂餐紀錄</label>
            </li>
            <label id="close-nav-label" for="nav-ctrl">Close</label>
        </ul>
        <!-- THE PANELS -->
        <article id="panels">
            <div class="container">
                <section id="panel-1">
                    <main>
                        <div class="tbl-header">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <thead>
                                    <tr>
										<th>訂位編號</th>
										<th>會員編號</th>
										<th>揪團編號</th>
										<th>訂位日期</th>
										<th>訂位時段</th>
										<th>訂位人數</th>
										<th>訂位狀態</th>
										<th>參加狀態</th>
										<th>評分數</th>
										<th>訂位成立日期</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="tbl-content">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tbody>
                                    <c:forEach var="orderVO" items="${SorderList}" >
									<tr>
										<td>${orderVO.bookingno}</td>
										<td>${orderVO.memno}</td>
										<td>${orderVO.groupno}</td>
										<td>${orderVO.bookingdate}</td>
										<td>${orderVO.timeperiod}</td>
										<td>${orderVO.people}</td> 
										<td>${(orderVO.bookingstatus==0)?"失敗":"成功"}</td>
										<td>${(orderVO.attendstatus==0)?"未出席":"已出席"}</td>
										
<%-- 										<FORM method="post" class="form1" action="<%=request.getContextPath()%>/booking/booking.do">               --%>
<%-- 	                                		<select name="mem_auth" onchange="submit(),alert('${store_no}的出席狀態已改變')"> --%>
<%-- 	                                			<option value="0" ${(orderVO.attendstatus==0)?'selected':''} >未出席</option> --%>
<%-- 	                                			<option value="1" ${(orderVO.attendstatus==1)?'selected':''}>已出席</option> --%>
<!-- 	                                		</select> -->
<%-- 	                                		<input type="hidden" name="store_no" class="store_no" value="${store_no}"> --%>
<!-- 	                                		<input type="hidden" name="action" value="updateStatus"> -->
<!-- 	                        		 	</FORM>  -->
										
										<td>${orderVO.givestar}</td>
										<td><fmt:formatDate value="${orderVO.ordercommit}" pattern="yyyy-MM-dd"/></td>
									</tr>
								</c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </main>
                </section>
                <section id="panel-2">
                    <main>
                        <div class="tbl-header">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <thead>
                                    <tr>
                                        <th>訂餐編號</th>
                                        <th>會員編號</th>
                                        <th>訂餐日期</th>
                                        <th>支付方式</th>
                                        <th>總金額</th>
                                        <th>取餐狀況</th>
                                        <th>幾顆星</th>
                                        <th>訂餐明細</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div class="tbl-content">
                            <table cellpadding="0" cellspacing="0" border="0" class="order">
                                <tbody>
                                     <c:forEach var="orderList" items="${orderMaster}" >
									<tr>
										<td class="orderno">${orderList.order_no}</td>
										<td>${orderList.mem_no}</td>
										<td>${orderList.order_date}</td>
										<td>										
										${(orderList.pay_type==0)?"儲值金":"信用卡"}
										</td> 
										<td>${orderList.order_total}</td>
										<td>${(orderList.take_status==0)?"未取餐":"已取餐"}</td>
										<td>${orderList.give_star}</td>
										<td><button class="botton">訂餐明細</button></td>
									</tr>
								</c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </main>
                </section>
            </div>
        </article>
        </section>
        <!-- 內容end 請在以上區塊做切版-->
        <!-- ===========================================Footer====================================== -->
         <%@ include file="footer.file" %>
        <footer class="footer" style="top:-38px">
          
        <!-- JS引用 -->        
        <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src="<%=request.getContextPath() %>/front-store-end/store/js/store_detail.js"></script>
<script>
$(document).ready(function() {	
$("button").click(function() {
	$.ajax({
		type:"post",
		url:"/CEA101G2/store/store.do",
 		data:createQueryString($(this).parent().siblings(".orderno").text()),
		dataType:"json",
		success:function(data){
			clearTD();
// 			$(".showDetail table").remove("td");
			
			for(x in data){
				console.log(data[x].food_scale);				
				$(".showDetail table").append(
				"<tr><td>"+data[x].food_no+"</td><td>"+data[x].food_scale+"</td><td>"+data[x].quantity+"</td><td>"+data[x].food_price+"</td><td>"+data[x].total+"</td></tr>");
			$(".showDetail,.detailBack").show(1000);
			}						
		}
	});
});
	function createQueryString(orderno){
		let queryString = {
			"action":"getOrderDetail",
			"orderno":orderno		
		}; 
		console.log(queryString);
		return queryString;
	}
	function clearTD(){
		$(".showDetail table").empty();
		$(".showDetail table").append("<tr><th>食物編號</th><th>食物規格</th><th>商品數量</th><th>商品單價</th><th>商品總額</th></tr>")
	}
	

$(".showDetail p").click(function() {
    $(".showDetail,.detailBack").hide(1000);
});
});
</script>

</body>

</html>