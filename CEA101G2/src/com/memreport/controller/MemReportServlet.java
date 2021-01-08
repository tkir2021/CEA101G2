package com.memreport.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.memreport.model.MemReportService;
import com.memreport.model.MemReportVO;

public class MemReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemReportServlet() {
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
				String m_report_no = req.getParameter("m_report_no");
				String group_no = req.getParameter("group_no");
				String reported_mem = req.getParameter("reported_mem");
				String report_mem = req.getParameter("report_mem");
				String report_reason = "輸入檢舉事由";
				if(report_reason == null || report_reason.trim().length()==0) {
					errorMsgs.put("reason", "檢舉事由不得為空。");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem_report/addMemReport.jsp");
					failureView.forward(req, res);
					return;
				}

				// 新增資料
				MemReportService mrSvc = new MemReportService();
				mrSvc.addMR(group_no, reported_mem, report_mem, report_reason);
				// 完成新增轉交
				String url ="/back-end/mem_report/listAllMemReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem_report/addMemReport.jsp");
				failureView.forward(req, res);
			}
		}
		
		

	// findPK
		if ("getOne_display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("m_report_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page_report.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String m_report_no = null;
				try {
					m_report_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page_report.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemReportService mrSvc = new MemReportService();
				MemReportVO mrVO = mrSvc.findByMRno(m_report_no);
				if (mrVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page_report.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("mrVO", mrVO); // 資料庫取出的物件,存入req
				String url = "/back-end/mem_report/listOneMemReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page_report.jsp");
				failureView.forward(req, res);
			}

		}
		// updateMR
		// step1.從listall.jsp中點選其中一筆
		if ("getOne_update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 取得listall.jsp網頁中name為m_report_no的參數值
				String mrno = new String(req.getParameter("m_report_no"));
				// 開始查詢
				MemReportService mrSvc = new MemReportService();
				MemReportVO mrVO = mrSvc.findByMRno(mrno);
				// 查詢成功，轉交到update_mr.jsp
				req.setAttribute("mrVO", mrVO);
				String url = "/back-end/mem_report/updateMemReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem_report/listAllMemReport.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String m_report_no = new String(req.getParameter("m_report_no"));
//				String group_no = req.getParameter("group_no");
//				String reported_mem = req.getParameter("reported_mem");
//				String report_mem = req.getParameter("report_mem");
				String emp_no = req.getParameter("emp_no");
//				String report_reason = req.getParameter("report_reason");
				Integer commit_status = 0;
				try {
					commit_status = new Integer(req.getParameter("commit_status").trim());
				} catch (NumberFormatException e) {
					if(commit_status <= 0 || commit_status >2) {
						errorMsgs.add("請選擇是否成立。");
					}
				}
				Integer report_status = 0;
				if (commit_status == 1 || commit_status == 2) {
					report_status = 1;
				}
				
				MemReportVO mrVO = new MemReportVO();
				mrVO.setCommit_status(commit_status);
				mrVO.setReport_status(report_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mrVO", mrVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem_report/updateMemReport.jsp");
					failureView.forward(req, res);
					return;
				}

				
				MemReportService mrSvc = new MemReportService();
				mrVO = mrSvc.updateMR(report_status, commit_status, m_report_no);
				
				req.setAttribute("MemReportVO", mrVO);
				String url = "/back-end/mem_report/listAllMemReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem_report/updateMemReport.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
