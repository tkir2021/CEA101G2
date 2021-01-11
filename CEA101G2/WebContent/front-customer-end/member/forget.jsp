<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<!DOCTYPE html>
<html lang="zh-tw">
<%@ include file="/front-customer-end/member/header.file" %>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Directing Template">
    <meta name="keywords" content="Directing, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>forget</title>
    <!-- css引用 -->
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/Header_Footer.css" type="text/css">
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/forget.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
</head>

<body>
    
    
    <!-- =======================================Content========================================== -->
    <section>
        <!-- 以下三行div為bootstrap的格線語法，請不要新增屬性、id，請參考格線語法自行更改 ((RWD課程應該會上))-->
        <div>
            <form action="<%=request.getContextPath() %>/member/LoginForget.do" method="post">
                <div class="forget">
                    <img class="forget2" src="http://previews.123rf.com/images/stodolskaya/stodolskaya1511/stodolskaya151100027/49219342-Connexion-de-l-utilisateur-ou-l-acc-s-authentification-ic-ne-Banque-d'images.jpg" alt="image" />
                    <div class="form-input"><i aria-hidden="true"></i>
                        <input type="text" name="mem_acct" value="" placeholder="請輸入帳號" /></div>
                    
                    <div class="form-input"><i aria-hidden="true"></i>
                        <input type="email"name="mem_mail" value="" placeholder="請輸入信箱" /></div>
                    <input type="hidden" name="action" value="sendMail" />
                    <input type="submit" id="submit" value="密碼找回" /><br>
                </div>
            </form>
        </div>
    </section>
    <!-- 內容end 請在以上區塊做切版-->
    <!-- ===========================================Footer====================================== -->
    <footer class="footer">
        <div class="container">
            <div class="footer__about">
                <div class="footer__about__logo">
                    <a href="./index.html"><img src="img/logo-06.png" alt=""></a>
                </div>
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
                                <img src="img/icon/iconfinder_46-facebook_104458.png">
                                <img src="img/icon/iconfinder_Instagram_1298747.png">
                                <img src="img/icon/iconfinder_Twitter_570625.png">
                            </div>
                        </li>
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
    <!-- JS引用 -->
    <script src="<%=request.getContextPath() %>/front-customer-end/member/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/front-customer-end/member/js/Preloader.js"></script>
    
</body>

</html>