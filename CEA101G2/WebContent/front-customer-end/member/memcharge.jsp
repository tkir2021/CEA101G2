<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.deposit.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
 String account =(String) session.getAttribute("account");
 Mem_DataVO mem_dataVO = new Mem_DataService().getMemAcc(account);
 String mem_no = mem_dataVO.getMem_no();
 String mem_name = mem_dataVO.getMem_name();
 pageContext.setAttribute("mem_dataVO",mem_dataVO);
 DepositVO depositVO = (DepositVO) request.getAttribute("depositVO");
%>

<!DOCTYPE html>
<html lang="zh-tw">
<%@ include file="/front-customer-end/member/header.file" %>

<head>
    <meta charset="UTF-8">
    <title>會員儲值</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.0/css/all.min.css'>
<%--     <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/font-awesome.min.css" type="text/css"> --%>
<%--     <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/Header_Footer.css" type="text/css"> --%>
<%--     <link rel="icon" href="<%=request.getContextPath() %>/front-customer-end/member/img/favicon.ico" type="image/x-icon"> --%>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/memcharge.css">
</head>
<body>


	<section>
    <!-- partial:index.partial.html -->
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/deposit.do" name="form1">
    <div>
        <div>
            <div class="main" >
                <h2>Hello! <%= mem_name %> </h2><h2>請輸入儲值金額</h2>
                     <input type="number" class="charge" placeholder="$" name="charge" value="<%= (depositVO==null)? "儲值金額" : depositVO.getCharge()%>"/> 
                            <input type="hidden" name="action" value="insert">
                            <input type="hidden" name="mem_no" value="<%= mem_no %>">
                            <input type="submit" class="submit" value="儲值"  class="btn btn-primary" >
                
            </div>
        </div>
    </div>
    </form>
    <div id="app"></div>
    </section>
    
    
    <%@ include file="footer.file" %>
    <!-- partial -->
    <script src="<%=request.getContextPath() %>/front-customer-end/member/js/memcharge.js"></script>
    
    
</body>

</html>