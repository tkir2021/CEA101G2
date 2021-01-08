<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.group.model.*"%>

<%  //取得會員資料
	String account =(String) session.getAttribute("account");
 	Mem_DataVO mem_dataVO = new Mem_DataService().getMemAcc(account);
 	pageContext.setAttribute("mem_dataVO",mem_dataVO);
 	
 	//取得所有揪團資訊
 	Group_Service group_Svc = new Group_Service();
	List<Group_VO> list = group_Svc.getAll();
	pageContext.setAttribute("list", list);
 	
%>


<!DOCTYPE html>
<html lang="zh-tw">

<%@ include file="/front-customer-end/member/header.file" %>

<head>
    <meta name="description" content="Directing Template">
    <meta name="keywords" content="Directing, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>會員資料</title>
    
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/memUpdate.css" type="text/css">
    
</head>

<body>
	<div>
		<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	</div>
    <!-- =======================================Content========================================== -->
    <section>
        <c:forEach var="group_VO" items="${list}">
        	<div class="groupList" data-aos="flip-left">
        		<div class="groupPic">
                	<img src="./img/group/Restaurant-seafood.jpg" alt="">
                </div>
        	</div>
        </c:forEach>
    </section>
    <!-- 內容end 請在以上區塊做切版-->
    <!-- ===========================================Footer====================================== -->
     <%@ include file="footer.file" %>
   
    <script>

    // window.onload = function(){
    //     var group = $("<div/>").addClass("groupList").attr("data-aos", "flip-left");
    //     var groupPic = $("<div/>").addClass("groupPic").append('<img src="">');
        
    //     $("section").append(group.append(groupPic));
    // }
        

    </script>
</body>

</html>