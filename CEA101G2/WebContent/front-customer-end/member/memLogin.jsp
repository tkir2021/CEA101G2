<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="zh-tw">
<%@ include file="/front-customer-end/member/header.file" %>

<head>
    <meta name="description" content="Directing Template">
    <meta name="keywords" content="Directing, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>會員登入與註冊</title>
    <!-- css引用 -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/fonts/FontAwesome/css/all.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/Header_Footer.css" type="text/css">
    <link rel="icon" href="<%=request.getContextPath() %>/front-customer-end/member/img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/loginstyle.css">
    
    <style>
    	section{
    	 background-image: url("<%=request.getContextPath() %>/front-customer-end/member/img/login.jpg");
    	}
    </style>
    
</head>

<body>
    
    <!-- =======================================Content========================================== -->
    <section>
        
            <div class="wrapper">
                <div class="form8">
                    <div class="stripes">
                        <div class="stripe stripe1"></div>
                        <div class="stripe stripe2"></div>
                        <div class="stripe stripe3"></div>
                        <div class="stripe stripe4"></div>
                        <div class="stripe stripe5"></div>
                        <div class="stripe stripe6"></div>
                        <div class="stripe stripe7"></div>
                    </div>
                    <button class="form8__btncircle">
                      <!--  <span></span>
                        <span></span>
                        <span></span>-->
                       <i class="fas fa-user-plus change"></i> 
                   	<i class="fas fa-undo-alt change"></i>
                    </button>
                    <div class="form8__log">
                        <span class="form8__text">登入會員 </span>
                        <form id="loginForm" METHOD="post" action="<%=request.getContextPath()%>/member/member.do">
                            <div class="iconinput"><i class="fas fa-user"></i><input id="account" type="text" name="account" value="" class="form8__inpt" placeholder="會員帳號" /></div>
                            <div class="iconinput"><i class="fas fa-key"></i> <input id="password" type="password" name="password" value="" class="form8__inpt" placeholder="密碼" /></div>
                            <input id="action" type="hidden" name="action" value="loginCheck">
<!--                             <input type="submit" class="form8__btn" value="Login"> -->
                            <button type="submit" class="form8__btn">Login</button>
                        </form>
                    </div>
                    <div class="form8__reg">
                        <span id="signup" class="form8__text">註冊會員</span>
                        <form id="signUpForm"METHOD="post" action="<%=request.getContextPath()%>/member/member.do">
                            <div class="iconinput"><i class="fas fa-user"></i><input id="acc" type="text" name="mem_acct" value="" class="form8__inpt" maxlength='20' placeholder="會員帳號" /></div>
                            <div class="iconinput"><i class="fas fa-key"></i><input id="psw" type="password" name="mem_pwd" value="" class="form8__inpt" maxlength='8' placeholder="密碼(英文或數字4~8碼)" /></div>
                            <div class="iconinput"><i class="fab fa-expeditedssl"></i><input id="conPsw" type="password" name="conmem_pwd" value="" class="form8__inpt" maxlength='8' placeholder="確認密碼" /></div>
                            <div class="iconinput"><i class="fas fa-id-card"></i><input id="name" type="text" name="mem_name" value="" class="form8__inpt" maxlength='6' placeholder="會員姓名(2~6碼)" /></div>
                            <div class="iconinput"><i class="fas fa-at"></i><input id="email" type="email" name="mem_mail" value="" class="form8__inpt" maxlength='50' placeholder="電子信箱" /></div>
                            <div class="iconinput"><i class="fas fa-mobile-alt"></i><input id="phone" type="text" name="men_phone" value="" class="form8__inpt" maxlength='10' placeholder="手機號碼" /></div>
                            <input id="newaction" type="hidden" name="action" value="insert">
                            <button id="signUpSubmit" type="submit" class="form8__btn">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        
    </section>
    <!-- 內容end 請在以上區塊做切版-->
    <!-- ===========================================Footer====================================== -->
   <%@ include file="footer.file" %>
   
    <!-- JS引用 -->
<!--     <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script> -->
    <script src="<%=request.getContextPath() %>/front-customer-end/member/js/script.js"></script>
    <script src="<%=request.getContextPath() %>/front-customer-end/member/js/jquery-3.3.1.min.js"></script>
<%--     <script src="<%=request.getContextPath() %>/front-customer-end/member/js/Preloader.js"></script> --%>
    <script src="<%=request.getContextPath() %>/front-customer-end/member/js/sweetalert.min.js"></script>
    
    <script>
    
    //註冊成功與否判斷
    if("${status}" == "fail"){
    	swal("註冊失敗，請重新註冊！", "", "error");
    }
    else if("${status}" == "success"){
    	swal("註冊成功，請至信箱確認驗證信！", "", "success");
    }
    
    
    //確認每欄都有資料，才能送出
    function check(){
    	 let inputs = $("#signUpForm input");
    	 for (let i = 0; i < inputs.length; i++){
    		 if(inputs.eq(i).val() === ""){
    			 inputs.eq(i).focus();
//     			 alert("尚有資料未填寫！");
				 swal("尚有資料未填寫！", "", "error");
    			 return;
    		 }
    	 }
		 $("#signUpForm").unbind("submit").submit();
    }
    
    
    
    $("#signUpForm").submit(function(e) {
		e.preventDefault();
		check();
	});
    
    
	
	$(document).ready(function(){
		 
		var path = "<%=request.getContextPath()%>/member/member.do";

		$("#loginForm").submit(function(e) {
			e.preventDefault();
			let account = $("#account").val();
			let password = $("#password").val();
			let action = $("#action").val();
			let form_data = new FormData();
			form_data.append("account", account);
			form_data.append("password", password);
			form_data.append("action", action);
			form_data.append("ajax", "true");
			$.ajax({
	            url: path,
//	             cache: false,
	            contentType: false, //這行沒加，Form表單的值會抓到null
	            processData: false, //這行沒加，送Form表單會出錯
	            data: form_data, //data只能指定單一物件   
	            type: "post",
	            success: function(msg){
					if(msg === "NG"){
// 						alert("帳號或密碼錯誤！");
						swal("帳號或密碼錯誤！", "", "error");
						$("#account").val("");
						$("#password").val("");
					} else if(msg === "verifyNG"){
// 						alert("帳號尚末啟用！");
						swal("帳號尚末啟用！", "", "error");
						$("#account").val("");
						$("#password").val("");
					}else {
						$("#loginForm").unbind('submit').submit();
					}
				}
	 		});
		});
	
	
		$("#signUpForm input").blur(function(){
		switch ($(this).attr("name")) {
			case 'mem_acct':
				$.ajax({
					url: path,
					data:{
		             	"account": $("#acc").val().trim(),
		             	"action": "accCheck"
		             },
		             success: function(msg){
		            	 console.log(msg);
		            	 
		            	 if(msg === "NG"){
		            		 $("#acc").val("");
		            		 alert("帳號已被註冊！");
		            	 }
		            	
		             }
				});				
    			break;
		
    		case 'mem_pwd':
    			if(!(/^[a-zA-Z0-9]{4,8}$/.test($("#psw").val().trim()))){
    				$("#psw").val("");
    				alert("密碼格式不正確！(英文或數字4~8碼)" + $("#psw").val());
    			}     				
        		break;
        		
    		case 'conmem_pwd':
        		if($("#psw").val() !== $("#conPsw").val().trim()){
        			$("#conPsw").val("");
        			alert("密碼不符合！");
        		}
        		break;
        		
    		case 'mem_name':
        		if(!(/^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,6}$/.test($("#name").val().trim()))){
        			$("#name").val("");
					alert("姓名只能是中、英文字母、數字和_，且長度必需在2到6之間！");
        		}
        		break;
        		
    		case 'mem_mail':
        		if(!(/^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4})*$/.test($("#email").val().trim()))){
        			$("#email").val("");
					alert("E-mail格式不正確！");
        		}
        		break;
        		
    		case 'men_phone':
    			if(!(/^09[0-9]{8}$/.test($("#phone").val().trim()))){
    				$("#phone").val("");
					alert("手機號碼格式不正確！");
    			}
        		break;
		}
		
		});
		
		//更換登入或註冊頁面
        $(document).on("click", ".form8__btncircle", function(){
        	 $(".change").toggle();
        	 //===============清除註冊假資料=============
        	 $("#acc").val("");
 			 $("#psw").val("");
 			 $("#conPsw").val("");
 			 $("#name").val("");
 			 $("#email").val("");
 			 $("#phone").val("");
        });
		
		
		$(document).on("click" , "#signup", function(){
			$("#acc").val("seafood999");
			$("#psw").val("seafood");
			$("#conPsw").val("seafood");
			$("#name").val("大衛海鮮");
			$("#email").val("david.wu.seafood@gmail.com");
			$("#phone").val("0912345678");
		});
		
	});
	
	</script>
</body>

</html>