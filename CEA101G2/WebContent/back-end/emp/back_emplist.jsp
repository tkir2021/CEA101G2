<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<!DOCTYPE html>
<%
	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>
<html>
<%@include file="/back-end/leftbar.file"%>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/emp/css/back_system/emp.css"
	type=" text/css">

</head>

<body class="ov-hid">

	<section class="main">
		<div class="emplist">
			<div id="apps" class="container">
				<div class="emprow mt-5">
					<div class="col-md-12 m-auto">
						<form method="post"
							action="<%=request.getContextPath()%>/emp/emp.do">
							<button type="button" class="btn btn-success" data-toggle="modal"
								data-target="#modalAdd" style="margin-top: 5px">Add</button>
						</form>
						<div class="card">
							<div class="card-header">
								<div class="d-flex flex-row">
									<!-- <div class="mr-auto">員工列表</div> -->
									<div class="ml-auto"></div>
								</div>
							</div>
							<div class="card-body p-0">
								<table class="table table-striped">
									<thead>
										<tr id="mytabletr" style="line-height: 10px">
											<th>員工編號</th>
											<th>員工姓名</th>
											<th>雇用日期</th>
											<th>電子郵件</th>
											<th>是否在職</th>
											<th></th>
										</tr>
									</thead>

									<tbody>
										<c:forEach var="empVO" items="${list}">
											<tr id="mytabletr" style="line-height: 33px; text-align:center;height:0px">
												<td>${empVO.emp_no}</td>
												<td>${empVO.emp_name}</td>
												<td>${empVO.emp_date}</td>
												<td>${empVO.emp_mail}</td>
												<td class="emp_status"></td><!-- ${empVO.emp_status} -->
												<td>
													<form method="post"
														action="<%=request.getContextPath()%>/emp/emp.do">
														<div class="btn-group">
															<button type="button" class="btn btn-primary"
																id="editbtn" data-toggle="dropdown" aria-haspopup="true"
																aria-expanded="false" data-toggle="modal"
																data-target="#modalUpdate" style="margin: 0px">修改
															</button>
														</div>
													</form>

												</td>
											</tr>
										</c:forEach>
									</tbody>

								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- Modal -->
				<div class="modal fade" id="modalAdd" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">add employee</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<form method="post"
								action="<%=request.getContextPath()%>/emp/emp.do">
								<div class="modal-body">
									<c:if test="${not empty errorMsgs}">
										<font style="color: red">請修正以下錯誤:</font>
										<ul>
											<c:forEach var="message" items="${errorMsgs}">
												<li style="color: red">${message}</li>
											</c:forEach>
										</ul>
									</c:if>
									<div class="form-group">
										<label>員工姓名</label> <input type="text" name="emp_name"
											value="${empVO.emp_name}" class="form-control">
									</div>
									<div class="form-group">
										<label>到職日</label> <input type="date" name="emp_date"
											value="${empVO.emp_date}" class="form-control">
									</div>
									<div class="form-group">
										<label>電子郵件</label> <input type="email" name="emp_mail"
											value="${empVO.emp_mail}" class="form-control">
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-outline-secondary"
										data-dismiss="modal">Close</button>

									<button type="submit" name="action" value="insert"
										class="btn btn-success">Save</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal fade" id="modalUpdate" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Modal update
									Employee</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form>
									<div class="form-group">
										<label>Name</label> <input type="text" class="form-control"
											v-model="updateEmployee.name">
									</div>
									<div class="form-group">
										<label>Salary</label> <input type="text" class="form-control"
											v-model="updateEmployee.salary">
									</div>
									<div class="form-group">
										<label>Age</label> <input type="text" class="form-control"
											v-model="updateEmployee.age">
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-outline-secondary"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-success"
									@click="onUpdateEmployee">Save</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Sidebar End -->
	<%@ include file="/back-end/scriptpart.file"%>
	<!-- 自訂新增 -->
	<script>
	
	
	</script>
</body>


</html>