package com.group.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group.model.Group_Service;
import com.group.model.Group_VO;

public class Group_Servlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String group_no = req.getParameter("group_no"); // 由前端 input 所取得的輸出工號
				if (group_no == null || (group_no.trim()).length() == 0) {
					errorMsgs.add("請輸入揪團編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				

				if (!group_no.matches("^[G]{1}[P]{1}\\d{8}$")) {
					errorMsgs.add("揪團編號格式不正確");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				Group_Service group_Svc = new Group_Service();
				Group_VO group_VO = group_Svc.getOneGroup_(group_no); // 將取得資料放入group_VO
				if (group_VO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("group_VO", group_VO); // 資料庫取出的group_VO物件,存入req
				String url = "/front-customer-end/group/listOneGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllGroup.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String group_no = req.getParameter("group_no");

				/*************************** 2.開始查詢資料 ****************************************/
				Group_Service group_Svc = new Group_Service();
				Group_VO group_VO = group_Svc.getOneGroup_(group_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("group_VO", group_VO); // 資料庫取出的group_VO物件,存入req
				String url = "/front-customer-end/group/update_group_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group/listAllGroup.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_group_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String group_no = req.getParameter("group_no");			
				String mem_no = req.getParameter("mem_no");
				String store_no = req.getParameter("store_no");
				String booking_no = req.getParameter("booking_no");
				Integer gp_kind = new Integer(req.getParameter("gp_kind"));
				java.sql.Date start_date = java.sql.Date.valueOf(req.getParameter("start_date"));
				Integer gp_status = new Integer(req.getParameter("gp_status"));

				Integer mem_least = null;
				try {
					mem_least = new Integer(req.getParameter("mem_least"));
				} catch (Exception e) {
					mem_least = 1;
					errorMsgs.add("參團人數(下限)格式不正確");
				}

				Integer mem_limit = null;
				try {
					mem_limit = new Integer(req.getParameter("mem_limit"));
				} catch (Exception e) {
					mem_limit = 1;
					errorMsgs.add("參團人數(上限)格式不正確");
				}
				
				java.sql.Date dining_date = null;
				try {
					dining_date = java.sql.Date.valueOf(req.getParameter("dining_date"));
				} catch (IllegalArgumentException e) {
					dining_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期！");
				}

				java.sql.Date end_date = null;
				try {
					end_date = java.sql.Date.valueOf(req.getParameter("end_date"));
				} catch (IllegalArgumentException e) {
					end_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期！");
				}

				String gp_info = req.getParameter("gp_info");
				if (gp_info == null || gp_info.length() == 0) {
					gp_info = "一起去吃吧！！";
					errorMsgs.add("揪團簡介：請勿空白");
				}

				Group_VO group_VO = new Group_VO();
				group_VO.setGroup_no(group_no);
				group_VO.setMem_no(mem_no);
				group_VO.setStore_no(store_no);
				group_VO.setBooking_no(booking_no);
				group_VO.setGp_kind(gp_kind);
				group_VO.setMem_least(mem_least);
				group_VO.setMem_limit(mem_limit);
				group_VO.setDining_date(dining_date);
				group_VO.setStart_date(start_date);
				group_VO.setEnd_date(end_date);
				group_VO.setGp_info(gp_info);
				group_VO.setGp_status(gp_status);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_VO", group_VO); // 含有輸入格式錯誤的group_VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group/update_group_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/

				Group_Service group_Svc = new Group_Service();
				group_VO = group_Svc.updateGroup_(group_no, mem_no, store_no, booking_no, gp_kind, mem_least, mem_limit,
						dining_date, start_date, end_date, gp_info, gp_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("group_VO", group_VO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-customer-end/group/listOneGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneGroup.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group/update_group_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addGroup.jsp的請求
			
//			System.out.println(req.getParameter("group_no"));
//			System.out.println(req.getParameter("mem_no"));
//			System.out.println(req.getParameter("store_no"));
//			System.out.println(req.getParameter("booking_no"));
//			System.out.println(req.getParameter("gp_kind"));
//			System.out.println(req.getParameter("mem_least"));
//			System.out.println(req.getParameter("mem_limit"));
//			System.out.println(req.getParameter("dining_date"));
//			System.out.println(req.getParameter("start_date"));
//			System.out.println(req.getParameter("end_date"));
//			System.out.println(req.getParameter("gp_info"));
//			System.out.println(req.getParameter("gp_status"));

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String mem_no = req.getParameter("mem_no");
				if (!mem_no.matches("^[M]{1}[M]{1}\\d{8}$")) {
					errorMsgs.add("會員編號格式不正確");
				}

				String store_no = req.getParameter("store_no");
				if (!store_no.matches("^[S]{1}[M]{1}\\d{8}$")) {
					errorMsgs.add("店家編號格式不正確");
				}

				String booking_no = req.getParameter("booking_no");
				if (!booking_no.matches("^[B]{1}[O]{1}\\d{8}$")) {
					errorMsgs.add("訂位編號格式不正確");
				}

				Integer gp_kind = null;
				try {
					gp_kind = new Integer(req.getParameter("gp_kind").trim());
					if (gp_kind != 0 && gp_kind != 1) {
						errorMsgs.add("揪團分類格式不正確");
					}
				} catch (Exception e) {
					errorMsgs.add("揪團分類格式不正確");
				}

				Integer mem_least = null;
				try {
					mem_least = new Integer(req.getParameter("mem_least"));
				} catch (Exception e) {
					mem_least = 1;
					errorMsgs.add("參團人數(下限)格式不正確");
				}

				Integer mem_limit = null;
				try {
					mem_limit = new Integer(req.getParameter("mem_limit"));
				} catch (Exception e) {
					mem_limit = 1;
					errorMsgs.add("參團人數(上限)格式不正確");
				}

				java.sql.Date dining_date = null;
				try {
					dining_date = java.sql.Date.valueOf(req.getParameter("dining_date"));
				} catch (IllegalArgumentException e) {
					dining_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入用餐日期！");
				}

				java.sql.Date start_date = null;
				try {
					start_date = java.sql.Date.valueOf(req.getParameter("start_date"));
				} catch (IllegalArgumentException e) {
					dining_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開團日期！");
				}

				java.sql.Date end_date = null;
				try {
					end_date = java.sql.Date.valueOf(req.getParameter("end_date"));
				} catch (IllegalArgumentException e) {
					dining_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入截止日期！");
				}

				String gp_info = req.getParameter("gp_info");
				if (gp_info == null || gp_info.length() == 0) {
					gp_info = "一起去吃吧！！";
					errorMsgs.add("揪團簡介：請勿空白");
				}

				Integer gp_status = null;
				try {
					gp_status = new Integer(req.getParameter("gp_status"));
				} catch (Exception e) {
					errorMsgs.add("揪團狀態格式不正確");
				}
				
				//裝載錯誤要回送時的group_VO資料
				Group_VO group_VO = new Group_VO();
				group_VO.setMem_no(mem_no);
				group_VO.setStore_no(store_no);
				group_VO.setBooking_no(booking_no);
				group_VO.setGp_kind(gp_kind);
				group_VO.setMem_least(mem_least);
				group_VO.setMem_limit(mem_limit);
				group_VO.setDining_date(dining_date);
				group_VO.setStart_date(start_date);
				group_VO.setEnd_date(end_date);
				group_VO.setGp_info(gp_info);
				group_VO.setGp_status(gp_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_VO", group_VO); // 含有輸入格式錯誤的group_VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group/addGroup.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				Group_Service group_Svc = new Group_Service();
				group_VO = group_Svc.addGroup_(mem_no, store_no, booking_no, gp_kind, mem_least, mem_limit, dining_date,
						start_date, end_date, gp_info, gp_status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-customer-end/group/listAllGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllGroup.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group/addGroup.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllGroup.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String group_no = req.getParameter("group_no");

				/*************************** 2.開始刪除資料 ***************************************/
				Group_Service group_Svc = new Group_Service();
				group_Svc.deleteGroup_(group_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-customer-end/group/listAllGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group/listAllGroup.jsp");
				failureView.forward(req, res);
			}

		}
	}
}
