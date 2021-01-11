<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<jsp:useBean id="storeSvc" scope="page"	class="com.store.model.Store_MemService" />
<%
	Store_MemService smSvc = new Store_MemService();
	List<Store_MemVO> list = smSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Directing Template">
<meta name="keywords" content="Directing, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>搜尋結果-All</title>
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
			<a href="./index.html"><img
				src="<%=request.getContextPath()%>/front-customer-end/SearchResult/img/logo.png"
				alt=""></a>
		</div>
		</div>
		<div class="header__nav">
			<nav class="header__menu mobile-menu">
				<ul>
					<li><a href="./blog.html">會員專區</a>
						<ul class="dropdown">
							<li><a href="./blog-details.html">我的行事曆</a></li>
							<li><a href="./blog-details.html">我要加值</a></li>
							<li><a href="./blog-details.html">我最愛的餐廳</a></li>
						</ul></li>
					<li><a href="#">我要揪團</a>
						<ul class="dropdown">
							<li><a href="./about.html">瀏覽揪團</a></li>
							<li><a href="./blog-details.html">我的揪團</a></li>
						</ul></li>
					<li><a href="#">店家專區</a>
						<ul class="dropdown">
							<li><a href="./about.html">店家資料管理</a></li>
							<li><a href="./blog-details.html">我的行事曆</a></li>
							<li><a href="./blog-details.html">商店管理</a></li>
							<li><a href="./blog-details.html">我的帳務</a></li>
						</ul></li>
				</ul>
			</nav>
			<div class="header__menu__right">
				<!-- <a href="#" class="primary-btn" title="註冊會員"><i class="fa fa-plus"></i>加入我們</a> -->
				<a href="#" class="login-btn" title="我要登入"><i class="fa fa-user"></i></a>
			</div>
		</div>
		</div>
	</header>
	<!-- =======================================Content========================================== -->
	<section>
		<!-- 以下三行div為bootstrap的格線語法，請不要新增屬性、id，請參考格線語法自行更改 ((RWD課程應該會上))-->
		<!-- 1美食圖----------------------------------------------------------Start-->
		<!-- <div class="col-lg-4 col-md-6"> -->
		<div class="listRestaurant">
			<c:forEach var="smVO" items="${list}">
				<div class="listing__item">
					<img class="listing__item__pic set-bg"
						src="<%=request.getContextPath() %>/store/store.do?store_no=${smVO.store_no}&action=getOneImage">
					<img
						src="<%=request.getContextPath()%>/front-customer-end/SearchResult/img/listing/list_icon-4.png"
						class="small" alt="">
					<div class="listing__item__pic__tag">Popular</div>
					<!-- </div> -->
					<div class="listing__item__text">
						<div class="listing__item__text__inside">
							<h5>${smVO.store_name}</h5>
							<div class="listing__item__text__rating">
								<div class="listing__item__rating__star">
									<span class="icon_star"></span> <span class="icon_star"></span>
									<span class="icon_star"></span> <span class="icon_star"></span>
									<span class="ic on_star-half_alt"></span>
								</div>
								<h6>$40 - $70</h6>
							</div>
							<ul>
								<li><span class="icon_pin_alt"></span>${smVO.addr}</li>
								<li><span class="icon_phone"></span> (+12) 345-678-910</li>
							</ul>
						</div>
						<div class="listing__item__text__info">
							<div class="listing__item__text__info__left">
								<img
									src="<%=request.getContextPath()%>/front-customer-end/SearchResult/img/listing/list_small_icon-1.png"
									alt=""> <span>Restaurant</span>
							</div>
							  <a href="<%=request.getContextPath()%>/front-customer-end/booking/booking.jsp" class="bookingHref">
                            <button class="bookingButtom">我要訂位</button>
                       		 </a>
						</div>
					</div>
				</div>
			</c:forEach>
			<!-- </div> -->
			<!-- ===================在這前面加餐廳列表============== -->
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
	<script
		src="<%=request.getContextPath()%>/front-customer-end/SearchResult/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-customer-end/SearchResult/js/Preloader.js"></script>
	<!-- 神的指示，感謝輯神 -->
	<!-- <script>
        window.addEventListener("scroll", function() {
            let opac = "rgba(34, 39, 54," + ((window.scrollY + 62.5) / 125).toString() + ")";
            $(".header").css("background-color", opac);
        });
</script> -->
	<!-- 神的指示，感謝輯神 -->
</body>
</html>