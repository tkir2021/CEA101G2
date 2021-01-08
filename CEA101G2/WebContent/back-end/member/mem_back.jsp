<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<%@ include file="/back-end/leftbar.file" %>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/back-end/member/css/mem_back.css">

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.Mem_DataService" />

<section>
<div class="listing">
            <div class="tbl-header">
                <table cellpadding="0" cellspacing="0" border="0">
                    <thead>
                        <tr>
                            <th>會員編號</th>
                            <th>會員狀態</th>
                            <th>會員等級</th>
                            <th>會員帳號</th>
                            <th>會員密碼</th>
                            <th>會員姓名</th>
                            <th>會員電話</th>
                            <th style="width:165px">E-mail</th>
                            <th>消費次數</th>
                            <th>儲值金</th>
                            <th>被檢舉<br>次數</th>
                            
                        </tr>
                    </thead>
                </table>
            </div>
            <div class="tbl-content">
                <table cellpadding="0" cellspacing="0" border="0">
                    <tbody>
                    <c:forEach var="memVO" items="${memSvc.getAll()}">
                        <tr>        
                            <td class="themem">${memVO.mem_no}</td>
                         	<td>
	                         	<FORM method="post" class="form1" action="<%=request.getContextPath()%>/member/member.do">              
	                                <select name="mem_auth" onchange="submit(),alert('${memVO.mem_no}的狀態已改變')">
<%-- 	                                    <option value="${(memVO.mem_auth==2)?2:1}" >${(memVO.mem_auth==2)?"正常":"已停權"}</option> --%>
<%-- 	                                    <option value="${(memVO.mem_auth==2)?1:2}" >${(memVO.mem_auth==2)?"已停權":"正常"}</option> --%>
	                                	
	                                	<option value="1" ${(memVO.mem_auth==1)?'selected':''} >已停權</option>
	                                	<option value="2" ${(memVO.mem_auth==2)?'selected':''}>正常</option>
	                                	
	                                </select>
	                                <input type="hidden" name="mem_no" class="mem_no" value="${memVO.mem_no}">
	                                <input type="hidden" name="action" value="updateAuth">
	                        	 </FORM> 
                           	</td>
                            <td>${memVO.mem_grade}</td>
                            <td>${memVO.mem_acct}</td>
                            <td>${memVO.mem_pwd}</td>
                            <td>${memVO.mem_name}</td>
                            <td>${memVO.men_phone}</td>
                            <td style="width:165px">${memVO.mem_mail}</td>
                            <td>${memVO.consume_times}</td>
                            <td>${memVO.deposit}</td>
                            <td>${memVO.report_count}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
</section>
 <script src="<%=request.getContextPath() %>/back-end/member/js/mem_back.js"></script>
 <%@ include file="/back-end/scriptpart.file" %>

<script>

//$("select").change(function(){
	//console.log($(this).closest("td").siblings(".themem").text());
  	//$(".mem_no").val($(this).closest("td").siblings(".themem").text());
  //console.log($(this).closest("input").val());
  //alert($(".mem_no").val()+"的狀態已改變");
 //	$(".form1").submit();
// alert("該會員狀態已改變");
//});
</script>
</body>
</html>