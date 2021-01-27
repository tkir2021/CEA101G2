<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<!DOCTYPE html>
<%
	Store_MemService storemSvc = new Store_MemService();
	List<Store_MemVO> list = storemSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<%@include file="/back-end/leftbar.file"%>
<head>
<meta charset="UTF-8">
<title>店家管理</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/store/css/mike.css" type="text/css">
</head>
<body>
<section id="all">
	<h2>店家管理</h2>
	<table id="customers">
		<tr>
			<th>店家編號</th>
			<th>店名</th>
			<th>地址</th>
			<th>平台權限狀態</th>
			<th>平台管理</th>
		</tr>

		<c:forEach var="store_MemVO" items="${list}" >
                 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do">
                <tr>
                    <td>${store_MemVO.store_no}</td>
                   <td><a href="<%=request.getContextPath() %>/back-end/store/store_no=${store_MemVO.store_no}.jsp">${store_MemVO.store_name}</a></td>
					<td>${store_MemVO.addr}</td>
					<td>
					<c:if test="${store_MemVO.s_permission == 0}">
					【停權】
					</c:if>
					<c:if test="${store_MemVO.s_permission == 1}">
					【正常】
					</c:if>
					</td>
					
					<td>
						
						<input type="hidden" name="action" value="update_s_permission">
						<input type="hidden" name="store_no" value="${store_MemVO.store_no}">
						<input type="hidden" name="s_permission" value="${store_MemVO.s_permission}">
						<input type="submit" value="關閉" ${store_MemVO.s_permission == 0 ? 'disabled':''}>
						<input type="submit" value="開啟" ${store_MemVO.s_permission == 1 ? 'disabled':''}>
					</td>
					
					
                </tr>
                 </FORM>
                </c:forEach>
            </table>
<%@ include file="/back-end/scriptpart.file"%>	
</section>
</body>
</html>