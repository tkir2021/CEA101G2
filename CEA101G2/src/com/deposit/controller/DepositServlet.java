package com.deposit.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.deposit.model.*;
import com.mem.model.Mem_DataService;
import com.mem.model.Mem_DataVO;

public class DepositServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("deposit_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入儲值序號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/select_pageDeposit.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String deposit_no = null;
				try {
					deposit_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("儲值序號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/select_pageDeposit.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				DepositService depositSvc = new DepositService();
				DepositVO depositVO = depositSvc.getOneDeposit(deposit_no);
				if (depositVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/select_pageDeposit.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("depositVO", depositVO); // 資料庫取出的DepositVO物件,存入req
				String url = "/front-customer-end/member/listOneDeposit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneDeposit.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/select_pageDeposit.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllDeposit.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String deposit_no = req.getParameter("deposit_no");
				
				/***************************2.開始查詢資料****************************************/
				DepositService depositSvc = new DepositService();
				DepositVO depositVO = depositSvc.getOneDeposit(deposit_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("depositVO", depositVO);         // 資料庫取出的DepositVO物件,存入req
				String url = "/front-customer-end/member/update_deposit_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Deposit_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/listAllDeposit.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_Deposit_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String deposit_no = req.getParameter("deposit_no").trim();
												
				String mem_no = null;
				try {
					mem_no = req.getParameter("mem_no").trim();
				} catch (NumberFormatException e) {
					mem_no = null;
					errorMsgs.add("會員資料請勿空白");
				}

				Integer charge = new Integer(req.getParameter("charge").trim());
				
				Timestamp charge_time= null;
//				 charge_time = new Timestamp(req.getParameter("charge_time").trim());

				DepositVO depositVO = new DepositVO();
				depositVO.setDeposit_no(deposit_no);
				depositVO.setMem_no(mem_no);
				depositVO.setCharge(charge);
				depositVO.setCharge_time(charge_time);
	

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("depositVO", depositVO); // 含有輸入格式錯誤的DepositVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/update_deposit_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				DepositService depositSvc = new DepositService();
				depositVO = depositSvc.updateDeposit(deposit_no, mem_no, charge, charge_time);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("depositVO", depositVO); // 資料庫update成功後,正確的的DepositVO物件,存入req
				String url = "/front-customer-end/member/listOneDeposit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneDeposit.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/update_deposit_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addDeposit.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			System.out.println(req.getParameter("mem_no"));
			String memNo=req.getParameter("mem_no");
			Mem_DataService mem_dataSvc = new Mem_DataService();
			Mem_DataVO mem_dataVO = mem_dataSvc.getOneMem(memNo);
			Integer cha = new Integer (req.getParameter("charge"));
			Integer a= mem_dataVO.getDeposit();
			Integer deposit = a + cha;
			System.out.println(deposit);
//			mem_dataVO.setMem_no(memNo);
//			mem_dataVO.setDeposit(deposit);
			
			mem_dataVO = mem_dataSvc.updateDeposit(memNo,deposit);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String deposit_no = req.getParameter("deposit_no").trim();
				
				String mem_no = null;
				try {
					mem_no = req.getParameter("mem_no").trim();
				} catch (NumberFormatException e) {
					mem_no = null;
					errorMsgs.add("會員資料請勿空白");
				}

				Integer charge = new Integer(req.getParameter("charge").trim());
				
				Timestamp charge_time= null;
//				 charge_time = new Timestamp(req.getParameter("charge_time").trim());

				DepositVO depositVO = new DepositVO();
//				depositVO.setDeposit_no(deposit_no);
				depositVO.setMem_no(mem_no);
				depositVO.setCharge(charge);
				depositVO.setCharge_time(charge_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("depositVO", depositVO); // 含有輸入格式錯誤的DepositVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/addDeposit.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				DepositService DepositSvc = new DepositService();
				depositVO = DepositSvc.addDeposit( mem_no, charge, charge_time);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/front-customer-end/member/listAllDeposit.jsp";
				String url = "/front-customer-end/member/memDeposit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllDeposit.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/addDeposit.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllDeposit.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String deposit_no = req.getParameter("deposit_no");
				
				/***************************2.開始刪除資料***************************************/
				DepositService deposit_noSvc = new DepositService();
				deposit_noSvc.deleteDeposit(deposit_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-customer-end/member/listAllDeposit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/listAllDeposit.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
