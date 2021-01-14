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
<title>員工列表</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/emp/css/back_system/emp.css" type=" text/css">
</head>

<body class="ov-hid">

	<section class="main" style="margin-top:10px">
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
												<td class="empNo">${empVO.emp_no}</td>
												<td class="empName">${empVO.emp_name}</td>
												<td class="empDate">${empVO.emp_date}</td>
												<td class="empEmail">${empVO.emp_mail}</td>
												<td class="empStatus">${empVO.emp_status==1? '在職': '離職'}</td>
												<td>
													<form method="post"	action="<%=request.getContextPath()%>/emp/emp.do">
														<div class="btn-group">
														<input type="hidden" name="emp_no"  value="${empVO.emp_no}">
															<button type="button" class="btn btn-primary"
																data-toggle="modal" data-target="#modalUpdate"
																style="margin: 0px">Edit</button>
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
				<!-- Modal add-->
				<form method="post"	action="<%=request.getContextPath()%>/emp/emp.do">
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
							<div class="modal-body">
								<div class="form-group">
									<label>員工姓名</label> <input type="text" name="emp_name"
											value="${empVO.emp_name}" class="form-control" placeholder="輸入姓名" pattern="^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,6}$" required>
								</div>
								<div class="form-group">
									<label>到職日</label> <input type="date" name="emp_date"
											value="${empVO.emp_date}" class="form-control" required>
								</div>
								<div class="form-group">
									<label>電子郵件</label> <input type="email" name="emp_mail"
											value="${empVO.emp_mail}" class="form-control" pattern="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$" required>
								</div>
							</div>							
								<div class="modal-footer">
									<button type="submit" name="action" value="insert" class="btn btn-success">Save</button>
								</div>							
						</div>
					</div>
				</div>
			</form>
				<!-- Update --> 	
				<div class="modal fade" id="modalUpdate" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Update
									Employee</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do">
							<div class="modal-body">
									<input type="text" name="emp_no"
											 class="form-control" id="update_empno" style="display:none">
									
									<div class="form-group">
										<label>員工姓名</label> <input type="text" name="emp_name"
											 class="form-control" id="update_empname">
									</div>
									<div class="form-group">
										<label>在職日</label> <input type="date" name="emp_date" class="form-control"
											id="update_empdate">
									</div>
									<div class="form-group">
										<label>電子郵件</label> <input type="email" name="emp_mail" class="form-control"
											id="update_empemail">
									</div>
									<div class="form-group">
										<label>是否在職</label> 
										<label><input type="radio" name="emp_status" id="update_empstatus" class="onduty" value="1">在職</label>
										<label><input type="radio" name="emp_status" id="update_empstatus" class="quit" value="0">離職</label>
									</div>
							</div>
							
							<div class="modal-footer">
								<!-- <button type="button" class="btn btn-outline-secondary"
									data-dismiss="modal">Close</button> -->
								<button type="submit" class="btn btn-success" name="action" value="update">Save</button>
							</div>
						</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Sidebar End -->
	<%@ include file="/back-end/scriptpart.file"%>
	<!-- 自訂新增 -->

	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script>
	/*當.btn.btn-primary click後，update燈箱中的id為update_empXXX中的屬性val去取btn.btn-primary的父層td裡的鄰居empno*/
$(".btn.btn-primary").click(function(){
	$("#update_empno").val($(this).parents("td").siblings(".empNo").text());
	$("#update_empname").val($(this).parents("td").siblings(".empName").text());
	/*console.log($(this).parents("td").siblings(".empName").text());*/
	$("#update_empdate").val($(this).parents("td").siblings(".empDate").text());
	$("#update_empemail").val($(this).parents("td").siblings(".empEmail").text());
// 	$("#update_empstatus").val($(this).parents("td").siblings(".empStatus").text());

	if($(this).parents("td").siblings(".empStatus").text()=="在職"){
		$(".onduty").attr("checked",true);
	}else{
		$(".quit").attr("checked",true);
	}
	
	});
	
</script>
</body>


</html>