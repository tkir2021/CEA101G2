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

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page_bill.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String bill_no = req.getParameter("bill_no");
//				String store_no = req.getParameter("store_no");
//				Integer bill_price = new Integer(req.getParameter("bill_price"));
//				java.sql.Timestamp bill_date = java.sql.Timestamp.valueOf(req.getParameter("bill_date").trim());
//				String bill_period = req.getParameter("bill_period");
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/bill/select_page_bill.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				BillService billSvc = new BillService();
				BillVO billVO = billSvc.getOneBill(bill_no);
				if (billVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/bill/select_page_bill.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("billVO", billVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-store-end/bill/listOneBill.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
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
				/***************************1.�����ШD�Ѽ�****************************************/
				String bill_no = req.getParameter("bill_no");
				
				/***************************2.�}�l�d�߸��****************************************/
				BillService billSvc = new BillService();
				BillVO billVO = billSvc.getOneBill(bill_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("billVO", billVO);        
				String url = "/front-store-end/bill/update_bill_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/bill/listAllBill.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
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
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
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
					req.setAttribute("billVO", billVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/bill/update_bill_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				BillService billSvc = new BillService();
				billVO = billSvc.updateBill(store_no, store_no, bill_price,
						bill_date, bill_period);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("billVO", billVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-store-end/bill/listOneBill.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/bill/update_Bill_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // �Ӧ�addBill.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
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
					req.setAttribute("billVO", billVO); // �t����J�榡���~��billVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-store-end/bill/addBill.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				
				BillService billSvc = new BillService();
				billVO = billSvc.addBill(store_no, bill_price,
						bill_date, bill_period);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-store-end/bill/listAllBill.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllOrderMaster.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k�s�W���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/bill/addBill.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // �Ӧ�listAllBill.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String bill_no = req.getParameter("bill_no");
				/***************************2.�}�l�R�����***************************************/
				BillService billSvc = new BillService();
				billSvc.deleteBill(bill_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-store-end/bill/listAllBill.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-store-end/bill/listAllBill.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}