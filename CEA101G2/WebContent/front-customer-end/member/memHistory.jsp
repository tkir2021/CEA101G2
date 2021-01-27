<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.mem.model.*"%>

<% String account =(String) session.getAttribute("account");
 	Mem_DataVO mem_dataVO = new Mem_DataService().getMemAcc(account);
 	pageContext.setAttribute("mem_dataVO",mem_dataVO);
%>

<jsp:useBean id="storeMemSvc" scope="page" class="com.store.model.Store_MemService" />

<!DOCTYPE html>
<html lang="zh-tw">
<%@ include file="/front-customer-end/member/header.file" %>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Directing Template">
    <meta name="keywords" content="Directing, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>歷史記錄查詢</title>
    <!-- css引用 -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/Header_Footer.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/jquery.dataTables.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/front-customer-end/member/css/memHistory.css" type="text/css">
    <link rel="icon" href="<%=request.getContextPath() %>/front-customer-end/member/img/favicon.ico" type="image/x-icon">
</head>

<body>
    
  
    <!-- =======================================Content========================================== -->
    <section>
        <div class="tabs">
            <div class="tab-2">
                <label for="tab2-1">
                    <h5 class="recordTitle">訂位紀錄</h5>
                </label>
                <input id="tab2-1" name="tabs-two" type="radio" checked="checked">
                <div class="main">
                    <table id="bookingTable">
                        <thead>
                            <th>訂位編號</th>
<!--                     		<th>會員編號</th> -->
<!-- 							<th>店家編號</th> -->
                            <th>店家名稱</th>
<!--                            <th>揪團編號</th> -->
                            <th>訂位日期</th>
                            <th>訂位時段</th>
                            <th>訂位人數</th>
<!--                     		<th>訂位狀況</th> -->
<!--                     		<th>出席狀況</th> -->
                            <th>評分</th>
                            <th>訂單日期</th>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="tab-2">
                <label for="tab2-2">
                    <h5 class="recordTitle">訂餐紀錄</h5>
                </label>
                <input id="tab2-2" name="tabs-two" type="radio">
                <div>
                    <table id="masterTable">
                        <thead>
                            <th>訂餐編號</th>
                            <th>店家名稱</th>
                            <th>訂購日期</th>
                            <th>付款方式</th>
                            <th>訂單總金額</th>
                            <th>訂單狀況</th>
                            <th>取餐狀況</th>
                            <th>評分</th>
                            <th>訂單詳情</th>
                        </thead>
                    </table>
                </div>
                <!-- Modal -->
                <div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="detailModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="detailModalLabel"><b>訂單詳情</b></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <table id="detailTable">
                                    <thead>
                                        <th>餐點名稱</th>
                                        <th>餐點圖片</th>
                                        <th>餐點規格</th>
                                        <th>數量</th>
                                        <th>單價</th>
                                        <th>金額小計</th>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- 內容end 請在以上區塊做切版-->
    <!-- ===========================================Footer====================================== -->
    <%@ include file="footer.file" %>
    
    <!-- JS引用 -->
    <script src="<%=request.getContextPath() %>/front-customer-end/member/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/front-customer-end/member/js/Preloader.js"></script>
    <script src="<%=request.getContextPath() %>/front-customer-end/member/js/bootstrap.min.js"></script>  
    <script src="<%=request.getContextPath() %>/front-customer-end/member/js/jquery.dataTables.min.js"></script>
    <script src="<%=request.getContextPath() %>/front-customer-end/member/js/sweetalert.min.js"></script>
    <script>
        let bookingPath = "<%=request.getContextPath()%>/booking/booking.do";
        let masterPath = "<%=request.getContextPath()%>/ordermaster/ordermaster.do";
    	let detailPath = "<%=request.getContextPath()%>/orderdetail/orderdetail.do";
    	let memno = "${mem_dataVO.getMem_no()}";
    	let order_no = "";

        $(document).ready(function() {
        	
        	//取得訂位記錄
            $("#bookingTable").DataTable({
                "autoWidth": true,
                "bAutoWidth": false,
                // 			"bLengthChange": false,
                "lengthMenu": [5, 10, 15, 20],
                // 			destroy: true,
                ajax: {
                    url: bookingPath,
                    data: {
                        "memno": memno,
                        "action": "bookingDisplay"
                    },
                    dataType: "json",
                    type: "POST",
                    //  	            async: false,
                    dataSrc: function(json) {
                        return json;
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        alert("資料取得錯誤！");
                    },
                },
                order: [[ 0, "desc" ]],
                columns: [
                    { data: "bookingno" },
                    // 			    { data: "memno" },
                    // 			    { data: "storeno" },
                    { data: "storename" },
                    //                     { data: "groupno", defaultContent: "" },
                    { data: "bookingdate" },
                    { data: "timeperiod" },
                    { data: "people" },
//                     			    { data: "bookingstatus" },
//                     			    { data: "attendstatus" },
                    {
                        data: "givestar",
                        defaultContent: "",
                        "render": function(data, type, obj, meta) {
                            if (data === 0) {
                                let opstr = "";

                                for (let i = 1; i <= 5; i++) {
                                    opstr += "<option value =" + i + ">" + i + "</option>"
                                }
                                return '<select class="star">' + opstr + '</select>' + '&nbsp;<button id="givestar" type="button" class="btn btn-primary" value="" />評分</button>';
                            } else {
                                return data;
                            }
                        }
                    },
                    { data: "ordercommit" }
                    // 			    { data: "ordercommit", "orderable": false ,"width":"100px","searchable": false, render:function(data,type,row,meta){
                    // 			    	return data;
                    // 			    	}
                    // 			    }
                ],
                // 			columnDefs: [],
                language: {
                    "lengthMenu": "顯示 _MENU_ 筆資料",
                    "sProcessing": "處理中...",
                    "sZeroRecords": "没有匹配结果",
                    "sInfo": "目前有 _MAX_ 筆資料",
                    "sInfoEmpty": "目前共有 0 筆紀錄",
                    "sInfoFiltered": " ",
                    "sInfoPostFix": "",
                    "sSearch": "尋找:",
                    "sUrl": "",
                    "sEmptyTable": "尚未有資料紀錄存在",
                    "sLoadingRecords": "載入資料中...",
                    "sInfoThousands": ",",
                    "oPaginate": {
                        "sFirst": "首頁",
                        "sPrevious": "上一頁",
                        "sNext": "下一頁",
                        "sLast": "末頁"
                    },
                    "order": [
                        [0, "desc"]
                    ],
                    "oAria": {
                        "sSortAscending": ": 以升序排列此列",
                        "sSortDescending": ": 以降序排列此列"
                    }
                }

            });

            // 		//隱藏欄位
            //  		$("#bookingTable").DataTable().columns([0]).visible(false);


            // 		//alert點擊到的cell值
            //  		var table = $('#bookingTable').DataTable(); 		 
            //  		$('#bookingTable tbody').on( 'click', 'td', function () {
            //  		    alert( table.cell( this ).data() );
            //  		} );

            
            
            
      		//取得訂餐主檔記錄
     		$("#masterTable").DataTable({
    			"autoWidth": true,
    			"bAutoWidth": false,
//     			"bLengthChange": false,
    			"lengthMenu": [5, 10, 15, 20],
    			destroy: true,
    			ajax:{
    				url: masterPath,
    				data:{
    	             	"memno": memno,
    	             	"action": "orderMasterDisplay"
    				},
    				dataType: "json",
    	            type:"POST",
//      	            async: false,
    	            dataSrc: function (json) {
    	                return json;
    	            },
    	            error: function (jqXHR, textStatus, errorThrown) {
    	                alert("資料取得錯誤！");
    	            },	            
    			},
    			order: [[ 0, "desc" ]],
    			columns: [
    			    { data: "order_no" },
    				{ data: "storename" },
    			    { data: "order_date" },
    			    { data: "pay_type", "render": function (data, type, obj, meta) { 
    			    	if(data === 0)
    			    		return "儲值金";
    			    	else if(data === 1)
    			    		return "信用卡";
    			    	else
    			    		return "其它";
    			    	}
    			    },
    			    { data: "order_total" },
    			    { data: "order_status", "render": function (data, type, obj, meta) { 
    			    	if(data === "0")
    			    		return "失敗";
    			    	else if(data === "1")
    			    		return "成立";
    			    	else
    			    		return "其它";
    			    	} 
    			    },
    			    { data: "take_status", "render": function (data, type, obj, meta) { 
    			    	if(data === "0")
    			    		return "未取餐";
    			    	else if(data === "1")
    			    		return "已取餐";
    			    	else
    			    		return "其它";
    			    	}
    			    },
    			    { data: "give_star", defaultContent: "", "render": function (data, type, obj, meta) {
    			    	console.log(data.split("-")[0]);
    			    	
    			    	if(data.split("-")[0] === "0" ){
    			    		return "無法評分";
    			    	}
    			    	else{
    			    		if(data.split("-")[1] === "0"){
                        		let opstr = "";
                        		for(let i=1; i<=5; i++){
                        			opstr +="<option value ="+ i +">"+ i +"</option>"
                        		}
                        		return '<select class="star">' + opstr + '</select>'+'&nbsp;<button id="om_givestar" type="button" class="btn btn-primary" value="" />評分</button>';
                        	}
                        	else{
                        		return data.split("-")[1];
                        	}
    			    	}
    			    	
    			    	
//     			    	if(data === 0){
//                     		let opstr = "";
//                     		for(let i=1; i<=5; i++){
//                     			opstr +="<option value ="+ i +">"+ i +"</option>"
//                     		}
//                     		return '<select class="star">' + opstr + '</select>'+'&nbsp;<button id="om_givestar" type="button" class="btn btn-primary" value="" />評分</button>';
//                     	}
//                     	else{
//                     		return data;
//                     	}
                    } 
                    },
                    { data: "info", defaultContent: "", "render": function (data, type, obj, meta) {
                    	return '<button id="information" type="button" class="btn btn-primary" data-toggle="modal" data-target="#detailModal" value="" />詳情</button>';
                    	} 
                    },
    			],
    			language: {
    	            "lengthMenu": "顯示 _MENU_ 筆資料",
    	            "sProcessing": "處理中...",
    	            "sZeroRecords": "没有匹配结果",
    	            "sInfo": "目前有 _MAX_ 筆資料",
    	            "sInfoEmpty": "目前共有 0 筆紀錄",
    	            "sInfoFiltered": " ",
    	            "sInfoPostFix": "",
    	            "sSearch": "尋找:",
    	            "sUrl": "",
    	            "sEmptyTable": "尚未有資料紀錄存在",
    	            "sLoadingRecords": "載入資料中...",
    	            "sInfoThousands": ",",
    	            "oPaginate": {
    	                "sFirst": "首頁",
    	                "sPrevious": "上一頁",
    	                "sNext": "下一頁",
    	                "sLast": "末頁"
    	            },
    	            "order": [[0, "desc"]],
    	            "oAria": {
    	                "sSortAscending": ": 以升序排列此列",
    	                "sSortDescending": ": 以降序排列此列"
    	            }
    	        }
    	        
    		});
            
            

        });

        $(document).on("click", "#givestar", function() {
            let bookingno = $(this).parents("tr").find(":nth-child(1)").text().substr(0, 10);
            // 			let testGivestar = $(this).parents("tr").find(":selected").text();
            let givestar = $(this).parents("tr").find("select").val();

            $.ajax({
                url: bookingPath,
                type: "post",
                data: {
                    "bookingno": bookingno,
                    "givestar": givestar,
                    "action": "Score"
                },
                success: function(msg) {
                    if (msg == "OK") {
                        swal("感謝您的評分！", "", "success");
                    }
                }
            });

            $("#bookingTable").DataTable().ajax.reload();

        });
        
        

    		

        $(document).on("click", "#om_givestar", function(){
    		order_no = $(this).parents("tr").find(":nth-child(1)").text().substr(0,10);
    		let om_givestar = $(this).parents("tr").find("select").val();
    		
    		$.ajax({
    			url: masterPath,
    			type: "post",
    			data: {
    				"order_no": order_no,
    				"om_givestar": om_givestar,
    				"action": "Score"
    			},
    			success: function(msg){
    				swal("感謝您的評分！", "", "success");
    			}
    		});
    			
    		$("#masterTable").DataTable().ajax.reload();
    		
        });
    	
        
        
        $(document).on("click", "#information", function(){
        	order_no = $(this).parents("tr").find(":nth-child(1)").text().substr(0,10);
        	$("#detailModalLabel").text("訂單編號：" + order_no);
        	
        	//取得訂餐明細記錄
        	$("#detailTable").DataTable({
    			"autoWidth": true,
    			"bAutoWidth": true,
    			"bLengthChange": false,
    			"searching": false,
//     			"lengthMenu": [5, 10, 15, 20],
    			destroy: true,
    			ajax:{
    				url: detailPath,
    				data:{
    	             	"order_no": order_no,
    	             	"action": "orderDetailDisplay"
    				},
    				dataType: "json",
    	            type:"POST",
//      	            async: false,
    	            dataSrc: function (json) {
    	                return json;
    	            },
    	            error: function (jqXHR, textStatus, errorThrown) {
    	                alert("資料取得錯誤！");
    	            },	            
    			},
    			columns: [
    				{ data: "food_name" },
    				{ data: "food_img", defaultContent: "", "render": function (data, type, obj, meta) { 
    						return data;
    						
    						//使用Base64
//     						return '<img src="data:image/jpg;base64,' + data + '"/>';

    						//使用Servlet
    <%-- 					return '<img src="<%=request.getContextPath() %>/member/member.do?mem_no=' + data + '&action=getOneImage" >'; --%>
    					}
    				},
    			    { data: "food_scale" },
    			    { data: "quantity" },
    			    { data: "food_price" },
    			    { data: "total" },
    			],
    			language: {
    	            "lengthMenu": "顯示 _MENU_ 筆資料",
    	            "sProcessing": "處理中...",
    	            "sZeroRecords": "没有匹配结果",
    	            "sInfo": "目前有 _MAX_ 筆資料",
    	            "sInfoEmpty": "目前共有 0 筆紀錄",
    	            "sInfoFiltered": " ",
    	            "sInfoPostFix": "",
    	            "sSearch": "尋找:",
    	            "sUrl": "",
    	            "sEmptyTable": "尚未有資料紀錄存在",
    	            "sLoadingRecords": "載入資料中...",
    	            "sInfoThousands": ",",
    	            "oPaginate": {
    	                "sFirst": "首頁",
    	                "sPrevious": "上一頁",
    	                "sNext": "下一頁",
    	                "sLast": "末頁"
    	            },
    	            "order": [[0, "desc"]],
    	            "oAria": {
    	                "sSortAscending": ": 以升序排列此列",
    	                "sSortDescending": ": 以降序排列此列"
    	            }
    	        }
    	        
    		});
        	
        	//隱藏欄位
        	$("#detailTable").DataTable().columns([1]).visible(false);
        	
        });
        
    </script>
</body>

</html>