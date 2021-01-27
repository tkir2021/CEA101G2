<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.booking.model.*"%>
<%@ page import="com.ordermaster.model.*"%>

<% 
 String store_no = (String)session.getAttribute("store_no");
 OrderMasterService omSvc = new OrderMasterService();
 List <OrderMasterVO> orderVO = omSvc.findByNumber(store_no);
 pageContext.setAttribute("orderMaster", orderVO);
 
 B_orderService orderSvc = new B_orderService();
 List<B_orderVO> SorderList = orderSvc.getOrderByNo(store_no);
 pageContext.setAttribute("SorderList",SorderList);
%>

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
        <input type="text" placeholder="Search..." class="searchBar" style="position:absolute; top:42%; left:77%;">
        <!-- TABS LIST -->
        <ul id="tabs-list">
            <!-- MENU TOGGLE -->
            <label id="open-nav-label" for="nav-ctrl"></label>
            <li id="li-for-panel-1">
                <label class="panel-label" for="panel-1-ctrl">訂位紀錄</label>
            </li>
            <!--INLINE-BLOCK FIX-->
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
                             <c:choose>
                             <c:when test="${SorderList==[]}">
                               <h6 style="position:relative; top:20%;">目前尚無訂位紀錄</h6>
                             </c:when>
                             <c:otherwise>
                                <c:forEach var="bookingVO" items="${SorderList}" >
          <tr>
           <td class="bookingno">${bookingVO.bookingno}</td>
           <td>${bookingVO.memno}</td>
           <jsp:useBean id="now" class="java.util.Date"/>
           <fmt:formatDate value="${now}" dateStyle="long" pattern="yyyy-MM-dd" var="nowDate"/>
           <c:choose>
            <c:when test="${bookingVO.bookingdate == now}">
             <td style="color:red">${bookingVO.bookingdate}</td>
            </c:when>
            <c:otherwise>
             <td style="color:darkgrey">${bookingVO.bookingdate}</td>
            </c:otherwise>
           </c:choose>
             <td>${bookingVO.timeperiod}</td>
             <td>${bookingVO.people}</td> 
             <td>${(bookingVO.bookingstatus==0)?"失敗":"成功"}</td>
             <td>
           <c:choose>
           <c:when test="${bookingVO.attendstatus==0}">
           <FORM method="post" class="takeStatus" action="<%=request.getContextPath()%>/booking/booking.do">
             <select name="attend" class="attend" onchange="submit()">
                                      <option value="0" ${(bookingVO.attendstatus==0)?'selected':''} >未出席</option>
                                      <option value="1" ${(bookingVO.attendstatus==1)?'selected':''}>已出席</option>
                                     </select>
                                   <input type="hidden" name="bookingno" class="bookingno" value="${bookingVO.bookingno}">
                                   <input type="hidden" name="action" value="updateAttend_status">
           </FORM>
           </c:when>
           <c:otherwise>已出席</c:otherwise>
           </c:choose>
           </td>
           <td>${bookingVO.givestar}</td>
           <td><fmt:formatDate value="${bookingVO.ordercommit}" pattern="yyyy-MM-dd"/></td>
          </tr>
         </c:forEach>
         </c:otherwise>
                             </c:choose>
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
                                 <c:choose>
                               <c:when test="${orderMaster==[]}">
                                <h6 style="position:relative; top:20%;">目前尚無訂餐紀錄</h6>
                               </c:when>
                              <c:otherwise>
                                     <c:forEach var="orderList" items="${orderMaster}" >
         <tr>
          <td class="orderno">${orderList.order_no}</td>
          <td>${orderList.mem_no}</td>
          <td>${orderList.order_date}</td>
          <td>          
          ${(orderList.pay_type==0)?"儲值金":"信用卡"}</td> 
          <td>${orderList.order_total}</td>
          <td>
           <c:choose>
            <c:when test="${orderList.take_status==0}">
             <FORM method="post" class="takeStatus" action="<%=request.getContextPath()%>/ordermaster/ordermaster.do">
              <select name="take_status" class="take_status" onchange="submit()">
                                       <option value="0" ${(orderList.take_status==0)?'selected':''} >未取餐</option>
                                       <option value="1" ${(orderList.take_status==1)?'selected':''}>已取餐</option>
                                      </select>
                                      <input type="hidden" name="order_no" class="oder_no" value="${orderList.order_no}">
                                      <input type="hidden" name="take_status" value="1">
                                      <input type="hidden" name="action" value="updateTake_status">
             </FORM>
            </c:when>
            <c:otherwise>已取餐</c:otherwise>
            </c:choose>
            </td>
            <td>${orderList.give_star}</td>
            <td><button class="botton">訂餐明細</button></td>
           </tr>
          </c:forEach>
          </c:otherwise>
         </c:choose>
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
<!--         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
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
//---------------------------------------------------------------------    
    $(".searchBar").keyup(function(){
     let value = $(this).val().toLowerCase();
     $("table tbody tr").filter(function(){
      $(this).toggle($(this).text().toLowerCase().indexOf(value)>-1);
     });
    });
//---------------------------------------------------------------------
console.log(${SorderList==[]});
//     $(".attend").change(function() {
//      $.ajax({
//       type:"post",
//       url:"/CEA101G2/booking/booking.do",
//        data:createQueryString($(this).parent().siblings(".bookingno").text()),
//       dataType:"json",
//       success:function(data){
//        $(this).empty();
//        $(this).append("已取餐");
//        alert("該顧客已取餐");  
//       }
//      });
//     });
   });
  </script>
 </body>
</html>