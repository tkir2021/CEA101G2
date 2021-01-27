package com.ordermaster.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ordermaster.model.OrderMasterService;
import com.ordermaster.model.OrderMasterVO;
import com.store.model.Store_MemService;

public class OrderMasterServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page_om.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("order_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂餐編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/ordermaster/select_page_om.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				String order_no = req.getParameter("order_no");
				/*************************** 2.開始查詢資料 *****************************************/
				OrderMasterService ordermSvc = new OrderMasterService();
				OrderMasterVO orderMasterVO = ordermSvc.getOneOrderMaster(order_no);
				if (orderMasterVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/ordermaster/select_page_om.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("orderMasterVO", orderMasterVO);
				String url = "/front-store-end/ordermaster/listOneOrderMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/ordermaster/select_page_om.jsp");
				failureView.forward(req, res);
			}
		}

		/************************ 取得訂餐資料 by Sheng *************************/

		if ("orderMasterDisplay".equals(action)) {
			res.setContentType("text/html;charset=UTF-8");
			String memno = req.getParameter("memno");
			PrintWriter out = res.getWriter();
			OrderMasterService ordermasterSVC = new OrderMasterService();
			Store_MemService smSvc = new Store_MemService();

			JSONArray jsar = new JSONArray(ordermasterSVC.findByNumber(memno));

			for (int i = 0; i < jsar.length(); i++) {

				try {
					JSONObject jsob = jsar.getJSONObject(i);
					String storeno = jsob.getString("store_no");
					String storename = smSvc.getOneStore_Mem(storeno).getStore_name();
					jsob.put("storename", storename);
					jsob.put("information", "");
					jsob.put("give_star", jsob.getString("take_status") + "-" + Integer.toString(jsob.getInt("give_star")));
					
					
					
					//以是否取餐來判斷可否評分
//					if(take_status.equals("0")) {
//						System.out.println(take_status + "－" + Integer.toString(jsob.getInt("give_star")));
//					}
//					else {
//						System.out.println(take_status + "－" + Integer.toString(jsob.getInt("give_star")));
//					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

//			System.out.println("List to JSON: " + jsar.toString());
			out.println(jsar.toString());
		}
		/************************ 取得訂餐資料 by Sheng *************************/

		/************************ 對訂餐訂單的店家評分 by Sheng *************************/

		if ("Score".equals(action)) {
			String order_no = req.getParameter("order_no");
			double om_givestar = new Double(req.getParameter("om_givestar"));

			OrderMasterService ordermasterSVC = new OrderMasterService();
			ordermasterSVC.upGivestar(order_no, om_givestar);
			PrintWriter out = res.getWriter();
			out.print("OK");
			out.flush();
			out.close();
		}
		/************************ 對訂餐訂單的店家評分 by Sheng *************************/

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String order_no = req.getParameter("order_no");

				/*************************** 2.開始查詢資料 ****************************************/
				OrderMasterService ordermSvc = new OrderMasterService();
				OrderMasterVO orderMasterVO = ordermSvc.getOneOrderMaster(order_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("orderMasterVO", orderMasterVO);
				String url = "/front-store-end/ordermaster/update_orderMaster_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/ordermaster/listAllOrderMaster.jsp");
				failureView.forward(req, res);
			}

		}
		
		/***********************改變訂餐訂單的取餐狀態 by Bella *************************/ 
		  if("updateTake_status".equals(action)) {
		   try {
		    String order_no = req.getParameter("order_no");
		    String take_status = req.getParameter("take_status");
		    //System.out.println(take_status);
		    //System.out.println(order_no);
		    OrderMasterService omSvc = new OrderMasterService();
		    OrderMasterVO omVO = omSvc.upGetFood(order_no, take_status);
		    
		    req.setAttribute("omVO", omVO);
		    RequestDispatcher successView =req.getRequestDispatcher("/front-store-end/store/store_detail.jsp");
		    successView.forward(req, res);
		   }catch(Exception e) {
		    System.out.println(e.getMessage());
		    RequestDispatcher failureView = req.getRequestDispatcher("/front-store-end/store/store_detail.jsp");
		    failureView.forward(req, res);
		   }
		  }

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String order_no = req.getParameter("order_no");
				String mem_no = req.getParameter("mem_no");
				String store_no = req.getParameter("store_no");
				String sale_no = req.getParameter("sale_no");
				java.sql.Date order_date = java.sql.Date.valueOf(req.getParameter("order_date").trim());
				Integer pay_type = new Integer(req.getParameter("pay_type").trim());
				Integer order_total = new Integer(req.getParameter("order_total").trim());
				Float sale_percent = new Float(req.getParameter("sale_percent").trim());
				Float discount = new Float(req.getParameter("discount").trim());
				String order_status = req.getParameter("order_status");
				String take_status = req.getParameter("take_status");
				Float give_star = new Float(req.getParameter("give_star").trim());

				OrderMasterVO orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrder_no(order_no);
				orderMasterVO.setMem_no(mem_no);
				orderMasterVO.setStore_no(store_no);
				orderMasterVO.setSale_no(sale_no);
				orderMasterVO.setOrder_date(order_date);
				orderMasterVO.setPay_type(pay_type);
				orderMasterVO.setOrder_total(order_total);
				orderMasterVO.setSale_percent(sale_percent);
				orderMasterVO.setDiscount(discount);
				orderMasterVO.setOrder_status(order_status);
				orderMasterVO.setTake_status(take_status);
				orderMasterVO.setGive_star(give_star);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderMasterVO", orderMasterVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/ordermaster/update_orderMaster_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				OrderMasterService ordermSvc = new OrderMasterService();
				orderMasterVO = ordermSvc.updateOrderMaster(order_no, mem_no, store_no, sale_no, order_date, pay_type,
						order_total, sale_percent, discount, order_status, take_status, give_star);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("orderMasterVO", orderMasterVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-store-end/ordermaster/listOneOrderMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/ordermaster/update_orderMaster_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addOrderMaster.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String mem_no = req.getParameter("mem_no");
				String store_no = req.getParameter("store_no");
				String sale_no = req.getParameter("sale_no");
				java.sql.Date order_date = java.sql.Date.valueOf(req.getParameter("order_date").trim());
				Integer pay_type = new Integer(req.getParameter("pay_type"));
				Integer order_total = new Integer(req.getParameter("order_total").trim());
				Float sale_percent = new Float(req.getParameter("sale_percent").trim());
				Float discount = new Float(req.getParameter("discount").trim());

				String order_status = req.getParameter("order_status");
				if (order_status == null) {
					errorMsgs.add("請確認訂單狀況");
				}

				String take_status = req.getParameter("take_status");
				if (order_status == null) {
					errorMsgs.add("請確認取餐狀況");
				}
				Float give_star = new Float(req.getParameter("give_star").trim());

				OrderMasterVO orderMasterVO = new OrderMasterVO();
				orderMasterVO.setMem_no(mem_no);
				orderMasterVO.setStore_no(store_no);
				orderMasterVO.setSale_no(sale_no);
				orderMasterVO.setOrder_date(order_date);
				orderMasterVO.setPay_type(pay_type);
				orderMasterVO.setOrder_total(order_total);
				orderMasterVO.setSale_percent(sale_percent);
				orderMasterVO.setDiscount(discount);
				orderMasterVO.setOrder_status(order_status);
				orderMasterVO.setTake_status(take_status);
				orderMasterVO.setGive_star(give_star);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderMasterVO", orderMasterVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/ordermaster/addOrderMaster.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				OrderMasterService ordermSvc = new OrderMasterService();
				orderMasterVO = ordermSvc.addOrderMaster(mem_no, store_no, sale_no, order_date, pay_type, order_total,
						sale_percent, discount, order_status, take_status, give_star);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-store-end/ordermaster/listAllOrderMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllOrderMaster.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法新增資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/ordermaster/addOrderMaster.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllOrderMaster.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String order_no = req.getParameter("order_no");

				/*************************** 2.開始刪除資料 ***************************************/
				OrderMasterService ordermSvc = new OrderMasterService();
				ordermSvc.deleteOrderNo(order_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-store-end/ordermaster/listAllOrderMaster.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/ordermaster/listAllOrderMaster.jsp");
				failureView.forward(req, res);
			}
		}

		/***************************
		 * 取訂位資料20200104 byBELLA
		 ********************************/
		if ("getALL".equals(action)) {
			JSONObject jsobject = new JSONObject();
			HttpSession session = req.getSession();
			String storeno = (String) session.getAttribute("storeno");
			OrderMasterService orderSvc = new OrderMasterService();
			List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
			try {
				list = orderSvc.getAll().stream().filter(o -> o.getStore_no().equals(storeno))
						.collect(Collectors.toList());

				jsobject.put("allOrder", list);
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(jsobject.toString());
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}

		}

	}

}
