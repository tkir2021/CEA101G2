<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<jsp:useBean id="storeSvc" scope="page"
	class="com.store.model.Store_MemService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Directing Template">
<meta name="keywords" content="Directing, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>搜尋結果</title>
<!-- ↑↑↑↑請記得修改title為自己製作頁面的名稱↑↑↑ -->
<!-- css引用 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-customer-end/SearchResult/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-customer-end/SearchResult/css/font-awesome.min.css"
	type="text/css">
<!-- <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-customer-end/SearchResult/css/Header_Footer.css"
	type="text/css">
<link rel="icon"
	href="<%=request.getContextPath()%>/front-customer-end/SearchResult/img/favicon.ico"
	type="image/x-icon">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-customer-end/SearchResult/css/allRestaurant.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-customer-end/SearchResult/css/elegant-icons.css">
 <style type="text/css">
 .bookingButtom {
            width: 90px;
            height: 30px;
            background-color: #dc3545;
            border-radius: 10px;
            margin-left: 30px;
            font-size: 15px;
            font-weight: bold;
            color: white;
            vertical-align: middle;
            display: inline-block;
        }
     /*---------------------
  			Login/Logout CSS
		-----------------------*/
 		.login-logout a:link {
            color: white;
        }
        .login-logout a:visited{
	    color: white;
		}
        .login-logout {
            position: absolute;
            right: 60px;
        }
        .login-logout li:hover {
            background-color: #ea314f;
        }
        .login-logout li {
            background-color: black;
            text-decoration: none;
            text-align: center;
            width: 100px;
            height: 30px;
            list-style-type: none;
        }
   </style>
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
		<img class="loaderpic"
			src="<%=request.getContextPath()%>/front-customer-end/SearchResult/img/logo-07.png"
			alt="">
	</div>
	<!-- ===========================================Header====================================== -->
	<header class="header">
		<div class="header__logo">
			<a href="<%=request.getContextPath() %>/index/index.jsp<%=request.getContextPath() %>/index/index.jsp">
			<img src="<%=request.getContextPath()%>/front-customer-end/SearchResult/img/logo.png"
				alt=""></a>
		</div>
		<div class="header__nav">
			<nav class="header__menu mobile-menu">
				<ul>
					<li><a href="<%=request.getContextPath() %>/front-customer-end/member/memUpdate.jsp">會員專區</a>
                                    <ul class="dropdown">
                                        <li><a href="<%=request.getContextPath() %>/front-customer-end/member/creditcard.jsp">我要儲值</a></li>
                                        <li><a href="<%=request.getContextPath() %>/front-customer-end/member/memUpdate.jsp">資料管理</a></li>
                                        <li><a href="<%=request.getContextPath() %>/front-customer-end/member/memHistory.jsp">訂單查詢及我要評分</a></li>
                                    </ul>
                                </li>
                                <li><a href="<%=request.getContextPath()%>/store/store.do?action=getOne_For_Store&action2=getALL">店家專區</a>
                                    <ul class="dropdown">
                                    <li><a href="<%=request.getContextPath()%>/front-store-end/store/store_Login.jsp">店家登入</a></li>
                                        <li><a href="<%=request.getContextPath()%>/front-store-end/store/update_Store_Mem_input.jsp">店家資料管理</a></li>
                                        <li><a href="<%=request.getContextPath()%>/store/store.do?action=getOne_For_Store&action2=getALL">我的歷史紀錄</a></li>
                                        <li><a href="<%=request.getContextPath()%>/front-store-end/store/listAllFood_List.jsp" id="to_position">我的餐點</a></li>
                                        <li><a href="<%=request.getContextPath()%>/front-store-end/store/addFood_List.jsp">餐點上架</a></li>
                                        <li><a href="<%=request.getContextPath()%>/front-store-end/store/listOneOpenHour.jsp">營業時段管理</a></li>
                                    </ul>
                                </li>
				</ul>
			</nav>
			<div class="header__menu__right">
				<!-- <a href="#" class="primary-btn" title="註冊會員"><i class="fa fa-plus"></i>加入我們</a> -->
				<a href="<%=request.getContextPath() %>/front-customer-end/member/memLogin.jsp" class="login-btn" title="我要登入"><i class="fa fa-user"></i></a>
				<ul class="login-logout" style="display: none;">
                   	<li><a href="<%=request.getContextPath() %>/member/member.do?action=logout">我要登出</a></li>
                </ul>
			</div>
		</div>
		</div>
	</header>
	<!-- =======================================Content========================================== -->
	<section>
		<!-- 1美食圖----------------------------------------------------------Start-->
		<div class="listRestaurant">
			<c:forEach var="fdVO" items="${fdVO}">
				<div class="listing__item">
				<a href="<%=request.getContextPath() %>/store/store.do?store_no=${fdVO}&action=getThisStore&location=/front-customer-end/shopping/EShop.jsp" >
					<img class="listing__item__pic set-bg" src="<%=request.getContextPath() %>/store/store.do?store_no=${fdVO}&action=getOneImage">
					<img src="<%=request.getContextPath()%>/front-customer-end/SearchResult/img/listing/list_icon-4.png"
						class="small" alt="">
					<div class="listing__item__pic__tag">Popular</div>
					<div class="listing__item__text">
						<div class="listing__item__text__inside">
							<h5>${storeSvc.getOneStore_Mem(fdVO).store_name}</h5>
							<div class="listing__item__text__rating">
								<div class="listing__item__rating__star">
									<c:forEach begin="1" end="${storeSvc.getOneStore_Mem(fdVO).star_total}" varStatus="j" step="1">
										<span class="icon_star"></span>
										<span class="ic on_star-half_alt"></span>
									</c:forEach>
								</div>
							</div>
							<ul>
								<li><span class="icon_pin_alt"></span>${storeSvc.getOneStore_Mem(fdVO).addr}</li>
								<li><span class="icon_phone"></span> (+12) 345-678-910</li>
							</ul>
						</div>
						<div class="listing__item__text__info">
							<div class="listing__item__text__info__left">
								<img
									src="<%=request.getContextPath()%>/front-customer-end/SearchResult/img/listing/list_small_icon-1.png"
									alt=""> <span>Restaurant</span>
							</div>
							 <a href="<%=request.getContextPath()%>/store/store.do?store_no=${fdVO}&action=getThisStore&location=/front-customer-end/booking/booking.jsp" class="bookingHref">
                            <button class="bookingButtom">我要訂位</button>
                       		 </a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- </div> -->

	</section>
	<!-- 內容end 請在以上區塊做切版-->
	<!-- ===========================================Footer====================================== -->
	<footer class="footer">
		<div class="container">
			<div class="footer__about">
				<div class="footer__about__logo">
					<a href="./index.html"><img
						src="<%=request.getContextPath()%>/front-customer-end/SearchResult/img/logo-06.png"
						alt=""></a>
				</div>
				<div class="footer__address">
					<ul>
						<li><span>Call Us:</span>
							<p>(+12) 345-678-910</p></li>
						<li><span>Email:</span>
							<p>info.colorlib@gmail .com</p></li>
						<li><span>Fax:</span>
							<p>(+12) 345-678-910</p></li>
						<li><span>Connect Us:</span>
							<div class="foote -->r__social">
								<img
									src="<%=request.getContextPath()%>/front-customer-end/SearchResult/img/icon/iconfinder_46-facebook_104458.png">
								<img
									src="<%=request.getContextPath()%>/front-customer-end/SearchResult/img/icon/iconfinder_Instagram_1298747.png">
								<img
									src="<%=request.getContextPath()%>/front-customer-end/SearchResult/img/icon/iconfinder_Twitter_570625.png">
							</div></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="footer__copyright">
					<div class="footer__copyright__text">
						<p>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;
							<script>
								document.write(new Date().getFullYear());
							</script>
							All rights reserved | We are Group2 CEA_101 <i
								class="fa fa-heart" aria-hidden="true"></i> I appreciate that we
							are the "team".
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
					</div>
				</div>
			</div>
		</div>
		</div>
	</footer>
	<!-- footer end 以上內容待置換-->
	<!-- JS引用 -->
	 
	<script src="<%=request.getContextPath()%>/front-customer-end/SearchResult/js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-customer-end/SearchResult/js/Preloader.js"></script>

	<script>
	 /*------------------
		登入/登出
	-------------------*/
	console.log(${(sessionScope.account) !=null});
	if(${(sessionScope.account) !=null}){
		$(".login-btn").mouseenter(function() {
			$(".login-logout").slideDown();
		});
		$(".login-logout").mouseleave(function() {
			$(".login-logout").slideUp();
		});
	}
</script>

</body>
</html>