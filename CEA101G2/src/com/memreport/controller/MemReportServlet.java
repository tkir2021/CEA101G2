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
		String action = req.getParameter("action");// ���o������name��action���ǻ���

	//insert
		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String m_report_no = req.getParameter("m_report_no");
				String group_no = req.getParameter("group_no");
				String reported_mem = req.getParameter("reported_mem");
				String report_mem = req.getParameter("report_mem");
				String report_reason = "��J���|�ƥ�";
				if(report_reason == null || report_reason.trim().length()==0) {
					errorMsgs.put("reason", "���|�ƥѤ��o���šC");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem_report/addMemReport.jsp");
					failureView.forward(req, res);
					return;
				}

				// �s�W���
				MemReportService mrSvc = new MemReportService();
				mrSvc.addMR(group_no, reported_mem, report_mem, report_reason);
				// �����s�W���
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
		if ("getOne_display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("m_report_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page_report.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String m_report_no = null;
				try {
					m_report_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page_report.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				MemReportService mrSvc = new MemReportService();
				MemReportVO mrVO = mrSvc.findByMRno(m_report_no);
				if (mrVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page_report.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("mrVO", mrVO); // ��Ʈw���X������,�s�Jreq
				String url = "/back-end/mem_report/listOneMemReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page_report.jsp");
				failureView.forward(req, res);
			}

		}
		// updateMR
		// step1.�qlistall.jsp���I��䤤�@��
		if ("getOne_update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// ���olistall.jsp������name��m_report_no���Ѽƭ�
				String mrno = new String(req.getParameter("m_report_no"));
				// �}�l�d��
				MemReportService mrSvc = new MemReportService();
				MemReportVO mrVO = mrSvc.findByMRno(mrno);
				// �d�ߦ��\�A����update_mr.jsp
				req.setAttribute("mrVO", mrVO);
				String url = "/back-end/mem_report/updateMemReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
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
						errorMsgs.add("�п�ܬO�_���ߡC");
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
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/mem_report/updateMemReport.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
