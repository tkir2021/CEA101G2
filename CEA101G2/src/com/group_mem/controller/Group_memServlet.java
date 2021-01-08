package com.group_mem.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_mem.model.Group_memService;
import com.group_mem.model.Group_memVO;



@WebServlet("/Group_memService")
public class Group_memServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group_mem/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				
				
				if (!group_no.matches("^[G]{1}[P]{1}\\d{8}$")) {
					errorMsgs.add("揪團編號格式不正確");
				}
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group_mem/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				Group_memService group_memSvc = new Group_memService();
				List<Group_memVO> oneGroupList = group_memSvc.getOneGroup_mem(group_no); // 將取得資料放入group_memVO
				if (oneGroupList == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group_mem/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("oneGroupList", oneGroupList); // 資料庫取出的oneGroupList物件,存入req
				String url = "/front-customer-end/group_mem/listOneGroup_mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneGroup_mem.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group_mem/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 來自addGroup_mem.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String group_no = req.getParameter("group_no");
				if (!group_no.matches("^[G]{1}[P]{1}\\d{8}$")) {
					errorMsgs.add("店家編號格式不正確");
				}
				
				String mem_no = req.getParameter("mem_no");
				if (!mem_no.matches("^[M]{1}[M]{1}\\d{8}$")) {
					errorMsgs.add("會員編號格式不正確");
				}
				
				//裝載錯誤要回送時的group_memVO資料
				Group_memVO group_memVO = new Group_memVO();
				group_memVO.setGroup_no(group_no);
				group_memVO.setMem_no(mem_no);
	

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_memVO", group_memVO); // 含有輸入格式錯誤的group_memVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group_mem/addGroup_mem.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				Group_memService group_memSvc = new Group_memService();
				group_memVO = group_memSvc.addGroup_mem(group_no, mem_no);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-customer-end/group_mem/listAllGroup_mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllGroup_mem.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group_mem/addGroup_mem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllGroup_mem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String group_no = req.getParameter("group_no");
				String mem_no = req.getParameter("mem_no");

				/*************************** 2.開始刪除資料 ***************************************/
				Group_memService group_memSvc = new Group_memService();
				group_memSvc.deleteGroup_mem(group_no, mem_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-customer-end/group_mem/listAllGroup_mem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-customer-end/group_mem/listAllGroup_mem.jsp");
				failureView.forward(req, res);
			}

		}
	}

}
