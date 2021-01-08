package com.emp.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.memreport.model.MemReportService;
import com.memreport.model.MemReportVO;

public class EmpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// addEmp
		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String emp_name = req.getParameter("emp_name");
				// 驗證名字的規則(正規化)
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.put("emp_name", "員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(enameReg)) {
					errorMsgs.put("emp_name", "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				// 到職日
				java.sql.Date emp_date = null;
				try {
					emp_date = java.sql.Date.valueOf(req.getParameter("emp_date").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("emp_date", "請輸入到職日");
				}
				// mailReg驗證mail格式
				String emp_mail = req.getParameter("emp_mail");
				String mailReg = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
				if (emp_mail == null || emp_mail.trim().length() == 0) {
					errorMsgs.put("emp_mail", "員工E-MAIL：請勿空白");
				} else if (!emp_mail.trim().matches(mailReg)) {
					errorMsgs.put("emp_mail", "請輸入正確之mail格式。");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/back_emplist.jsp");
					failureView.forward(req, res);
					return;
				}



				// 新增資料
				EmpService empSvc = new EmpService();
				empSvc.addEmp(emp_name, emp_date, emp_mail);
				// 完成新增轉交
				String url ="/back-end/emp/back_emplist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/back_emplist.jsp");
				failureView.forward(req, res);
			}
		}
		// findPK
		if ("getOne_For_Display".equals(action))

		{ // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("emp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String emp_no = null;
				try {
					emp_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.findByPK(emp_no);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		// update Step1
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 取得listall.jsp網頁中name為m_report_no的參數值
				String emp_no = req.getParameter("emp_no");
				// 開始查詢
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.findByPK(emp_no);
				// 查詢成功，轉交到update_emp.jsp
				req.setAttribute("empVO", empVO);
				String url = "/back-end/emp/update_emp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String emp_no = new String(req.getParameter("emp_no"));
				String emp_name = new String(req.getParameter("emp_name"));

				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(enameReg)) {
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String emp_mail = new String(req.getParameter("emp_mail"));
				String mailReg = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
				if (emp_mail == null || emp_mail.trim().length() == 0) {
					errorMsgs.add("員工E-MAIL：請勿空白");
				} else if (!emp_mail.trim().matches(mailReg)) {
					errorMsgs.add("請輸入正確之mail格式。");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/back_emplist.jsp");
					failureView.forward(req, res);
					return;
				}
				
				java.sql.Date emp_date;
				try {
					emp_date = java.sql.Date.valueOf(req.getParameter("emp_date"));
				} catch (IllegalArgumentException e) {
					emp_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer emp_status = 1;
				if (emp_status < 0 || emp_status > 1) {
					errorMsgs.add("請選擇該員工的在職情況");
				}

				EmpVO empVO = new EmpVO();
				empVO.setEmp_no(emp_no);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_mail(emp_mail);
				empVO.setEmp_date(emp_date);
				empVO.setEmp_status(emp_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(emp_no, emp_name, emp_mail, emp_date, emp_status);// emp_img,

				req.setAttribute("empVO", empVO);
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

			catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp.jsp");
				failureView.forward(req, res);
			}

		}
	}
}
