package com.orderdetail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;
import com.ordermaster.model.OrderMasterService;
import com.ordermaster.model.OrderMasterVO;
import com.store.model.Food_ListService;
import com.store.model.Store_MemService;

public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page_od.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			System.out.println(req.getParameter("order_no"));

			try {
				String order_no = req.getParameter("order_no");
				/*************************** 2.開始查詢資料 *****************************************/
				OrderDetailService orderdSvc = new OrderDetailService();
				List<OrderDetailVO> orderDetailVO = orderdSvc.getOneOrderDetail(order_no);
				
				if (orderDetailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/orderdetail/select_page_om.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				
				req.setAttribute("orderDetailVO", orderDetailVO);
				String url = "/front-store-end/orderdetail/listOneOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/orderdetail/select_page_od.jsp");
				failureView.forward(req, res);
			}
		}

		
		/************************取得訂餐詳細資料 by Sheng*************************/
		if ("orderDetailDisplay".equals(action)) {
			res.setContentType("text/html;charset=UTF-8");
			String order_no = req.getParameter("order_no");
			PrintWriter out = res.getWriter();
			OrderDetailService orderdetailSVC = new OrderDetailService();
			Food_ListService food_listSVC = new  Food_ListService();
			
			JSONArray jsar = new JSONArray(orderdetailSVC.getOneOrderDetail(order_no));
			
			for (int i = 0; i < jsar.length(); i++) {
			
				try {
					JSONObject jsob = jsar.getJSONObject(i);
					String food_no = jsob.getString("food_no");
					String food_name = food_listSVC.getOneFood_List(food_no).getFood_name();
					
//					/************************Base64取圖*************************/
//					final Base64.Encoder encoder = Base64.getEncoder();
//					byte[] food_img = food_listSVC.getOneFood_List(food_no).getFood_img();
//					final String encodedText = encoder.encodeToString(food_img);
//					System.out.println(encodedText);
//					jsob.put("food_img", encodedText);
//					/************************Base64*************************/
									
//					//使用Servlet取圖，存入food_no
//					jsob.put("food_img", food_no);
					
					jsob.put("food_name", food_name);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
//			System.out.println("List to JSON: " + jsar.toString());
			out.println(jsar.toString());
		}
		/************************取得訂餐詳細資料 by Sheng*************************/
		
		
		
		if ("insert".equals(action)) { // 來自addOrderMaster.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String order_no = req.getParameter("order_no");
				String food_no = req.getParameter("food_no");
				String food_scale = req.getParameter("food_scale");
				Integer quantity = new Integer(req.getParameter("quantity"));
				Integer food_price = new Integer(req.getParameter("food_price"));
				Integer total = new Integer(req.getParameter("total"));

				OrderDetailVO orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrder_no(order_no);
				orderDetailVO.setFood_no(food_no);
				orderDetailVO.setFood_scale(food_scale);
				orderDetailVO.setQuantity(quantity);
				orderDetailVO.setFood_price(food_price);
				orderDetailVO.setTotal(total);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderDetailVO", orderDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/orderdetail/addOrderDetail.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				OrderDetailService orderdSvc = new OrderDetailService();
				orderDetailVO = orderdSvc.addOrderDetail(order_no, food_no, food_scale, quantity, food_price, total);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-store-end/orderdetail/listAllOrderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllOrderMaster.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法新增資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/orderdetail/addOrderDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
//if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String order_no = req.getParameter("order_no");
//				String food_no = req.getParameter("food_no");
//				String food_scale = req.getParameter("food_scale");
//				Integer quantity = new Integer(req.getParameter("quantity"));
//				Integer food_price = new Integer(req.getParameter("food_price"));
//				Integer total = new Integer(req.getParameter("total"));
//
//				OrderDetailVO orderDetailVO = new OrderDetailVO();
//				orderDetailVO.setOrder_no(order_no);
//				orderDetailVO.setFood_no(food_no);
//				orderDetailVO.setFood_scale(food_scale);
//				orderDetailVO.setQuantity(quantity);
//				orderDetailVO.setFood_price(food_price);
//				orderDetailVO.setTotal(total);
//				
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("orderDetailVO", orderDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-store-end/orderdetail/update_orderDetail_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				OrderDetailService orderdSvc = new OrderDetailService();
//				orderDetailVO = orderdSvc.updateOrderDetail(order_no, food_no, food_scale, quantity, food_price, total);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("orderDetailVO", orderDetailVO); 
//				String url = "/front-store-end/orderdetail/listOneOrderDetail.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-store-end/orderdetail/update_orderDetail_input.jsp");
//				failureView.forward(req, res);
//			}
//		}

	}
}
