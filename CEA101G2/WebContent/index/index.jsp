<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Directing Template">
    <meta name="keywords" content="Directing, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Let's Eat 非食不可</title>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700;800&display=swap" rel="stylesheet">
    <!-- Css Styles -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/index/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/index/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/index/css/flaticon.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/index/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/index/css/barfiller.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/index/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/index/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/index/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/index/css/slicknav.min.css" type="text/css">
    <!-- <link rel="stylesheet" href="css/style.css" type="text/css"> -->
    <!-- 自定義的CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/index/css/style_Bella.css" type="text/css">
    <!-- owl -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.0.0-beta.2.4/assets/owl.carousel.min.css"></link>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.0.0-beta.2.4/assets/owl.theme.default.min.css"></link>
    <!-- AOS 緣起浮動動畫 -->
    <link href="<%=request.getContextPath() %>/index/css/aos.css" rel="stylesheet">
    <!-- 標題圖-->
    <link rel="icon" href="<%=request.getContextPath() %>/index/img/favicon.ico" type="image/x-icon">
	<style>
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
            right: 6px;
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
        <img class="loaderpic" src="<%=request.getContextPath() %>/index/img/logo-07.png" alt="">
        <div class="loader"></div>
    </div>
    <!-- Header Section Begin -->
    <header class="header" style=" position: fixed; max-width:100%">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="header__logo">
                        <a href="<%=request.getContextPath() %>/index/index.jsp"><img src="<%=request.getContextPath() %>/index/img/logo-04.png" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-9 col-md-9">
                    <div class="header__nav">
                        <nav class="header__menu mobile-menu">
                            <ul>
                                <li><a href="#position" id="to_position">平台緣起</a>
                                </li>
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
                            <a href="<%=request.getContextPath() %>/front-customer-end/member/memLogin.jsp" class="primary-btn" title="註冊會員"><i class="fa fa-plus"></i>加入我們</a>
                            <a href="<%=request.getContextPath() %>/front-customer-end/member/memLogin.jsp" class="login-btn" title="我要登入"><i class="fa fa-user"></i></a>
                            <ul class="login-logout" style="display: none;">
                   				 <li><a href="<%=request.getContextPath() %>/member/member.do?action=logout">我要登出</a></li>
                			</ul>
                        </div>
                    </div>
                </div>
            </div>
            <div id="mobile-menu-wrap"></div>
        </div>
    </header>
    <!-- Header Section End -->
    <!-- Hero Section Begin -->
    <!-- 首頁圖 -->
    <section class="indexPic" style="overflow: hidden;">
        <div class="hero_banner">
            <div class="slideshow" style="width: 100%; height: 600px;">
                <div class=" item">
                    <img src="<%=request.getContextPath() %>/index/food_img/pooja-chaudhary-q66grqqHpDQ-unsplash.jpg" style="max-width: 100%">
                </div>
                <div class="item">
                    <img src="<%=request.getContextPath() %>/index/food_img/photo-1480562812028-47fd3a3e43ae.jfif" style="max-width: 100%">
                </div>
                <div class="item">
                    <img src="<%=request.getContextPath() %>/index/food_img/photo-1530153382845-fdcb2aa7c74e.jfif" style="max-width: 100%">
                </div>
                <div class="item">
                    <img src="<%=request.getContextPath() %>/index/food_img/vy-huynh-rcHHKG01IPY-unsplash.jpg" style="max-width: 100%">
                </div>
                <div class="item">
                    <img src="<%=request.getContextPath() %>/index/food_img/haryo-setyadi-yvzzemH8-J0-unsplash.jpg" style="max-width: 100%">
                </div>
            </div>
        </div>
      
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hero__text">
                        <div class="hero__search__form">
                            <form method="post" action="<%=request.getContextPath()%>/mem/search.do">
                                <div class="thebar" style="width: 1162px">
                                    <input type="text" name="keyword" placeholder="Search...">
                                    <input type="hidden" name="action" value="search">
                                    <div class="select__option">
                                        <select>
                                            <option value="">搜尋地點</option>
                                        </select>
                                    </div>
                                    <button type="submit"><i class="fas fa-search"></i></button>
                                </div>
                            </form>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- 往上移動 -->
    <img class="toTop" src="<%=request.getContextPath() %>/index/img/icon/back-to-top-icon-png-8.jpg">
    <!-- 瓶中信遊戲 -->
<!--     <div class="playGame"></div> -->
<!--     <img class="gamebottle" src="img/icon/螢幕擷取畫面 2020-12-02 184704.png"> -->
    <section class="most-search spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Popular Restaurants Suggested for you</h2>
                        <p>Let's try the highly graded restaurants and have a good day.</p>
                    </div>
                </div>
            </div>
       
            <div class="row">
                <div class="col-lg-12">
                    <div class="tab-content">
                        <div class="tab-pane active" id="tabs-1" role="tabpanel" style="overflow-x: hidden">
                            <div class="row" data-aos="zoom-in">
                                
                              <!-- 1美食圖----------------------------------------------------------Start-->                              
                        <jsp:useBean id="storeSvc" scope="page" class="com.store.model.Store_MemService" />
                             <c:forEach var="storeVO" items="${storeSvc.getAllindex()}">
                                <div class="col-lg-4 col-md-6">
                                <a href="<%=request.getContextPath() %>/store/store.do?store_no=${storeVO.store_no}&action=getThisStore&location=/front-customer-end/shopping/EShop.jsp">
                                <div class="listing__item">
										<!-- 店家圖片 -->
                                        <div class="listing__item__pic set-bg" data-setbg="<%=request.getContextPath() %>/store/store.do?store_no=${storeVO.store_no}&action=getOneImage">
                                            <img src="<%=request.getContextPath() %>/index/img/listing/list_icon-4.png" alt="">
                                            <div class="listing__item__pic__tag">Popular</div>
                                        </div>
                                        <div class="listing__item__text">
                                            <div class="listing__item__text__inside">
                                                <h5>${storeVO.store_name}</h5>
                                                <div class="listing__item__text__rating">
                                                    <div class="listing__item__rating__star">
										<!-- 星星數量動態產生    -->
                                                        <c:forEach begin="1" end="${storeVO.star_total}" varStatus="j" step="1"> 
                                                        <span class="icon_star"></span>
                                                        <span class="ic on_star-half_alt"></span>
                                                    	</c:forEach>  	
                                                    	
                                                    </div>
<!--                                                     <h6>$40 - $70</h6> -->
                                                </div>
                                                <ul>
                                                    <li><span class="icon_pin_alt"></span> ${storeVO.addr}</li>
                                                    <li><span class="icon_phone"></span> (+12) 345-678-910</li>
                                                </ul>
                                            </div>
                                            <div class="listing__item__text__info">
                                                <div class="listing__item__text__info__left">
                                                    <img src="<%=request.getContextPath() %>/index/img/listing/list_small_icon-1.png" alt="">
                                                    <span>Restaurant</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                             </c:forEach>

						<!-- 美食餐廳排行End -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </section>
    <!-- Most Search Section End -->
  
    <!-- Feature Location Section Begin -->
    <section class="feature-location spad" style="overflow-x: hidden;">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Top Featured Locations</h2>
                        <p>Explore restaurants, bars, and cafés by locality</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <a href="<%=request.getContextPath() %>/mem/search.do?keyword=台北&action=search" class="feature__location__item large-item set-bg" data-setbg="<%=request.getContextPath() %>/index/img/feature-location/taipei.jpg">
                        <div class="feature__location__item__text" data-aos="fade-down">
                            <h5>Taipei</h5>
                            <ul>
                                <li>2045 Listings</li>
                                <li>3648 Users</li>
                            </ul>
                        </div>
                    </a>
                </div>
                <div class="col-lg-6">
                    <div class="row">
                        <div class="col-lg-6 col-md-6">
                            <a href="<%=request.getContextPath() %>/mem/search.do?keyword=台中&action=search" class="feature__location__item set-bg" data-setbg="<%=request.getContextPath() %>/index/img/feature-location/taichung.jpg">
                                <div class="feature__location__item__text" data-aos="fade-down">
                                    <h5>Taichung</h5>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <a href="<%=request.getContextPath() %>/mem/search.do?keyword=高雄&action=search" class="feature__location__item set-bg" data-setbg="<%=request.getContextPath() %>/index/img/feature-location/kaohsiung-2728058__480.jpg">
                                <div class="feature__location__item__text" data-aos="fade-down">
                                    <h5>Kaohsiung</h5>
                                </div>
                            </a>
                        </div>
                    </div>
                    <a href="<%=request.getContextPath() %>/mem/search.do?keyword=台南&action=search" class="feature__location__item set-bg" data-setbg="<%=request.getContextPath() %>/index/img/feature-location/thainan.JPG">
                        <div class="feature__location__item__text" id="position" data-aos="fade-down">
                            <h5>Tainan</h5>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </section>
    <!-- Feature Location Section End -->
    <!-- Testimonial Section Begin -->
    <section class="testimonial spad set-bg" style="overflow-x: hidden" data-setbg="<%=request.getContextPath() %>/index/food_img/photo-1603248866939-f6ddc5d25a64.jfif">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2 data-aos="fade-down">非食不可帶給你滿滿的美食饗宴</h2>
                        <p data-aos="fade-down">我們有著5000++的客戶們相信著我們，歡迎各大店家加入我們的行列</p>
                    </div>
                    <div class="testimonial__slider owl-carousel">
                        <div class="testimonial__item" data-hash="review-1">
                            <p data-aos="fade-down">" 民以食為天，但排山倒海的店家讓你不知如何挑選嗎？非食不可平台讓你可以有效又快速就能吃到在地推薦的美食，並且大大減少踩雷的風險。
                                科技日新月異，科技冷漠使你沒有朋友嗎？非食不可平台提供了揪團的服務，除了能使你填飽肚子外，也能結交到新朋友，身心靈一次滿足。
                                店家方面更是能夠透過平台提高曝光率與增加收益，將傳統的口耳相傳結合現代的網路傳播，更提升名聲遠播的速度，為您實現發大財的夢想。"</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Testimonial Section End -->
    <!-- Newslatter Section Begin 移除 -->
    <!-- Newslatter Section End -->
    <!-- Footer Section Begin -->
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="footer__about">
                        <div class="footer__about__logo">
                            <a href="<%=request.getContextPath() %>/index/index.jsp"><img src="<%=request.getContextPath() %>/index/img/logo-06.png" alt=""></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 offset-lg-1 col-md-6">
                    <div class="footer__address">
                        <ul>
                            <li>
                                <span>Call Us:</span>
                                <p>(+12) 345-678-910</p>
                            </li>
                            <li>
                                <span>Email:</span>
                                <p>info.colorlib@gmail .com</p>
                            </li>
                            <li>
                                <span>Fax:</span>
                                <p>(+12) 345-678-910</p>
                            </li>
                            <li>
                                <span>Connect Us:</span>
                                <div class="foote -->r__social">
                                    <img src="<%=request.getContextPath() %>/index/img/icon/iconfinder_46-facebook_104458.png">
                                    <img src="<%=request.getContextPath() %>/index/img/icon/iconfinder_Instagram_1298747.png">
                                    <img src="<%=request.getContextPath() %>/index/img/icon/iconfinder_Twitter_570625.png">
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- <div class="col-lg-3 offset-lg-1 col-md-6">
                    <div class="footer__widget">
                        <ul>
                            <li><a href="#">Home</a></li>
                            <li>You come here for food, and we come here for you. Thanks for coming. Let's Eat, and have meal together</li>
                        </ul>
                    </div>
                </div>
            </div> -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="footer__copyright">
                            <div class="footer__copyright__text">
                                <p>
                                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                    Copyright &copy;<script>
        document.write(new Date().getFullYear());
                                    </script> All rights reserved | We are Group2 CEA_101 <i class="fa fa-heart" aria-hidden="true"></i> I appreciate that we are the "team".
                                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </footer>
    <!-- Footer Section End -->
    <!-- Js Plugins -->
    <script src="<%=request.getContextPath() %>/index/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/index/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath() %>/index/js/jquery.nice-select.min.js"></script>
    <script src="<%=request.getContextPath() %>/index/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath() %>/index/js/jquery.nicescroll.min.js"></script>
    <script src="<%=request.getContextPath() %>/index/js/jquery.barfiller.js"></script>
    <script src="<%=request.getContextPath() %>/index/js/jquery.magnific-popup.min.js"></script>
    <script src="<%=request.getContextPath() %>/index/js/jquery.slicknav.js"></script>
    <script src="<%=request.getContextPath() %>/index/js/owl.carousel.min.js"></script>
    <script src="<%=request.getContextPath() %>/index/js/main.js"></script>
    <!-- 淡入淡出-->
    <!--     <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.cycle.all.js"></script> -->
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/index/js/jquery.cycle.all.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('.slideshow').cycle();
        });
    </script>
    <!-- <script>
        $('.owl-carousel').owlCarousel({
            loop: true,
            margin: 0,
            nav: false,
            /* 控制列 */
            autoWidth: true,
            /* 可自行設定輪播寬度 */
            items: 1,
            /* 一頁出現的張數 */
            autoplay: true,
            /* 自動輪播 */
            autoplayTimeout: 5000,
            /* 輪播速度 */
            autoplayHoverPause: false,
            // 導航點顯示
            dots: true,
        });
    </script> -->
    <!-- 神的指示，感謝輯神 -->
    <script>
        window.addEventListener("scroll", function() {
            let opac = "rgba(34, 39, 54," + (window.scrollY / 620).toString() + ")";
            $(".header").css("background-color", opac);
        });
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
    <!-- 神的指示，感謝輯神 -->
    <script src="<%=request.getContextPath() %>/index/js/aos.js"></script>
    <script>
  //套版動畫
        AOS.init(); 
    </script>
</body>

</html>