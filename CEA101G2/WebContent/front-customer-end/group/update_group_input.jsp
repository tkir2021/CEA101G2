<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group.model.*"%>

<%
Group_VO group_VO = (Group_VO) request.getAttribute("group_VO"); //GroupServlet.java (Concroller) 存入req的group_VO物件 (包括幫忙取出的group_VO, 也包括輸入資料錯誤時的group_VO物件)
%>
<%= group_VO==null %>--${group_VO.group_no}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>揪團資料修改 - update_group_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>揪團資料修改 - update_group_input.jsp</h3>
		 <h4><a href="<%= request.getContextPath()%>/front-customer-end/group/select_page.jsp"><img src="<%= request.getContextPath()%>/front-customer-end/group/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/group/group.do" name="form1">
<table>
	<tr>
		<td>揪團編號：<font color=red><b>*</b></font></td>
		<td><%=group_VO.getGroup_no()%></td>
	</tr>
	<tr>
		<td>會員編號：</td>
		<td><%=group_VO.getMem_no()%></td>
	</tr>
	<tr>
		<td>店家編號：</td>
		<td><%=group_VO.getStore_no()%></td>
	</tr>
	<tr>
		<td>訂位編號：</td>
		<td><%=group_VO.getBooking_no()%></td>
	</tr>
	<tr>
		<td>揪團分類：</td>
		<td>${(group_VO.gp_kind == "1")? "公開" : "私密"}</td>

	</tr>
	<tr>
		<td>參團人數(下限)：</td>
		<td><input type="TEXT" name="mem_least" size="45" value="<%=group_VO.getMem_least()%>" /></td>
	</tr>
	<tr>
		<td>參團人數(上限)：</td>
		<td><input type="TEXT" name="mem_limit" size="45" value="<%=group_VO.getMem_limit()%>" /></td>
	</tr>
		<tr>
		<td>用餐日期：</td>
		<td><input name="dining_date" id="f_dining_date" type="text" ></td>
	</tr>
	<tr>
		<td>開團日期：</td>
		<td><%=group_VO.getStart_date()%></td>
	</tr>
	<tr>
		<td>截團日期：</td>
		<td><input name="end_date" id="f_end_date" type="text" ></td>
	</tr>
	<tr>
		<td>揪團簡介：</td>
<%-- 		<td><input type="TEXT" name="gp_info" size="45" value="<%=group_VO.getGp_info()%>" placeholder="請填入關鍵字" /></td> --%>
<td><textarea name="gp_info" placeholder="請填入簡介" ><%=group_VO.getGp_info()%></textarea></td>
	</tr>
	<tr>
		<td>揪團狀態：</td>
			<td>${(group_VO.gp_status == 1)? "成功" : "失敗"}</td>
<%-- 			<td><%= (group_VO.getGp_status()== 1)? "成功" : "失敗"%></td> --%>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="group_no" value="<%=group_VO.getGroup_no()%>">
<input type="hidden" name="mem_no" value="<%=group_VO.getMem_no()%>">
<input type="hidden" name="store_no" value="<%=group_VO.getStore_no()%>">
<input type="hidden" name="booking_no" value="<%=group_VO.getBooking_no()%>">
<input type="hidden" name="gp_kind" value="<%=group_VO.getGp_kind()%>">
<input type="hidden" name="start_date" value="<%=group_VO.getStart_date()%>">
<input type="hidden" name="gp_status" value="<%=group_VO.getGp_status()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-customer-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front-customer-end/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-customer-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_dining_date').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=group_VO.getDining_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_end_date').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=group_VO.getEnd_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>