package com.memGrade.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.memGrade.model.*;

public class MemGradeServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

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
				String str = req.getParameter("grade_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入等級編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/selectPageMemGrade.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer grade_no = null;
				try {
					grade_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("等級編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/selectPageMemGrade.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemGradeService memgradeSvc = new MemGradeService();
				Mem_GradeVO mem_gradeVO = memgradeSvc.getOneGrade(grade_no);
				if (mem_gradeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/selectPageMemGrade.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mem_gradeVO", mem_gradeVO); // 資料庫取出的GradeVO物件,存入req
				String url = "/front-customer-end/member/listOneMemGrade.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGrade.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/selectPageMemGrade.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllGrade.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer grade_no = new Integer(req.getParameter("grade_no"));
				
				/***************************2.開始查詢資料****************************************/
				MemGradeService mem_gradeSvc = new MemGradeService();
				Mem_GradeVO mem_gradeVO = mem_gradeSvc.getOneGrade(grade_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("mem_gradeVO", mem_gradeVO);         // 資料庫取出的GradeVO物件,存入req
				String url = "/front-customer-end/member/update_memGrade_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Grade_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/listAllMemGrade.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_Grade_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer grade_no = new Integer(req.getParameter("grade_no").trim());
				
				

				Integer consume_level = null;
				try {
					consume_level = new Integer(req.getParameter("consume_level").trim());
				} catch (NumberFormatException e) {
					consume_level = 0;
					errorMsgs.add("消費金額門檻請填數字.");
				}
				
				Double discount = null;
				try {
					discount = new Double(req.getParameter("discount").trim());
				} catch (NumberFormatException e) {
					discount = 0.0;
					errorMsgs.add("折扣%數請填數字.");
				}


				Mem_GradeVO mem_gradeVO = new Mem_GradeVO();
				mem_gradeVO.setGrade_no(grade_no);
				mem_gradeVO.setConsume_level(consume_level);
				mem_gradeVO.setDiscount(discount);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mem_gradeVO", mem_gradeVO); // 含有輸入格式錯誤的GradeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/update_memGrade_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemGradeService mem_gradeSvc = new MemGradeService();
				mem_gradeVO = mem_gradeSvc.updateGrade(grade_no, consume_level, discount);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mem_gradeVO", mem_gradeVO); // 資料庫update成功後,正確的的GradeVO物件,存入req
				String url = "/front-customer-end/member/listOneMemGrade.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneGrade.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/update_memGrade_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addGrade.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
                Integer grade_no = new Integer(req.getParameter("grade_no").trim());
				
				

				Integer consume_level = null;
				try {
					consume_level = new Integer(req.getParameter("consume_level").trim());
				} catch (NumberFormatException e) {
					consume_level = 0;
					errorMsgs.add("消費金額門檻請填數字.");
				}
				
				Double discount = null;
				try {
					discount = new Double(req.getParameter("discount").trim());
				} catch (NumberFormatException e) {
					discount = 0.0;
					errorMsgs.add("折扣%數請填數字.");
				}

				Mem_GradeVO mem_gradeVO = new Mem_GradeVO();
				mem_gradeVO.setGrade_no(grade_no);
				mem_gradeVO.setConsume_level(consume_level);
				mem_gradeVO.setDiscount(discount);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mem_gradeVO", mem_gradeVO); // 含有輸入格式錯誤的GradeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-customer-end/member/addMemGrade.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemGradeService mem_gradeSvc = new MemGradeService();
				mem_gradeVO = mem_gradeSvc.addGrade(grade_no,consume_level, discount);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-customer-end/member/listAllMemGrade.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllGrade.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/addMemGrade.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllGrade.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer grade_no = new Integer(req.getParameter("grade_no"));
				
				/***************************2.開始刪除資料***************************************/
				MemGradeService mem_gradeSvc = new MemGradeService();
				mem_gradeSvc.deleteGrade(grade_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-customer-end/member/listAllMemGrade.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-customer-end/member/listAllMemGrade.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
