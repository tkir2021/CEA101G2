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
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>charge</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.0/css/all.min.css'>
    <link rel="stylesheet" href="css/memcharge.css">
</head>

<body>
    <!-- partial:index.partial.html -->
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/deposit.do" name="form1">
    <div>
        <div>
            <div class= >
                <h2>Hello! <%= mem_name %> 請輸入儲值金額</h2>
                     <input type="number" class="charge" placeholder="$" name="charge"
                            value="<%= (depositVO==null)? "儲值金額" : depositVO.getCharge()%>"/> 
                            <input type="hidden" name="action" value="insert">
                            <input type="hidden" name="mem_no" value="<%= mem_no %>">
                            <input type="submit" class="submit" value="儲值"  class="btn btn-primary" >
                </form>
            </div>
        </div>
    </div>
    <div id="app"></div>
    <!-- partial -->
    <script src="js/memcharge.js"></script>
</body>

</html>