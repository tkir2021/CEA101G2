package com.bill.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bill.model.BillService;
import com.bill.model.BillVO;
import com.ordermaster.model.OrderMasterService;
import com.ordermaster.model.OrderMasterVO;

public class BillServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page_bill.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String bill_no = req.getParameter("bill_no");
//				String store_no = req.getParameter("store_no");
//				Integer bill_price = new Integer(req.getParameter("bill_price"));
//				java.sql.Timestamp bill_date = java.sql.Timestamp.valueOf(req.getParameter("bill_date").trim());
//				String bill_period = req.getParameter("bill_period");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/bill/select_page_bill.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BillService billSvc = new BillService();
				BillVO billVO = billSvc.getOneBill(bill_no);
				if (billVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/bill/select_page_bill.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("billVO", billVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-store-end/bill/listOneBill.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/bill/select_page_bill.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String bill_no = req.getParameter("bill_no");
				
				/***************************2.開始查詢資料****************************************/
				BillService billSvc = new BillService();
				BillVO billVO = billSvc.getOneBill(bill_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("billVO", billVO);        
				String url = "/front-store-end/bill/update_bill_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/bill/listAllBill.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			System.out.println(req.getParameter("bill_no"));
			System.out.println(req.getParameter("store_no"));
			System.out.println(req.getParameter("bill_price"));
			System.out.println(req.getParameter("bill_date"));
			System.out.println(req.getParameter("bill_period"));
			
			
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String bill_no = req.getParameter("bill_no");
				String store_no = req.getParameter("store_no");
				Integer bill_price = new Integer(req.getParameter("bill_price"));
				java.sql.Timestamp bill_date = java.sql.Timestamp.valueOf(req.getParameter("bill_date").trim());
				String bill_period = req.getParameter("bill_period");
				
				BillVO billVO = new BillVO();
				billVO.setBill_no(bill_no);
				billVO.setStore_no(store_no);
				billVO.setBill_price(bill_price);
				billVO.setBill_date(bill_date);
				billVO.setBill_period(bill_period);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("billVO", billVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/bill/update_bill_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				BillService billSvc = new BillService();
				billVO = billSvc.updateBill(store_no, store_no, bill_price,
						bill_date, bill_period);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("billVO", billVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-store-end/bill/listOneBill.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/bill/update_Bill_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addBill.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String bill_no = req.getParameter("bill_no");
				String store_no = req.getParameter("store_no");
				Integer bill_price = new Integer(req.getParameter("bill_price"));
				java.sql.Timestamp bill_date = java.sql.Timestamp.valueOf(req.getParameter("bill_date").trim());
				String bill_period = req.getParameter("bill_period");
				
				BillVO billVO = new BillVO();
//				billVO.setBill_no(bill_no);
				billVO.setStore_no(store_no);
				billVO.setBill_price(bill_price);
				billVO.setBill_date(bill_date);
				billVO.setBill_period(bill_period);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("billVO", billVO); // 含有輸入格式錯誤的billVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/bill/addBill.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				BillService billSvc = new BillService();
				billVO = billSvc.addBill(store_no, bill_price,
						bill_date, bill_period);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-store-end/bill/listAllBill.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllOrderMaster.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法新增資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/bill/addBill.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllBill.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數***************************************/
				String bill_no = req.getParameter("bill_no");
				/***************************2.開始刪除資料***************************************/
				BillService billSvc = new BillService();
				billSvc.deleteBill(bill_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-store-end/bill/listAllBill.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/bill/listAllBill.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}