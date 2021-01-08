<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>非食不可後臺登入頁面</title>

<!--Bootsrap 4 CDN-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!--Fontawesome CDN-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">

<!-- Css Styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/emp/css/back_login/back_login.css">

</head>

<body>

	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					<h3>Sign In</h3>
				</div>
				<div class="card-body">
					<form action="<%=request.getContextPath()%>/emplogin.do" method="post">
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" class="form-control" placeholder="員工編號"
								name="emp_no">
						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" class="form-control" placeholder="登入密碼"
								name="emp_pwd">
						</div>
						<!-- <div class="row align-items-center remember">
    								<input type="checkbox">Remember Me
							</div> -->
						<div class="form-group">
							<div class="emp_errormsgs" style="color:yellow; display:inline-block">
								<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li>${message}</li>
										</c:forEach>
									</ul>
								</c:if>
							</div>
							<input type="submit" name="action" value="login"
								class="btn float-right login_btn">
						</div>
					</form>
				</div>
				<div class="card-footer">
					<img src="<%=request.getContextPath()%>/back-end/emp/img/logo-04.png"
						id="logo">
				</div>
			</div>
		</div>
	</div>
</body>
<!-- Js Plugins -->
<script
	src="<%=request.getContextPath()%>/back-end/emp/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/back-end/emp/js/bootstrap.min.js"></script>
</body>

</html>