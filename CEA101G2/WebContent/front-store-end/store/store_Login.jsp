<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="zxx"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- saved from url=(0074)file:///C:/Users/CJ02002/Desktop/%E5%BA%97%E5%AE%B6/view/empty.html#signup -->
<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/front-store-end/store/js/user.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath() %>/front-store-end/store/js/jquery-3.3.1.min.js"></script>


<head>
    
    <meta name="description" content="Directing Template">
    <meta name="keywords" content="Directing, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign-Up/Login Form</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/front-store-end/store/css/loginstyle.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-store-end/store/js/font-awesome.min.css" type="text/css">
	<link rel="icon" href="<%=request.getContextPath() %>/front-store-end/store/img/favicon.ico" type="image/x-icon"> 
</head>

<body>
    <!-- =======================================Content========================================== -->
    <section>
	<div class="form">
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Log In</a></li>
        <li class="tab"><a href="#login">Sign Up</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="signup">   
           <h1>Welcome Back!</h1>
          
          <form action="<%=request.getContextPath()%>/store/login.do" method="post">
          
            <div class="field-wrap">
            <label>
              帳號<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="on" name="username" value="${param.username}"/>
          </div>
          
          <div class="field-wrap">
            <label>
              密碼<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="on" name="password"/>
          </div>
          
          <p class="forgot"><a class="forget" href="#">Forgot Password?</a></p>
          
          <input class="button button-block" type="submit" value="Login">
          
          </form>

        </div>
        
        <div id="login">   
         <h1>Sign Up for Eat</h1>
    	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>      
          <form action="<%=request.getContextPath()%>/store/regist.do" method="post" enctype="multipart/form-data">
          
          <div class="top-row">
            <div class="field-wrap">
              <label>
                註冊帳號<span class="req">*</span>
              </label>
              <input type="text" required autocomplete="off" name="store_acct" id="username"/>
            </div>
        
            <div class="field-wrap">
              <label>
                註冊密碼<span class="req">*</span>
              </label>
              <input type="password"required autocomplete="off" name="store_pwd"/>
            </div>
          </div>

          <div class="field-wrap">
            <label>
              店名<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" name="store_name"/>
          </div>
          
         <div class="field-wrap">
            <label>
              地址
         <span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" name="addr"/>
          </div>
          
            <div class="field-wrap">
            <label>
              一周營業日ex.1111110表示周一至周六
         <span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" name="open_dates"/>
          </div>

          <div class="field-wrap">
            <label>
              電子信箱<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" name="email"/>
          </div>

          <div class="field-wrap">
            <label>
              店家分類01:中式-素葷
              02:中式-素食
              03:中式-葷食
            10:西式-素食<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" name="s_category"/>
          </div>
          
          <div class="field-wrap">
            <label>
              店家簡介<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" name="store_info"/>
          </div>
          
        <div class="field-wrap">
            <label>
              桌位限制<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" name="table_limit"/>
          </div>
          
           <div class="field-wrap">
            <label><br>
              店家圖片上傳<span class="req">*</span>
              
            </label>
            <input type="file"required autocomplete="off" name="rest_img"/>
          </div>
          
          <input type="submit" class="button button-block" value="submit"></button>
          
          <input type="hidden" name="action" value="insert"> 
          </form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
<!-- partial -->
  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="./script.js"></script>
    </section>
    			<jsp:useBean id="store_MemSvc" scope="page"
				class="com.store.model.Store_MemService" />
    <!-- 內容end 請在以上區塊做切版-->
   
    <!-- JS引用 -->
   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
   
   <script src="<%=request.getContextPath() %>/front-store-end/store/js/script.js"></script>
    <script src="<%=request.getContextPath() %>/front-store-end/store/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/front-store-end/store/js/Preloader.js"></script>
    <script>src="https://cdn.bootcdn.net/ajax/libs/layer/3.1.1/layer.js"</script>
    <script>
    $(function(){
    	$("#store_acct").blur(function(){
    		layer.msg("you removed")
    	})
    })
    </script>
</body></html>