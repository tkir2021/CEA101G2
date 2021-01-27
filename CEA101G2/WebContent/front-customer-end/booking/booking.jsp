<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.booking.model.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.mem.model.*" %>
<% 
    String storeno =request.getParameter("store_no");
    String account = (String)session.getAttribute("account");
    
    Mem_DataVO mem_dataVO= new Mem_DataService().getMemAcc(account);
    session.setAttribute("memVO",mem_dataVO);

  	Store_MemVO storeVO = (Store_MemVO) new Store_MemService().getOneStore_Mem(storeno);
    session.setAttribute("storeVO", storeVO);

    List<B_orderVO> orderVO = new B_orderService().getOrderByNo(storeno);
    session.setAttribute("orderVO", orderVO);
%>
<jsp:useBean id="memSvc" class="com.mem.model.Mem_DataService"/>

<html lang="zxx">
<jsp:useBean id="B_orderSvc" scope="page" class="com.booking.model.B_orderService" />
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Directing Template">
    <meta name="keywords" content="Directing, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>BookingPage</title>
    <!-- css引用 -->
     <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/booking/css/font-awesome.min.css" type="text/css">
    <!-- datepicker -->
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <!-- main CSS-->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/booking/css/booking.css" type="text/css">
    <!-- 標題圖-->
    <link rel="icon" href="<%=request.getContextPath() %>/front-customer-end/booking/img/favicon.ico" type="image/x-icon">
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
        <img class="loaderpic" src="<%=request.getContextPath() %>/front-customer-end/booking/img/logo-07.png" alt="">
    </div>
    <!-- ===========================================Content====================================== -->
    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <FORM METHOD="post" class="insertForm" ACTION="<%= request.getContextPath() %>/front-customer-end/booking/order.do">
                        <div class="whiteback">
                            <div class="title">
                                <p class="reservation">RESERVATION</p>
                                <p class="reserve-sub">Reserve The Table!</p>
                                <p class="small-bar"></p>
                            </div>
                            <div class="bookingPart">
                                <p class="choose_date">CHOOSE A GOOD DATE:</p>
                                <div class="datepicker"></div>
                                <input TYPE="hidden" class="bookingDate" name="bookingDate" />
                            </div>
                            <jsp:useBean id="openSvc" scope="page" class="com.storeOpen.model.S_openService" />
                            <fieldset class="time">
                                <legend>DINING TIME</legend>                                                                                          
                                <c:forEach var="openVO" items="<%=openSvc.getOneOpen(storeno)%>">
                                <div class="openHour">
                                    <p class="opentime">${openVO.timeperiod}</p>                                 
                                </div>
                                </c:forEach>            
                            </fieldset>
                          
                           <p class="guest"> Guests：</p>
                           <div class="box">
                            <select class="people" name="people" >
        <option value='-1' disabled selected >Choose：</option></select>
       </div>
                          
                            <input type="hidden" name="action" value="insert">
                            <input type="button" name="" class="submit" value="Reserve!">
                            
                            </div>   
                            
                            <div class="orderSend"></div>
                            <div class="showOrder"><img class="icon" src="<%=request.getContextPath()%>/front-customer-end/booking/img/logo-06.png">
                            <div class="ordercontent">
                            </div>
<%--                             <a href="<%=request.getContextPath()%>/index/index.jsp"><button class="ok">OK</button></a> --%>
<%--                             <img class="ok" src="<%=request.getContextPath()%>/front-customer-end/booking/img/icon/OK.png"> --%>
                            <input type ="button" class="ok" onclick="javascript:location.href='<%=request.getContextPath()%>/index/index.jsp'" value="OK"></input>
                            </div>
                    </FORM>
                </div>
            </div>
        </div>
    </section>
    <!-- JS引用 -->
    <script src="<%=request.getContextPath() %>/front-customer-end/booking/js/jquery-3.3.1.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <!-- datepicker -->
<!--     <script src="js/jquery-booking.js"></script> -->
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <script src="<%=request.getContextPath() %>/front-customer-end/booking/js/Preloader.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({
               numberOfMonths: 2,
               dateFormat: "yy-mm-dd",
               minDate: new Date(),
               maxDate: "+30d",
               beforeShowDay: noSomedays,
               onSelect: function(date) {
                $(".bookingDate").val(date);
               }
            });
       });
       
       
       function noSomedays(a) {
           a = a.getDay();
           let c = "${storeVO.open_dates}";
          // let c ="1101110";
           let cc = c.split("");
           console.log(c);

           let b = [];

           for (let i = 0; i < cc.length; i++) {
               if (cc[i] == 0) {
                   b.push(i);
               }
           }
           return [a !== b[0] && a !== b[1] && a !== b[2] && a !== b[3] && a !== b[4] && a !== b[5] && a !== b[6] && a !== b[7], ""];
       }
               
   //---------------------------------------------------------------------       
       $(".submit").click(function() {
               $(".orderSend,.showOrder").css({
                   display: 'initial'
               });
           });
           $(".ok").click(function() {
               $(".orderSend,.showOrder").css({
                   display: 'none'
               });
           })
           
   //  -----------------------------------------------------------       
                   
           $(".opentime").click(function() {
               if ($(this).hasClass("greyBottom")) {
                   $(this).removeClass("greyBottom");
               } else {
                   $(".opentime").each(function() {
                       $(this).removeClass("greyBottom");
                   });
                   $(this).addClass("greyBottom");
               }
           });
               
     $(document).ready(function() {  
      $(".opentime").click(function() {
       $.ajax({
        type : "post",
        url : "/CEA101G2/booking/order.do",
        data : createQueryString(
         $(".bookingDate").val(),$(this).text()),
        dataType : "json",
        success : function(data) {
        clearSelection();
        let restpeople = ${storeVO.table_limit} - data.peopleSum;
//         $(".sel__box__options.sel__box__options--black-panther").text(1 + "&nbsp" +"guest");
              
        if (restpeople > 10) {
         for (let i = 1; i <= 10; i++) {
          switch (i) {
          case 1:
          $(".people").append(
          "<option value='1'>1&nbsp guest</options>");
          break;
          default:
          $(".people").append("<option value='"+i+"'>"+ i+ "&nbsp guests"+ "</option>");
          break;
          };           
        };
         console.log("該日期+時段的剩餘人數"+ restpeople);
         } else if (0 < restpeople && restpeople <= 10) {
          for (let i = 1; i <= restpeople; i++) {
           switch (i) {
           case 1:
           $(".people").append(
           "<option value='1'>1&nbsp guest</options>");
           break;
           default:
           $(".people").append(
           "<option value='"+i+"'>"+ i+ "&nbsp guests"+ "</option>");
           break;
           };
          };
          for (let j = restpeople+1 ; j<=10 ;j++){
           $(".people").append(
           "<option disabled value='"+j+"'>"+ j+ "&nbsp guests(full)"+ "</option>");
          }
             
         } else if (restpeople !== null && restpeople <= 0) {
          alert("親，桌位滿了換一個時段或日期吧！");
         } else {
          alert("請重新操作一次~");
         };
        },
        error : function() {
//          alert("AJAX-class發生錯誤囉!")
        }
       });
      });
         function createQueryString(bookingdate, time) {
          var queryString = {
           "action" : "getPeople",
           // "storeno" : storeno,
           "bookingdate" : bookingdate,
           "openHour" : time
          };
          console.log(queryString);
          return queryString;
         }

         function clearSelection() {
          $(".people").empty();
          $(".people").append(
            "<option value='-1' disabled>Choose:</option>");
         }
         //-------------------------------------------------------------------------------------      
         $(".submit").click(function(){
          $.ajax({
           type:"POST",
           url:"/CEA101G2/booking/order.do",
           data: createQueryString2(
             $(".bookingDate").val(),$(".greyBottom").text(),$(".people").val()
             ),
           dataType:"json",
           success:function(data){    
        	   console.log(data.orderVO);
               console.log($(".bookingDate").val());
               $(".orderContent").append("<P class='name'>Dear "+data.memVO+",</P>"+
               "<p class='reserve'>Your Reservation was sent-out as following:</p>"+
               "<p class='detail'>Date:"+ $(".bookingDate").val()+
               ",<br>Dining Time:"+ $(".greyBottom").text().substring(0,8)+
               ",<br>Number of Guests:"+$(".people").val()+
               "<br></p><p class='endquto'>We’re looking forward to welcoming you!</p>");
           },
           error:function(){
            alert("AJAX-class發生錯誤囉!");
           }
          });
         });
         
         function createQueryString2(bookingDate,openHour,people){
          let queryString2 = {
           "action":"insertByAjax",
           "bookingdate":bookingDate,
           "openHour":openHour,
           "people":people
          };
          return queryString2;
         };
     });
	    </script>
	</body>
</html>