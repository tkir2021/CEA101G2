package com.storereport.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.memreport.model.MemReportService;
import com.storereport.model.StoreReportService;
import com.storereport.model.StoreReportVO;

public class StoreReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StoreReportServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");// 取得網頁中name為action的傳遞值

		//insert
				if ("insert".equals(action)) {
					Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						String s_report_no = req.getParameter("s_report_no");
						String booking_no = req.getParameter("booking_no");
						String order_no = req.getParameter("order_no");
						String reported_store = req.getParameter("reported_store");
						String report_mem = req.getParameter("report_mem");
						String report_reason = "輸入檢舉事由";
						if(report_reason == null || report_reason.trim().length()==0) {
							errorMsgs.put("reason", "檢舉事由不得為空。");
						}
						
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store_report/addStoreReport.jsp");
							failureView.forward(req, res);
							return;
						}

						// 新增資料
						StoreReportService srSvc = new StoreReportService();
						srSvc.addSR(order_no, booking_no, reported_store, report_mem, report_reason);
						// 完成新增轉交
						String url ="/back-end/store_report/listAllStoreReport.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);

					} catch (Exception e) {
						errorMsgs.put("Exception", e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store_report/addStoreReport.jsp");
						failureView.forward(req, res);
					}
				}
		
		// findPK
		if ("getOne_display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("s_report_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉號碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page_report.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String s_report_no = null;
				try {
					s_report_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page_report.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				StoreReportService srSvc = new StoreReportService();
				StoreReportVO srVO = srSvc.findByS_report_no(s_report_no);
				if (srVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page_report.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("srVO", srVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/store_report/listOneStoreReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page_report.jsp");
				failureView.forward(req, res);
			}

		}
		// updateSR
		// step1.從listall.jsp中點選其中一筆
		if ("getOne_update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 取得listall.jsp網頁中name為s_report_no的參數值
				String srno = new String(req.getParameter("s_report_no"));
				// 開始查詢
				StoreReportService srSvc = new StoreReportService();
				StoreReportVO srVO = srSvc.findByS_report_no(srno);
				// 查詢成功，轉交到update_mr.jsp
				req.setAttribute("srVO", srVO);
				String url = "/back-end/store_report/updateStoreReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/store_report/listAllStoreReport.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String s_report_no = new String(req.getParameter("s_report_no"));
//				String group_no = req.getParameter("group_no");
//				String reported_mem = req.getParameter("reported_mem");
//				String report_mem = req.getParameter("report_mem");
//				String emp_no = req.getParameter("emp_no");
//				String report_reason = req.getParameter("report_reason");
				Integer commit_status = 0;
				try {
					commit_status = new Integer(req.getParameter("commit_status").trim());
				} catch (NumberFormatException e) {
					commit_status = 0;
					errorMsgs.add("請選擇是否成立。");
				}
				Integer report_status = 0;
				if (commit_status == 1 || commit_status == 2) {
					report_status = 1;
				}

				Integer report_grade = 0;
				try {
					report_grade = new Integer(req.getParameter("report_grade").trim());
				} catch (NumberFormatException e) {
					report_grade = 0;
					errorMsgs.add("請選擇嚴重性評分分數。");
				}

				StoreReportVO srVO = new StoreReportVO();
				srVO.setS_report_no(s_report_no);
				srVO.setCommit_status(commit_status);
				srVO.setReport_status(report_status);
				srVO.setReport_grade(report_grade);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("srVO", srVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store_report/updateStoreReport.jsp");
					failureView.forward(req, res);
					return;
				}

				StoreReportService srSvc = new StoreReportService();
				srVO = srSvc.updateSR(s_report_no, report_grade, report_status, commit_status);

				req.setAttribute("StoreReportVO", srVO);
				String url = "/back-end/store_report/listAllStoreReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/store_report/updateStoreReport.jsp");
				failureView.forward(req, res);
			}
		}

	}

}