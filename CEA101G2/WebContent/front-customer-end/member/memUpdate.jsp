<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.mem.model.*"%>

<% String account =(String) session.getAttribute("account");
 	Mem_DataVO mem_dataVO = new Mem_DataService().getMemAcc(account);
 	pageContext.setAttribute("mem_dataVO",mem_dataVO);
%>

<%-- <%= account %> --%>
<%-- <%=mem_dataVO==null%> --%>
<%-- <%=mem_dataVO.getMem_name()%> --%>
<%-- --${mem_dataVO.getMem_grade()}-- --%>


<!DOCTYPE html>
<html lang="zh-tw">
<%@ include file="/front-customer-end/member/header.file" %>

<head>
    <meta name="description" content="Directing Template">
    <meta name="keywords" content="Directing, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>會員資料與修改</title>
    <!-- css引用 -->
    <!-- <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/font-awesome.min.css" type="text/css"> -->
<!--     <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"> -->
<%--     <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/Header_Footer.css" type="text/css"> --%>
<%--     <link rel="icon" href="<%=request.getContextPath() %>/front-customer-end/member/img/favicon.ico" type="image/x-icon"> --%>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/memUpdate.css" type="text/css">
    
</head>

<body>


    <!-- =======================================Content========================================== -->
    <section>
        <div class="main">
        	<div class="show">
        	<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
			<font>請修正以下錯誤:</font>
			<ul>
	    		<c:forEach var="message" items="${errorMsgs}">
 			  	<li>${message}</li>
				</c:forEach>
			</ul>
			</c:if> 
		</div>
            <form method="post" action="<%=request.getContextPath() %>/member/member.do" enctype="multipart/form-data" name="form1">
                <div class="memUpForm">
                    <h3>會員資料 <i class="fas fa-pencil-alt"></i></h3>
                    
                    <div class="formDetail">
                        <div id="display" class="displayImg">
                            <img id="upImg" src="<%=request.getContextPath() %>/member/member.do?mem_no=${mem_dataVO.mem_no}&action=getOneImage" alt="">
                        </div>
                        
                        <div class="diyForm">
                            <div>
                                <label>會員帳號：</label><span>${mem_dataVO.getMem_acct()}</span>
                            </div>
                            <div>
                                <label>會員等級：</label><span>${mem_dataVO.getMem_grade()}</span>
                            </div>
                            <div>
                                <label>會員姓名：</label><input class="edit" type="TEXT" name="mem_name" maxlength="6" value="${mem_dataVO.getMem_name()}" readonly="readonly"/>
                            </div>
                            <div>
                                <label>登入密碼：</label><input class="edit" type="TEXT" name="mem_pwd" maxlength="8" value="${mem_dataVO.getMem_pwd()}" readonly="readonly"/>
                            </div>
                            <div>
                                <label>會員手機：</label><input class="edit" type="TEXT" name="men_phone" maxlength="10" value="${mem_dataVO.getMen_phone()}" readonly="readonly"/>
                            </div>
                            <div>
                                <label>E-mail：</label><input class="edit" type="TEXT" name="mem_mail" maxlength="50" value="${mem_dataVO.getMem_mail()}" readonly="readonly"/>
                            </div>
                            <div>
                                <label>累計消費金額：</label><span>${mem_dataVO.getConsume_times()}</span>
                            </div>
                            <div>
                                <label>儲值金餘額：</label><span>${mem_dataVO.getDeposit()}</span>
                            </div>
                            <div>
<!--                             	<input class="editImg"  type="file" id="myFile" name="mem_img"/> -->
                            	<input class="editImg"  type="file" id="myFile" name="mem_img" disabled="disabled"/>
                            </div>
                            
                            <input type="hidden" name="action" value="update">
							<input type="hidden" name="mem_no" value="${mem_dataVO.getMem_no()}">
                            
                            <div id="memUpBtn">
                            	<input id="change" type="button" value="修改">
                                <input id="memUpSend" type="submit" value="送出">
            
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </section>
    
    <!-- 內容end 請在以上區塊做切版-->
    <!-- ===========================================Footer====================================== -->
     <%@ include file="footer.file" %>
    
    <!-- JS引用 -->
<%--     <script src="<%=request.getContextPath() %>/front-customer-end/member/js/jquery-3.3.1.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath() %>/front-customer-end/member/js/Preloader.js"></script> --%>
    <script>
        
        //修改資料
        $(document).ready(function(){
        	
  			$("#change").click(function(){
				$(".edit").removeAttr("readonly").addClass("on");
				$(".editImg").removeAttr("disabled");
  			});
  			
  			
  			$("#memUpSend").click(function(){
				$(".editImg").removeAttr("disabled");
  			});
  			
  			
  			
  			
  			
		});
        

        //預覽圖片
        let myFile = document.getElementById("myFile");
        let display = document.getElementById("display");
        
        myFile.addEventListener("change", function() {

            if (this.files) {
                for (let i = 0; i < this.files.length; i++) {
                    let file = this.files[i];
                    if (file.type.indexOf("image") > -1) {
                       
                        let reader = new FileReader();
                        reader.addEventListener("load", function(e) {
                            let img = document.createElement("img");
                            img.id = "upImg"
                            img.src = e.target.result;
                            
                            $("#upImg").remove();
                            display.append(img);
                            
                        });
                        reader.readAsDataURL(file);
                    } else {
                        alert('請上傳圖片！');
                    }
                }
            }
        });
        

        
        
    </script>
</body>

</html>